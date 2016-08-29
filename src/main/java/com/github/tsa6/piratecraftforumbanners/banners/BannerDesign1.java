package com.github.tsa6.piratecraftforumbanners.banners;

import com.github.tsa6.piratecraftforumbanners.ImageUtils;
import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import com.github.tsa6.piratecraftforumbanners.PlayerNotFoundException;
import com.github.tsa6.piratecraftforumbanners.statistics.FAStatistic;
import com.github.tsa6.piratecraftforumbanners.statistics.Statistic;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author taizosimpson
 */
public class BannerDesign1 extends Banner {

	private int WIDTH = 500;
	private int HEIGHT = 100;
	private int MARGIN = 5;
	private int STATISTIC_HEIGHT = 15;
	private Color BACKGROUND = new Color(255, 255, 255);
	private Color LEFT = new Color(204, 255, 255);
	private Font FONT = new Font("Arial", Font.PLAIN, 60);

	private List<Statistic> stats;
	private boolean error = false; //Something has gone wrong, and nothing is to be trusted
	private long nextRefresh;

	public BannerDesign1(String user, List<Statistic> stats) throws MalformedPlayernameException {
		super(user);
		this.stats = stats;
		for(Statistic s : stats) {
			if(error) {
				break;
			}
			try {
				s.load();
			} catch (IOException ex) {
				System.err.println("Problem loading statistic "+s.getClass().getCanonicalName());
				ex.printStackTrace();
				error = true;
			} catch (PlayerNotFoundException ex) {
				this.stats = Arrays.asList(new FAStatistic() {
					@Override
					protected char getCharacter() {
						return FAStatistic.translateToCharacter("fa-times");
					}

					@Override
					public void load() throws IOException, PlayerNotFoundException { }

					@Override
					public String getText() {
						return "No statistics found";
					}
				}, Statistic.BLANK, Statistic.BLANK);
				break;
			}
		}
	}

	protected List<Statistic> getStats() {
		return new ArrayList<>(stats);
	}

	@Override
	public void paintOnto(Graphics g) throws IOException {
		if(error) {
			ImageUtils.drawError(g, WIDTH, HEIGHT);
		}else{
			paintBG(g);
			paintStatistics(g);
			paintHeadBG(g);
			paintName(g);
			paintHead(g);
		}
	}

	private void paintBG(Graphics g) throws IOException {
		g.drawImage(ImageIO.read(Banner.class.getResource("/backgrounds/1.png")), 0, 0, null);
	}

	private void paintHeadBG(Graphics g) {
		g.setColor(LEFT);
		g.fillPolygon(new int[]{0,(int)(HEIGHT*0.8),HEIGHT,0}, new int[]{0,0,HEIGHT,(int)(HEIGHT*1.1)}, 4);
	}

	private void paintName(Graphics g) {
		g.setFont(FONT);
		g.setColor(Color.BLACK);
		double targetWidth = 0.8 * HEIGHT - 2 * MARGIN;
		double targetHeight = 0.2 * HEIGHT - MARGIN;
		g.setFont(ImageUtils.determineFontSize(g, FONT, getUser(), targetWidth, targetHeight));
		FontMetrics fm = g.getFontMetrics();
		g.drawString(getUser(), (int) ((targetWidth - fm.getStringBounds(getUser(), g).getWidth()) / 2) + MARGIN,(int)(HEIGHT - (targetHeight - fm.getFont().getSize() * 3f / 4f)/2 - MARGIN));
	}

	private void paintHead(Graphics g) throws IOException {
		BufferedImage head;
		int size = (int) (HEIGHT*0.8 - 2*MARGIN);
		try {
			head = ImageIO.read(Banner.getGraphicsCache().get(getURL(getUser(), size)));
		} catch (IOException ex) {
			throw new IOException("Problem downloading head image from cravatar", ex);
		}
		g.drawImage(head, MARGIN, MARGIN, null);
	}

	private void paintStatistics(Graphics g) throws IOException {
		float relativeXOrigin = (float) (HEIGHT*0.8 + MARGIN);
		float spaceBetweenStats = (float) (HEIGHT - STATISTIC_HEIGHT * getStats().size()) / (getStats().size() + 1);
		g.setFont(ImageUtils.determineFontSize(g, FONT, "ABCDEFGHIJKLMNOPWXYZ", Double.MAX_VALUE, STATISTIC_HEIGHT));
		FontMetrics fm = g.getFontMetrics();
		for(int i = 0; i <getStats().size(); i++) {
			Statistic stat = getStats().get(i);
			Image icon = stat.getIcon(STATISTIC_HEIGHT);
			int uppermostPoint = (int) (spaceBetweenStats + i * (spaceBetweenStats + STATISTIC_HEIGHT));
			int leftmostPoint = (int) (relativeXOrigin + 0.3*(uppermostPoint + STATISTIC_HEIGHT));
			int iconSize = icon==null?0:STATISTIC_HEIGHT;
			String text = stat.getText();

			//Paint background
			if(!(text.matches("[\\s]*") && icon == null)) {
				g.setColor(new Color(109, 109, 120, 150));
				g.fillPolygon(new int[]{
					0,
					leftmostPoint + MARGIN * 2 + iconSize + fm.stringWidth(text),
					(int) (leftmostPoint + MARGIN * 2 + iconSize + fm.stringWidth(text) + 0.3*STATISTIC_HEIGHT),
					0
				}, new int[]{
					uppermostPoint - MARGIN,
					uppermostPoint - MARGIN,
					uppermostPoint + STATISTIC_HEIGHT + MARGIN,
					uppermostPoint + STATISTIC_HEIGHT + MARGIN
				}, 4);

				//Paint statistic
				g.setColor(Color.BLACK);
				g.drawImage(icon, leftmostPoint, uppermostPoint, null);
				g.drawString(text, leftmostPoint + MARGIN + iconSize, uppermostPoint + STATISTIC_HEIGHT);
			}
		}
	}

	private static URL getURL(String user, int size) {
		try {
			return new URL("http://cravatar.eu/helmhead/"+user+"/"+size+".png");
		} catch (MalformedURLException ex) {
			throw new IllegalArgumentException("Malformed user id:  "+user, ex);
		}
	}
}
