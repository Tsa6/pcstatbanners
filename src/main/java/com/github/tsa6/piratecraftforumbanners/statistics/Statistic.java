package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import com.github.tsa6.piratecraftforumbanners.PlayerNotFoundException;
import com.github.tsa6.piratecraftforumbanners.ProfanityException;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Statistic {

	public static final Statistic BLANK = BlankStatistic.getBlankStatistic();

	void load() throws IOException, PlayerNotFoundException;
	String getText();
	Image getIcon(int size);

	public static Statistic getStatistic(String text, String player, URL baseStatsURL) throws MalformedPlayernameException, ProfanityException, MalformedURLException {
		switch(text) {
			case "Playtime":
			case "Deaths":
			case "Kills":
			case "Money":
			case "PVP Streak":
			case "PVP Top Streak":
				return new HeaderStatistic(text, player, baseStatsURL);
			case "Blocks Broken":
			case "Blocks Placed":
			case "Arrows Fired":
			case "Collected EXP":
			case "Fish Caught":
			case "Total Damage Taken":
			case "Food Eaten":
			case "Crafted Items":
			case "Eggs Thrown":
			case "Tools Broken":
			case "Commands":
			case "Items Dropped":
			case "Items Picked Up":
			case "Teleports":
				return new FurtherStatistic(text, player, baseStatsURL);
			case "Hidden":
				return BLANK;
		}

		Matcher blockMatcher = Pattern.compile("Block(?<type>PLACE|BREAK)(?<id>\\d+)").matcher(text);
		if(blockMatcher.find()) {
			return new BlockStatistic(Integer.parseInt(blockMatcher.group("id")), BlockStatistic.Type.valueOf(blockMatcher.group("type")), player, baseStatsURL);
		}

		Matcher customMatcher = Pattern.compile("(?<text>[^|]+)\\|(?<url>[^|]+)").matcher(text);
		if(customMatcher.find()) {
			try {
				return new CustomStatistic(customMatcher.group("text"), new URL(customMatcher.group("url")));
			} catch (MalformedURLException ex) { } //Handled by next section
		}

		return new CustomStatistic(text, null);

	}

	static class BlankStatistic extends CustomStatistic {

		private static final BlankStatistic instance;
		static {
			try {
				instance = new BlankStatistic();
			} catch (ProfanityException ex) {
			throw new Error(ex);
			}
		}

		private BlankStatistic() throws ProfanityException {
			super("", null);
		}

		public static BlankStatistic getBlankStatistic() {
			return instance;
		}

	}

}
