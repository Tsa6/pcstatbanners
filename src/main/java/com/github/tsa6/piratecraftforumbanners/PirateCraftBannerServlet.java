package com.github.tsa6.piratecraftforumbanners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PirateCraftBannerServlet extends BannerServlet {

	private static final URL url;
	static {
		try {
			url = new URL("http://stats.piratemc.com/");
		} catch (MalformedURLException ex) {
			throw new Error(ex);
		}
	}

	public PirateCraftBannerServlet() {//Fix
		super(url);
		if(System.getProperty("http.agent") == null) {
			System.setProperty("http.agent", "StatForumBanners/1.0(Contact tsa6games@gmail.com)");
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "Updated http.agent to {0}", System.getProperty("http.agent"));
		}
	}

}