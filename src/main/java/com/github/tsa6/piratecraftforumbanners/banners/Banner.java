package com.github.tsa6.piratecraftforumbanners.banners;

import com.github.tsa6.piratecraftforumbanners.CacheManager;
import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import java.awt.Graphics;
import java.io.IOException;

public abstract class Banner {

	private static final CacheManager GRAPHICS_CACHE = new CacheManager(1000*60*60*24*3);

	private final String user;

	public static CacheManager getGraphicsCache() {
		return GRAPHICS_CACHE;
	}

	public Banner(String user) throws MalformedPlayernameException {
		MalformedPlayernameException.requireValid(user);
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public abstract void paintOnto(Graphics g) throws IOException;

}
