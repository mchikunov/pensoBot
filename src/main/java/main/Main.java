package main;


import controller.IncomingCallServlet;
import controller.ServletBot;
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


        // String url = "http://054098cc.ngrok.io/servletBot"+"?address=" + "москва".getBytes(StandardCharsets.US_ASCII);
        // ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(url);


        DbHelper dbHelper = new DbHelper();
        BotStarter.startBot();
        PensionerService pensionerService = new PensionerServiceImpl();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new IncomingCallServlet(pensionerService)), "/phone");
        context.addServlet(new ServletHolder(new ServletBot()), "/servletBot");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");


        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});


        Server server = new Server(8081);
        server.setHandler(handlers);
        server.start();


        pensionerService.addPensioner(new Pensioner("Иван", "Иванов", "64", "Москва улица Ленина 1 кв 1", "79854859568", "All I need is ..."));


        System.out.println("Server started");
        server.join();
    }
}
