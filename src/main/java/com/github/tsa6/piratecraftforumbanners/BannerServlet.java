package com.github.tsa6.piratecraftforumbanners;

import com.github.tsa6.piratecraftforumbanners.banners.Banner;
import com.github.tsa6.piratecraftforumbanners.banners.BannerDesign1;
import com.github.tsa6.piratecraftforumbanners.statistics.Statistic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BannerServlet extends javax.servlet.http.HttpServlet{

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
		//[3+] = statistics
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
				resp.setHeader("content-type","text/html");
				resp.setHeader("ETag", "v1");
				resp.setHeader("cache-control", "max-age:86400");
				InputStream returnFile = BannerServlet.class.getResourceAsStream("/piratecraftbanners/pages/banners.html");
				OutputStream response = resp.getOutputStream();
				byte[] buf = new byte[1028];
				int read;
				while((read = returnFile.read(buf)) != -1) {
					response.write(buf,0,read);
				}
			}
		}else if(!error){
			resp.setHeader("cache-control", "max-age="+(CacheManager.getInstance().getNextUpdate(new URL(baseStatsURL, user)) - System.currentTimeMillis()));
			banner.paintOnto(bannerImg.createGraphics());
			ImageIO.write(bannerImg, "png", resp.getOutputStream());
		}
	}

}
