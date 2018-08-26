package bot;

import dao.VolunteerDAOImpl;
import model.Pensioner;
import model.Volunteer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.PensionerService;
import service.PensionerServiceImpl;
import service.VolunteerService;
import service.VolunteerServiceImpl;
import util.TransformAddress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBot extends TelegramLongPollingBot {

    private static  final VolunteerService volunteerService = new VolunteerServiceImpl();

    private static  final PensionerService pensionerService = new PensionerServiceImpl();

    private Map<Long, String> contex = new HashMap<>();

    public SimpleBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }


    public SimpleBot() {
        super();
    }

    public void sendMessageWithLocation(long id , long chatId) {
//        SendMessage message = new SendMessage() // Create a message object object
//                .setChatId(chatId)
//                .setText(msg);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Принять заявку").setCallbackData("accept"));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
//        message.setReplyMarkup(markupInline);

        String adress = pensionerService.getPensioner(chatId).getAddress();
        String[] location = new String[1];
        try {
            location = TransformAddress.getLatLongPositions(adress);


        } catch (Exception e) {
            e.printStackTrace();
        }

        float lat = Float.valueOf(location[0]);
        float lng = Float.valueOf(location[1]);
        try {

           // - этот код отправляет геопозицию

           //Float lat = 59.9418720f, lng = 30.2655820f;
            SendLocation sendLocation = new SendLocation(lat, lng);
            sendLocation.setChatId(chatId);
            // - конец кода

            execute(sendLocation);
            //execute(); // Call method to send the message
        } catch (TelegramApiException e) {
           e.printStackTrace();
       }
    }

    public void sendMessage(String msg, long chatId) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(msg);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String messageFromUser = update.getMessage().getText();

            if (messageFromUser.equals("/start")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Добро пожаловать в приложение  <Помоги Пенсионеру>  Зарегистрируйтесь, если вы зашли первый раз");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                if (volunteerService.getVolunteerByChatId(chatId) == null) {
                    rowInline.add(new InlineKeyboardButton().setText("Регистрация").setCallbackData("registration"));
                } else {
                    rowInline.add(new InlineKeyboardButton().setText("Онлайн").setCallbackData("online"));
                    rowInline.add(new InlineKeyboardButton().setText("Офлайн").setCallbackData("offline"));
                }


                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);


//                try {
//                    execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }

                try {
                    // - этот код отправляет геопозицию
//                    Float lat = 59.9418720f, lng = 30.2655820f;
//                    SendLocation sendLocation = new SendLocation(lat, lng);
//                    sendLocation.setChatId(chatId);
//                    // - конец кода
//
//                    execute(sendLocation);
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (contex.get(chatId).equals("registration")) {
                String[] params = messageFromUser.split(" ");
                Volunteer volunteer = new Volunteer(params[0], params[1], params[2], true, chatId);
                volunteerService.addVolunteer(volunteer);
                contex.remove(chatId);
                sendMessage("Поздравляем Вы зарегистрированы! Ваш", chatId);
                new InlineKeyboardButton().setText("Онлайн").setCallbackData("online");
                new InlineKeyboardButton().setText("Офлайн").setCallbackData("offline");
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            long chatId = callbackQuery.getFrom().getId();
            String button = callbackQuery.getData();
            if (button.equals("registration") ) {

                sendMessage("Введите ваше Имя Фамилию возвраст, через пробел", chatId);
                contex.put(chatId, "registration");

            } else if (button.equals("online")) {
                Volunteer user = volunteerService.getVolunteerByChatId(chatId);
                user.setStatus(true);
                volunteerService.updateVolunteer(user);
                sendMessage("Вы онлайн, ожидайте заявку", chatId);
            } else if (button.equals("offline")) {
                Volunteer user = volunteerService.getVolunteerByChatId(chatId);
                user.setStatus(false);
                volunteerService.updateVolunteer(user);
                sendMessage("Вы отключены от системы, спасибо! Не забывайте пенсионерам нужна Ваша помощь!! Ждем вашего возвращения. ", chatId);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Pensooo_bot";
    }

    @Override
    public String getBotToken() {
        return "692766715:AAGtc_qrrSs-0YIH4H8cnvX_Fl8WXA6jK8A";
    }

}