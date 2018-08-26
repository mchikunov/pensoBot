package controller;

import model.Pensioner;
import service.PensionerService;
import util.SendGetRequestToBot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IncomingCallServlet extends HttpServlet {
    private PensionerService pensionerService;



    public IncomingCallServlet(PensionerService pensionerService) {
        this.pensionerService = pensionerService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pensionerAddress = null;
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String comment = req.getParameter("comment");


        Pensioner pensioner = pensionerService.getPensionerByPhone(phoneNumber);
        if (pensioner != null) {
            try {
                SendGetRequestToBot.executeGetRequest("http://127.0.0.1:8081/servletBot" , String.valueOf(pensioner.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            pensionerService.addPensioner(new Pensioner(name,lastName,age,address,phoneNumber,comment));
            try {
                SendGetRequestToBot.executeGetRequest("http://127.0.0.1:8081/servletBot",
                        String.valueOf(pensionerService.getPensionerByPhone(phoneNumber).getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
