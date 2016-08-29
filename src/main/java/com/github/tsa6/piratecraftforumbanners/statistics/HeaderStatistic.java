package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class HeaderStatistic extends RegexableStatistic {

	public static final String STATISTIC_PLAYTIME = "Playtime";
	public static final String STATISTIC_DEATHS = "Deaths";
	public static final String STATISTIC_KILLS = "Kills";
	public static final String STATISTIC_MONEY = "Money";
	public static final String STATISTIC_STREAK = "PVP Streak";
	public static final String STATISTIC_TOP_STREAK = "PVP Top Streak";

	private static final Pattern PATTERN = Pattern.compile("<li><i class=\"fa-li fa (?<icon>fa-[a-z\\-]+)\"></i><b>(?<value>(<strong>&pound;<\\/strong>)?[^<]+)</b> (?<name>[A-Z a-z]+)</li>");

	public HeaderStatistic(String statisticname, String playername, URL baseStatsURL) throws MalformedURLException, MalformedPlayernameException {
		super(statisticname, playername, baseStatsURL);
	}

	@Override
	protected Pattern getStatisticPattern() {
		return PATTERN;
	}

	@Override
	public String getText() {
		return String.format("%s %s", getValue(), getStatisticName());
	}

}
