package controller;

import bot.SimpleBot;
import model.Pensioner;
import service.PensionerService;
import service.PensionerServiceImpl;
import service.VolunteerService;
import service.VolunteerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class IncomingCallServlet extends HttpServlet {
    private static final PensionerService pensionerService = new PensionerServiceImpl();
    private static final VolunteerService volunteerService = new VolunteerServiceImpl();
    private SimpleBot simpleBot;

    public IncomingCallServlet(SimpleBot simpleBot) {
        this.simpleBot = simpleBot;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String name = req.getParameter("name");
//        String lastName = req.getParameter("lastName");
//        String age = req.getParameter("age");
//        String address = req.getParameter("address");
        System.out.println("Поймал сервлет");
        String phoneNumber = req.getParameter("phone");
        String comment = req.getParameter("comment");
//        String id = req.getParameter("pensionerId");

        Pensioner pensioner = pensionerService.getPensionerByPhone(phoneNumber);

        if (pensioner != null) {
            try {
                //SendGetRequestToBot.executeGetRequest("http://127.0.0.1:8081/servletBot" , String.valueOf(pensioner.getId()));
                pensioner.setWaiting(true);
                pensionerService.updatePensioner(pensioner);
                simpleBot.sendMessageWithLocation(pensioner, comment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        else {
//            pensionerService.addPensioner(new Pensioner(name,lastName,age,address,phoneNumber,comment));
//            try {
//                SendGetRequestToBot.executeGetRequest("http://127.0.0.1:8081/servletBot",
//                        String.valueOf(pensionerService.getPensionerByPhone(phoneNumber).getId()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        ArrayList freeVolunteers = (ArrayList) volunteerService.getAllFreeVolunteers();
        for (int i = 0; i < freeVolunteers.size(); i++) {
            freeVolunteers.get(i);
        }
          //  SimpleBot.sendMessageWithLocation();
        }
}
