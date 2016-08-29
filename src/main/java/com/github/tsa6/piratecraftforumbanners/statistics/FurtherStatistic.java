package com.github.tsa6.piratecraftforumbanners.statistics;

import com.github.tsa6.piratecraftforumbanners.MalformedPlayernameException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class FurtherStatistic extends RegexableStatistic {

	public static final String STATISTIC_BLOCKS_BROKEN = "Blocks Broken";
	public static final String STATISTIC_BLOCKS_PLACED = "Blocks Placed";
	public static final String STATISTIC_ARROWS_FIRED = "Arrows Fired";
	public static final String STATISTIC_COLLECTED_EXPIRIENCE = "Collected EXP";
	public static final String STATISTIC_FISH_CAUGHT = "Fish Caught";
	public static final String STATISTIC_DAMAGE_TAKEN = "Total Damage Taken";
	public static final String STATISTIC_FOOD_EATEN = "Food Eaten";
	public static final String STATISTIC_CRAFTED_ITEMS = "Crafted Items";
	public static final String STATISTIC_EGGS_THROWN = "Eggs Thrown";
	public static final String STATISTIC_TOOLS_BROKEN = "Tools Broken";
	public static final String STATISTIC_COMMANDS_EXECUTED = "Commands";
	public static final String STATISTIC_ITEMS_DROPPED = "Items Dropped";
	public static final String STATISTIC_ITEMS_COLLECTED = "Items Picked Up";
	public static final String STATISTIC_TELEPORTS = "Teleports";

	public FurtherStatistic(String statisticname, String playername, URL baseStatsURL) throws MalformedURLException, MalformedPlayernameException {
		super(statisticname, playername, baseStatsURL);
	}

	@Override
	protected Pattern getStatisticPattern() {
		return Pattern.compile("<tr>[\\s]*<th><i class=\"fa (?<icon>[a-z\\-]+)\"><\\/i> (?<name>[A-Za-z\\s]+)<\\/th>[\\s]*<td>(?<value>[\\d]+) [^<]+<\\/td>[\\s]*<td>[^<]+<\\/td>[\\s]*<\\/tr>",Pattern.CASE_INSENSITIVE);
	}

	@Override
	public String getText() {
		return String.format("%s %s",getValue(),getStatisticName());
	}

}
