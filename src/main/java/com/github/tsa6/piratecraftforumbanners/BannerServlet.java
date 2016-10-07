package com.github.tsa6.piratecraftforumbanners;

import com.github.tsa6.piratecraftforumbanners.banners.Banner;
import com.github.tsa6.piratecraftforumbanners.banners.BannerDesign1;
import com.github.tsa6.piratecraftforumbanners.banners.BannerDesign2;
import com.github.tsa6.piratecraftforumbanners.statistics.Statistic;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public abstract class BannerServlet extends javax.servlet.http.HttpServlet{
	
	private static final CacheManager IMAGE_CACHE = new CacheManager(1000*60*60*24);
	private static final Pattern QUERY_STRING_VERSION_PATTERN = Pattern.compile("v=(?<v>[a-z%d\\*-_.%%~+]*)");
	private static final String CURRENT_VERSION = "2";

	private final List<String> bannedUsers;
	private final URL baseStatsURL;

	public BannerServlet(URL baseStatsURL) {
		new File("pcbanners").mkdir();
		this.baseStatsURL = baseStatsURL;

		File bannedFile = new File("pcbanners/bannedusernames.txt");
		List<String> toAssign = null;
		try {
			if(!bannedFile.exists()) {
					FileChannel fileChannel = FileChannel.open(bannedFile.toPath(),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
					ReadableByteChannel defaults = Channels.newChannel(getClass().getResourceAsStream("/piratecraftbanners/banlist.txt"));
					fileChannel.transferFrom(defaults, 0, Long.MAX_VALUE);
			}
			toAssign = Files.readAllLines(bannedFile.toPath());
		} catch (IOException ex) {
			toAssign = new ArrayList<>();
		} finally {
			bannedUsers = toAssign;
		}
	}

	@Override
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int w = 500;
		int h = 100;

		resp.addDateHeader("Date", System.currentTimeMillis());
		
		String pathInfo = req.getPathInfo();
		String[] context;
		if(pathInfo != null) {
			context = req.getPathInfo().split("/");
		}else{
			context = new String[0];
		}
		Banner banner = null;
		boolean error = false;
		boolean handledError = false;
		BufferedImage bannerImg = null;
		String user;
		//[1] = design
		//[2] = user
		//[3+] = other (statistic, bg, etc)
		if(context.length < 3) {
			error = true;
			user = null;
		}else{
			bannerImg = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
			resp.setContentType("image/png");
			user = context[2];
			if(bannedUsers.contains(user)) {
				error = true;
				resp.setStatus(423);
				resp.setHeader("cache-control", "max-age:864000");
				ImageUtils.drawError(bannerImg.createGraphics(), w, h, String.format("Error 423: User %s has been blocked", user));
				ImageIO.write(bannerImg, "png", resp.getOutputStream());
				handledError = true;
			}
			try {
				switch(context[1]) {
					case "1":
						ArrayList<Statistic> stats = new ArrayList<>();
						for(int i = 3; i < context.length; i++) {
							stats.add(Statistic.getStatistic(context[i], user, baseStatsURL));
						}
						banner = new BannerDesign1(user, stats);
						break;
					case "2":
						try {
							BufferedImage bgImg;
							switch(context[3]) {
								case "a":
									bgImg = BannerDesign1.getBackgroundImage();
									break;
								default:
									URL imageURL = new URL(new String(Base64.getDecoder().decode(context[3])));
									bgImg = ImageIO.read(IMAGE_CACHE.get(imageURL));
							}
						
							ArrayList<Statistic> stats2 = new ArrayList<>();
							for(int i = 7; i < context.length; i++) {
								stats2.add(Statistic.getStatistic(context[i], user, baseStatsURL));
							}
							Color statColorOpaque = Color.decode("0x"+context[5]);
							Color statColorTrans = "1".equals(context[6])?new Color(statColorOpaque.getRed(),statColorOpaque.getGreen(),statColorOpaque.getBlue(),150):statColorOpaque;
							banner = new BannerDesign2(user, stats2, bgImg, Color.decode("0x"+context[4]), statColorTrans);
						}catch(MalformedURLException ex) {
							error = true;
							resp.setStatus(400);
							resp.setHeader("cache-control", "max-age:864000");
							ImageUtils.drawError(bannerImg.createGraphics(), w, h, String.format("Error 400: Malformed url"));
							ImageIO.write(bannerImg, "png", resp.getOutputStream());
							handledError = true;
						}catch(IOException ex) {
							error = true;
							resp.setStatus(400);
							resp.setHeader("cache-control", "max-age:864000");
							ImageUtils.drawError(bannerImg.createGraphics(), w, h, String.format("Error 500:  %s", ex.getMessage()));
							ImageIO.write(bannerImg, "png", resp.getOutputStream());
							handledError = true;
						}catch(NumberFormatException ex) {
							error = true;
							resp.setStatus(400);
							resp.setHeader("cache-control", "max-age:864000");
							ImageUtils.drawError(bannerImg.createGraphics(), w, h, String.format("Error 400:  %s:%s", ex.getClass().getSimpleName(), ex.getMessage()));
							ImageIO.write(bannerImg, "png", resp.getOutputStream());
							handledError = true;
						}catch(IllegalArgumentException ex) {
							error = true;
							resp.setStatus(400);
							resp.setHeader("cache-control", "max-age:864000");
							ImageUtils.drawError(bannerImg.createGraphics(), w, h, String.format("Error 400:  Malformed Base64: %s", context[3]));
							ImageIO.write(bannerImg, "png", resp.getOutputStream());
							handledError = true;
						}
						break;
					default:
						error = true;
				}
			}catch(MalformedPlayernameException ex) {
				error = true;
			} catch (ProfanityException ex) {
				error = true;
				resp.setStatus(400);
				ImageUtils.drawError(bannerImg.createGraphics(), w, h, String.format("Error 400: Server refuses to generate profane banner", user));
				ImageIO.write(bannerImg, "png", resp.getOutputStream());
				handledError = true;
			}
		}

		//Send
		if(error && !handledError) {
			if(context.length > 1) {
				String reqStr = req.getRequestURI();
				if(reqStr.endsWith("/")) {
					reqStr += "/";
				}
				if(reqStr.startsWith("/")) {
					reqStr = reqStr.substring(1);
				}
				String[] reqURI = reqStr.split("\\/");
				String[] redURI = Arrays.copyOf(reqURI, reqURI.length - context.length + 1);
				resp.sendRedirect("/" + Arrays.stream(redURI).collect(Collectors.joining("/")));
			}else{
				String queryString = req.getQueryString();
				String version;
				if(queryString != null) {
					Matcher m = QUERY_STRING_VERSION_PATTERN.matcher(queryString);
					if(m.find()) {
						version = m.group("v");
					}else{
						version = CURRENT_VERSION;
					}
				}else{
					version = CURRENT_VERSION;
				}
				resp.setHeader("Content-Type","text/html");
				resp.setHeader("Cache-Control", "public, max-age=86400, stale-while-revalidate=604800, stale-if-error=604800");
				resp.setHeader("ETag", "v2");
				if(!"v2".equals(req.getHeader("If-None-Match"))) {
					String resourceLocation;
					switch(version) {
						case "1":
							resourceLocation = "/piratecraftbanners/pages/banners_1.html";
							break;
						case "2":
						default:
							resourceLocation = "/piratecraftbanners/pages/banners.html";
					}
					InputStream returnFile = BannerServlet.class.getResourceAsStream(resourceLocation);
					ByteArrayOutputStream baout = new ByteArrayOutputStream();
					byte[] buf = new byte[1028];
					int read;
					while((read = returnFile.read(buf)) != -1) {
						baout.write(buf,0,read);
					}
					resp.addIntHeader("Content-Length", baout.size());
					baout.writeTo(resp.getOutputStream());
				}else{
					resp.setStatus(304);
				}
			}
		}else if(!error){
			resp.setHeader("Cache-Control", "stale-while-revalidate=86400, stale-if-error=86400, max-age="+(CacheManager.getInstance().getNextUpdate(new URL(baseStatsURL, user)) - System.currentTimeMillis()));
			banner.paintOnto(bannerImg.createGraphics());
			ByteArrayOutputStream baout = new ByteArrayOutputStream();
			ImageIO.write(bannerImg, "png", baout);
			resp.addIntHeader("Content-Length", baout.size());
			baout.writeTo(resp.getOutputStream());
		}
	}

}
