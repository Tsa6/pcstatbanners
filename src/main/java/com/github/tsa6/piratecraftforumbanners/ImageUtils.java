package com.github.tsa6.piratecraftforumbanners;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class ImageUtils {
	public static Font determineFontSize(Graphics context, Font font, String text, double maxW, double maxH) {
		Objects.requireNonNull(font, "Provided font cannot be null");
		context = context.create();
		int min = 1;
		int max = 500;
		while( min + 1 < max ) {
			int value = (int) ((max + min)/2);
			context.setFont(font.deriveFont((float) value));
			Rectangle2D bounds = context.getFontMetrics().getStringBounds(text, context);
			if(bounds.getWidth() > maxW || (3f/4f) * value > maxH) {
				max = value;
			}else{
				min = value;
			}
		}
		return font.deriveFont((float) min);
	}
	
	public static void centerTextInArea(Graphics g, String text, int x, int y, int w, int h) {
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D textSize = fm.getStringBounds(text, g);
		g.drawString(text, (int) ((w - textSize.getWidth()) / 2 + x), (int) (y + h - (h - (fm.getFont().getSize()*(3f/4f))) / 2));
	}
	
	public static Dimension centerRectInRect(Dimension outer, Dimension inner) {
		return new Dimension((outer.width - inner.width)/2, (outer.height - inner.height)/2);
	}
	
	public static void drawError(Graphics g, int width, int height) {
		drawError(g, width, height, "An internal error occured attempting to retrieve this banner.");
	}
	
	public static void drawError(Graphics g, int width, int height, String message) {
		g.setColor(Color.WHITE);
		Polygon border = new Polygon(new int[]{0,width - 1,width - 1,0}, new int[]{0,0,height - 1,height - 1}, 4);
		g.fillPolygon(border);
		g.setColor(Color.RED);
		g.drawPolygon(border);
		g.setFont(ImageUtils.determineFontSize(g, Font.decode(Font.MONOSPACED), message, width*0.9, height*0.9));
		ImageUtils.centerTextInArea(g, message, 0, 0, width, height);
	}
}
