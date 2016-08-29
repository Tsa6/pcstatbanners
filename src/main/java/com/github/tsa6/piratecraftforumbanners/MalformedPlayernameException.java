package com.github.tsa6.piratecraftforumbanners;

public class MalformedPlayernameException extends PlayerNotFoundException {

	public MalformedPlayernameException(String playername) {
		super(playername);
	}

	public MalformedPlayernameException(String playername, Throwable cause) {
		super("Playername was malformed or otherwise illegal:  "+playername, cause);
	}

	public static void requireValid(String playername) throws MalformedPlayernameException {
		if(!isValid(playername)) {
			throw new MalformedPlayernameException(playername);
		}
	}

	public static boolean isValid(String playername) {
		return playername.matches("[a-zA-z\\d_]{3,16}");
	}

}
