package com.github.tsa6.piratecraftforumbanners;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CacheManager {

	private static CacheManager instance = new CacheManager();
	private static final ScheduledExecutorService clearThread = Executors.newSingleThreadScheduledExecutor();

	private final HashMap<URL, CacheEntry> cache = new HashMap<>();
	private int refreshMillis;

	public CacheManager() {
		this(1000*60);
	}

	public CacheManager(int refreshMillis) {
		this.refreshMillis = refreshMillis;
	}

	public InputStream get(URL url) throws IOException {
		CacheEntry out;
		if(!cache.containsKey(url)) {
			out = new CacheEntry(url);
			cache.put(url, out);
		}else{
			out = cache.get(url);
		}
		return out.get();
	}

	public List<String> getLines(URL url) throws IOException {
		return new BufferedReader(new InputStreamReader(get(url))).lines().collect(Collectors.toList());
	}

	public long getNextUpdate(URL url) {
		if(!cache.containsKey(url)) {
			CacheEntry entry = new CacheEntry(url);
			try {
				entry.get();
			} catch (IOException ex) {
				Logger.getLogger(this.getClass().getCanonicalName()).warning(ex.getMessage());
			}
			cache.put(url, entry);
		}
		return cache.get(url).nextUpdate();
	}

	public void deleteCache(URL url) {
		cache.remove(url);
	}

	public void clearCache(){
		cache.clear();
	}

	public int getRefreshMillis() {
		return refreshMillis;
	}

	public void setRefreshMillis(int refreshMillis) {
		this.refreshMillis = refreshMillis;
	}

	public static CacheManager getInstance() {
		return instance;
	}

	private class CacheEntry {

		private final URL url;
		private byte[] data;
		private long lastUpdated;
		private ScheduledFuture nextClear;

		CacheEntry(URL url) {
			this.url = url;
		}

		public InputStream get() throws IOException {
			
			//Reset clear countdown
			if(nextClear != null) {
				nextClear.cancel(false);
			}
			nextClear = clearThread.schedule(this::clear, 2, TimeUnit.HOURS);
			
			
			if(shouldReload()) {
				ByteArrayOutputStream buf = new ByteArrayOutputStream();
				byte[] sbuff = new byte[2048];
				int bytesRead;
				URLConnection conn = url.openConnection();
				conn.setRequestProperty("User-Agent", "TBot/1.0(Contact tsa6games@gmail.com)");
				try(
						InputStream is = conn.getInputStream()
				) {
					while((bytesRead = is.read(sbuff)) > 0) {
						buf.write(sbuff, 0, bytesRead);
					}
				}
				lastUpdated = System.currentTimeMillis();
				data = buf.toByteArray();
			}
			return new ByteArrayInputStream(data);
		}

		protected boolean shouldReload() {
			return data == null || System.currentTimeMillis() - lastUpdated > refreshMillis;
		}

		public long getLastUpdated() {
			return lastUpdated;
		}

		public long nextUpdate() {
			return lastUpdated + refreshMillis;
		}

		public void clear() {
			data = null;
			lastUpdated = 0;
		}
	}

}
