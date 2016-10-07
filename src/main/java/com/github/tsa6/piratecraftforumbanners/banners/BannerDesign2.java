package com.github.tsa6.piratecraftforumbanners.banners;

import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import com.github.tsa6.piratecraftforumbanners.statistics.Statistic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.List;

public class BannerDesign2 extends BannerDesign1 {
	
	private final Image background;
	private final Color leftColor;
	private final Color statColor;
	
	public BannerDesign2(String user, List<Statistic> stats, Image background) throws MalformedPlayernameException {
		super(user, stats);
		this.background = background;
		this.leftColor = super.getLeftColor();
		this.statColor = super.getStatisticBackgroundColor();
	}
	
	public BannerDesign2(String user, List<Statistic> stats, Image background, Color leftBackgroundColor, Color statBackgroundColor) throws MalformedPlayernameException {
		super(user, stats);
		this.background = background;
		this.leftColor = leftBackgroundColor;
		this.statColor = statBackgroundColor;
	}

	public Image getBackground() {
		return background;
	}

	@Override
	protected void paintBG(Graphics g) throws IOException {
		g.drawImage(background, 0, 0, null);
	}

	@Override
	protected Color getStatisticBackgroundColor() {
		return statColor;
	}

	@Override
	public Color getLeftColor() {
		return leftColor;
	}
	
}
