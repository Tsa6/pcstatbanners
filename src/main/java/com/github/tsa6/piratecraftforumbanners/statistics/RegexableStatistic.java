package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.CacheManager;
import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import com.github.tsa6.piratecraftforumbanners.PlayerNotFoundException;
import java.io.EOFException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class RegexableStatistic extends FAStatistic {

	private final String playername;
	private final String statisticname;
	private final URL playerStatsURL;
	private char icon;
	private String value;

	public RegexableStatistic(String statisticname, String playername, URL baseStatsURL) throws MalformedPlayernameException, MalformedURLException {
		MalformedPlayernameException.requireValid(playername);
		this.playername = playername;
		this.statisticname = statisticname;
		this.playerStatsURL = new URL(baseStatsURL, playername);
	}

	//needs to have groups "icon", "name", and "value"
	protected abstract Pattern getStatisticPattern();

	protected String getValue() {
		return value;
	}

	public String getPlayerName() {
		return playername;
	}

	public String getStatisticName() {
		return statisticname;
	}

	@Override
	public void load() throws IOException, PlayerNotFoundException {
		Matcher fullTextSearch = getStatisticPattern().matcher(CacheManager.getInstance().getLines(playerStatsURL).stream().collect(Collectors.joining(String.format("%n"))));
		while(fullTextSearch.find()) {
			if(fullTextSearch.group("name").equalsIgnoreCase(getStatisticName())) {
				icon = translateToCharacter(fullTextSearch.group("icon"));
				value = fullTextSearch.group("value").replaceAll("<strong>(?<text>[^<]*)<\\/strong>", "${text}").replaceAll("&pound;", "£");
				return;
			}
		}
		throw new EOFException("Reached end of statistics page without finding statistic \""+getStatisticName()+"\".");
	}

	@Override
	protected char getCharacter() {
		return icon;
	}

}
