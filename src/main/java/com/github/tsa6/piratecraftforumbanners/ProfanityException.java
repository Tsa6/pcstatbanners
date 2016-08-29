package com.github.tsa6.piratecraftforumbanners;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProfanityException extends Exception {

	private static ProfanityChecker checker;
	static {
		File swearFile = new File("swears.txt");
		List<String> swears = new ArrayList<>();
		try {
			if(!swearFile.exists()) {
				FileChannel fileChannel = FileChannel.open(swearFile.toPath(),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
				ReadableByteChannel defaults = Channels.newChannel(ProfanityException.class.getResourceAsStream("/piratecraftbanners/swears.txt"));
				fileChannel.transferFrom(defaults, 0, Long.MAX_VALUE);
			}
			swears = Files.readAllLines(swearFile.toPath());
		} catch (IOException ex) {
			swears = new ArrayList<>();
		} finally {
			checker = new ProfanityChecker(swears);
		}
	}

	private final String swear;

	public ProfanityException(String swear) {
		super("Message blocked for containing swear \""+swear+'"');
		this.swear = swear;
	}

	public String getSwear() {
		return swear;
	}

	public static ProfanityChecker getChecker() {
		return checker;
	}

	public static void setChecker(ProfanityChecker checker) {
		ProfanityException.checker = checker;
	}

	public static void requireClean(String message) throws ProfanityException {
		String swear = checker.check(message);
		if(swear != null) {
			throw new ProfanityException(swear);
		}
	}
}
