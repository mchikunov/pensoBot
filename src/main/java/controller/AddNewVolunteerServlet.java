package controller;

import com.google.gson.Gson;
import model.Volunteer;
import service.VolunteerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewVolunteerServlet {
    public class SignUpServlet extends HttpServlet {
        private final VolunteerService volunteerService;

        public SignUpServlet(VolunteerService volunteerService) {
            this.volunteerService = volunteerService;
        }

        public void doPost(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String age = request.getParameter("age");
            boolean status = true;
            long chatId = 0;


            Volunteer volunteer = new Volunteer(name, lastName, age, tel, status, chatId);
            volunteerService.addVolunteer(volunteer);

            Gson gson = new Gson();

            String json = gson.toJson(volunteer);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);

        }
    }
