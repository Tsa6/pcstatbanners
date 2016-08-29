package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.ProfanityException;
import static com.github.tsa6.piratecraftforumbanners.ProfanityException.requireClean;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class CustomStatistic implements Statistic {

	private final String text;
	private final URL url;
	private Image icon;

	public CustomStatistic(String text, URL url) throws ProfanityException {
		this.text = text;
		this.url = url;
		requireClean(text);
	}

	@Override
	public void load() throws IOException {
		if(url != null) {
			icon = ImageIO.read(url);
		}else{
			icon = null;
		}
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Image getIcon(int size) {
		if(icon != null)
			return icon.getScaledInstance(size, size, Image.SCALE_FAST);
		else return null;
	}

}
