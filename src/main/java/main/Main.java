package main;


import service.Service;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
public static void main(String[] args) throws Exception{


        Service service = new Service();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignUpServlet(service)), "/signup");
        context.addServlet(new ServletHolder(new SignInServlet(service)), "/signin");



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
