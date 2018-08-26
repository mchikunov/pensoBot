package main;


import controller.IncomingCallServlet;
import model.Pensioner;
import org.eclipse.jetty.servlet.ServletHolder;
import service.PensionerService;
import service.PensionerServiceImpl;
import util.DbHelper;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import util.SendGetRequestToBot;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        BotStarter.startBot();

        DbHelper dbHelper = new DbHelper();
        PensionerService pensionerService = new PensionerServiceImpl();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new IncomingCallServlet(pensionerService)), "/phone");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8081);
        server.setHandler(handlers);
        server.start();

        System.out.println("Server started");
        server.join();
    }
}
