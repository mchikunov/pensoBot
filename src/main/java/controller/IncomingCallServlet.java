package controller;

import com.sun.net.httpserver.HttpServer;
import model.Pensioner;
import service.PensionerService;
import util.SendGetRequestToBot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
            pensionerAddress = "Москва_я_тебя_искал";//pensioner.getAddress();

            try {
             //  SendGetRequestToBot.executeGetRequest("http://054098cc.ngrok.io/servletBot" , "?address=privet");
//                SendGetRequestToBot.executeGetRequest("http://054098cc.ngrok.io/servletBot" , "");
                SendGetRequestToBot.executeGetRequest("http://054098cc.ngrok.io/servletBot" , "?address="+pensionerAddress);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        else {
            pensionerService.addPensioner(new Pensioner(name,lastName,age,address,phoneNumber,comment));
           // pensionerService.addPensioner(new Pensioner("Иван","Иванов","64","Москва улица Ленина 1 кв 1","89063453434","All I need is ..."));
            pensionerAddress = address;
            ///to Eugeny

            try {
                SendGetRequestToBot.executeGetRequest("http://054098cc.ngrok.io/servletBot",pensionerAddress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
