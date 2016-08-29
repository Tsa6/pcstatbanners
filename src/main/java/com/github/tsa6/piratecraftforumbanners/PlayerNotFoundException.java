package com.github.tsa6.piratecraftforumbanners;

public class PlayerNotFoundException extends Exception {

	public PlayerNotFoundException(String player) {
		super("for "+player);
	}

	public PlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PlayerNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
