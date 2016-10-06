package com.github.tsa6.piratecraftforumbanners;

import java.net.MalformedURLException;
import java.net.URL;

public class PirateCraftBannerServlet extends BannerServlet {

	private static final URL URL;
	static {
		try {
			URL = new URL("http://stats.piratemc.com/");
		} catch (MalformedURLException ex) {
			throw new Error(ex);
		}
	}

	public PirateCraftBannerServlet() {
		super(URL);
	}

}