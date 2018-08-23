package servlets;

import service.DBException;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

public class SignUpServlet extends HttpServlet {
    private final Service service;



    public SignUpServlet(Service service) {
        this.service = service;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        List<String> users = null;
        try {
           // users = service.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            response.getWriter().println("Added user id: " + service.addUser(login));
        } catch (DBException e1) {
            e1.printStackTrace();
        }




    }

}
