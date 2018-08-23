package servlets;

import service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final Service service;

    public SignInServlet(Service service) {
        this.service = service;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

//        try {
//            if(service.getUser(login))
//            {
//                response.getWriter().println("Autalrizate: " + login);
//            }
//        else{response.getWriter().println("Unautalrizate: " + login);}
//        } catch (DBException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }


}
