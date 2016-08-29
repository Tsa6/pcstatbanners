package com.github.tsa6.piratecraftforumbanners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfanityChecker {

	private final Pattern regex;

	public ProfanityChecker(Iterable<String> swears) {
		StringBuilder regexBuilder = new StringBuilder("");
		boolean first = true;
		for(String swear : swears) {
			if(first) {
				regexBuilder = regexBuilder.append("(");
				first = false;
			}else{
				regexBuilder = regexBuilder.append("|(");
			}
			boolean first1 = true;
			char[] chars = swear.toCharArray();
			for(int i = 0; i < chars.length; i++) {
				char c = chars[i];
				boolean firstOrLast = i == 0 || i == chars.length - 1;
				String charex;
				switch(c) {
					case 'c':
					case 'k':
						charex = "(c|k)";
						break;
					case 's':
						charex = "(s|\\$)";
						break;
					case '\\':
					case '[':
					case ']':
					case '^':
					case '$':
					case '.':
					case '*':
					case '(':
					case ')':
					case '?':
					case '+':
					case '{':
					case '}':
					case '|':
						charex = "\\" + c;
						break;
					default:
						charex = c + "";
				}
				charex += "+";
				if(firstOrLast) {
					regexBuilder = regexBuilder.append(charex);
				}else{
					regexBuilder = regexBuilder.append("([^a-z]|").append(charex).append(")");
				}
			}
			regexBuilder = regexBuilder.append(")");
		}
		this.regex = Pattern.compile(regexBuilder.toString(), Pattern.CASE_INSENSITIVE);
	}

	public String check(String message) {
		Matcher m = regex.matcher(message);
		if(m.find()) {
			return m.group();
		}else{
			return null;
		}
	}

	public Pattern getRegex() {
		return regex;
	}

}
