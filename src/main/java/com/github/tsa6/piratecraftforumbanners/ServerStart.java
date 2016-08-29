package com.github.tsa6.piratecraftforumbanners;

import java.io.File;
import java.util.Scanner;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ServerStart {
	public static void main(String[] args) throws Exception {
		Server server = new Server(80);
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[]{connector});

		File logFolder = new File("logs/requests");
		logFolder.mkdirs();
		server.setRequestLog(new CustomLogger(logFolder.toPath()));

		ServletContextHandler pcbannerHandler = new ServletContextHandler();
		pcbannerHandler.setContextPath("/");
		pcbannerHandler.addServlet(PirateCraftBannerServlet.class, "/pcstatbanner/*");
		HandlerCollection allhandlers = new HandlerCollection();
		allhandlers.addHandler(pcbannerHandler);
		allhandlers.addHandler(new DefaultHandler());
		server.setHandler(allhandlers);
		server.start();

		Scanner input = new Scanner(System.in);
		while(true) {
			String line = input.nextLine();
			if(line.equalsIgnoreCase("quit") | Thread.interrupted()) {
				server.stop();
				server.join();
				break;
			}
		}
	}


}
