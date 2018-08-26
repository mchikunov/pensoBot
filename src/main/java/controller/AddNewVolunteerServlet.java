package controller;

import com.google.gson.Gson;
import model.Volunteer;
import service.VolunteerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewVolunteerServlet extends HttpServlet {
        private final VolunteerService volunteerService;

    public AddNewVolunteerServlet(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
            String name = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String age = request.getParameter("age");
            String phone = request.getParameter("phone");
            long chatId = 0;


            Volunteer volunteer = new Volunteer(name, lastName, age, phone, false, chatId);
            volunteerService.addVolunteer(volunteer);

            Gson gson = new Gson();

            String json = gson.toJson(volunteer);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);

        }
    }

