package com.github.tsa6.piratecraftforumbanners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;

public class CustomLogger implements RequestLog {

	private final Path directory;

	private PrintWriter dailyWriter;
	private ZonedDateTime dailyWriterTime;

	public CustomLogger(Path logDirectory) {
		directory = logDirectory;
	}

	@Override
	public void log(Request request, Response response) {
		//Check writer
		ZonedDateTime now = ZonedDateTime.now();
		if(dailyWriterTime == null || dailyWriterTime.getDayOfYear() != now.getDayOfYear() || dailyWriter.checkError()) {
			dailyWriterTime = now;
			File parentDir = new File(directory.toAbsolutePath().toString() + "/" + now.getYear()+ "/" + now.getMonth().getDisplayName(TextStyle.SHORT, Locale.US));
			parentDir.mkdirs();
			File log = new File(parentDir.getAbsolutePath() + "/" +now.getDayOfMonth() + ".log");
			boolean createFailed = false;
			boolean logCreated = false;
			if(!log.exists()) {
				try {
					log.createNewFile();
					logCreated = true;
				} catch (IOException ex) {
					Logger.getLogger(CustomLogger.class.getName()).log(Level.SEVERE, null, ex);
					createFailed = true;
				}
			}
			if(!createFailed) {
				try {
					dailyWriter = new PrintWriter(Files.newOutputStream(log.toPath(), StandardOpenOption.APPEND));
				} catch (IOException ex) {
					Logger.getLogger(CustomLogger.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			if(logCreated) {
				dailyWriter.println("[timestamp] remoteaddress:port -- \"method path\" status size \"referrer\" \"user-agent\"");
			}
		}

		dailyWriter.printf(
			"[%s] %s:%d -- \"%s %s\" %d %d \"%s\" \"%s\"%n",
			ZonedDateTime.ofInstant(Instant.ofEpochMilli(request.getTimeStamp()),ZoneId.systemDefault()).format(DateTimeFormatter.RFC_1123_DATE_TIME),
			request.getRemoteAddr(),
			request.getRemotePort(),
			request.getMethod(),
			request.getRequestURI(),
			response.getStatus(),
			response.getContentLength(),
			request.getHeader("Referer"),
			request.getHeader("User-Agent")
		);
		dailyWriter.flush();
	}
}
