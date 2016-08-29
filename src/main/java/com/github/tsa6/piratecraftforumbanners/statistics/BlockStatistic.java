package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import com.github.tsa6.piratecraftforumbanners.PlayerNotFoundException;
import com.github.tsa6.piratecraftforumbanners.banners.Banner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public class BlockStatistic extends RegexableStatistic {

	private final Type type;
	private boolean error = false;

	public BlockStatistic(int blockid, Type type, String playername, URL baseStatsURL) throws MalformedPlayernameException, MalformedURLException {
		super(String.format("%d", blockid), playername, baseStatsURL);
		this.type = type;
	}

	@Override
	protected Pattern getStatisticPattern() {
		return Pattern.compile("<tr><td>\\s*<img src=\"http://stats.piratemc.com/img/blocks/\\d+.png\" title=\"Block ID: \\d+\" class=\"img-circle\" width=\"[^\"]+\"> <b>(?<icon>(?<name>\\d+))<\\/b><\\/td><td>\\d+<\\/td><td>(?<value>\\d+)<\\/td><td>" + type.toString() + "<\\/td><td>Pirate Survival</td></tr>");
	}

	@Override
	public String getText() {
		return String.format("%s %s",getValue(), getType());
	}

	@Override
	protected String getValue() {
		if(!error) {
			return super.getValue();
		}else{
			return "0";
		}
	}

	@Override
	public Image getIcon(int size) {
		try {
			return ImageIO.read(Banner.getGraphicsCache().get(new URL("http://stats.piratemc.com/img/blocks/" + getStatisticName() + ".png"))).getScaledInstance(size, size, BufferedImage.SCALE_FAST);
		} catch (IOException ex) {
			if(ex.getCause() instanceof FileNotFoundException){
				//Requested unavailable block
			}else{
				ex.printStackTrace();//Unknown error
			}
			BufferedImage replacement = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
			Graphics g = replacement.createGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, size, size);
			g.setColor(Color.red);
			g.drawLine(0, 0, size, size);
			g.drawLine(0, size, size, 0);
			return replacement;
		}
	}

	public Type getType() {
		return type;
	}

	public static enum Type {
		PLACE, BREAK;

		@Override
		public String toString() {
			switch(this) {
				case PLACE:
					return "Placed";
				case BREAK:
					return "Broken";
				default:
					return "???";
			}
		}
	}

	@Override
	public void load() throws IOException, PlayerNotFoundException {
		try {
			super.load();
		}catch(EOFException ex) {
			error = true;
		}
	}

}
