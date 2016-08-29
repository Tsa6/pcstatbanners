package com.github.tsa6.piratecraftforumbanners.statistics;

import java.io.IOException;

public interface CachingStatistic {
	void reload() throws IOException;
	long nextReload() throws IOException;
}
