package bot;

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

    private static final VolunteerService volunteerService = new VolunteerServiceImpl();
    private static final PensionerService pensionerService = new PensionerServiceImpl();


    private static Map<Long, String> contex = new HashMap<>();

    public SimpleBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    public void sendMessageWithLocation(Pensioner pensioner, String comment) {
        SendMessage msg = new SendMessage().setText("Необходима помощь: " + comment);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Принять").setCallbackData("accept#" + pensioner.getId()));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        msg.setReplyMarkup(markupInline);

        String[] location;
        try {
            //TODO расскоментить на норм локацию
//            location = TransformAddress.getLatLongPositions(pensioner.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Location не получен пропускаем");
            return;
        }

//        float lat = Float.valueOf(location[0]);
//        float lng = Float.valueOf(location[1]);
        float lat = 59.942885F;
        float lng = 30.257503F;

        try {
            SendLocation sendLocation = new SendLocation(lat, lng);

            List<Volunteer> volunteers = volunteerService.getAllFreeVolunteers();
            for (Volunteer volunteer : volunteers) {
                System.out.println("id volunteer " + volunteer.getChatId());
                sendLocation.setChatId(volunteer.getChatId());
                msg.setChatId(volunteer.getChatId());
                execute(sendLocation);
                execute(msg);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendCanceledMessage(Volunteer volunteer) {
        SendMessage msg = new SendMessage().setText("Волонтёр найден! Спасибо за вашу помощь, ожидайте новых заявок!");
        try {
            List<Volunteer> volunteers = volunteerService.getAllFreeVolunteers();
            volunteers.remove(volunteer);
            for (Volunteer v : volunteers) {
                System.out.println("id volunteer " + v.getChatId());
                msg.setChatId(v.getChatId());
                execute(msg);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String msg, long chatId) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(msg);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageFromUser = update.getMessage().getText();

            if (messageFromUser.equals("/start")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Добро пожаловать в приложение  <Помоги Пенсионеру>  Зарегистрируйтесь, если вы зашли первый раз");

                if (volunteerService.getVolunteerByChatId(chatId) == null) {
                    rowInline.add(new InlineKeyboardButton().setText("Регистрация").setCallbackData("registration"));
                } else {
                    rowInline.add(new InlineKeyboardButton().setText("Онлайн").setCallbackData("online"));
                    rowInline.add(new InlineKeyboardButton().setText("Офлайн").setCallbackData("offline"));
                }

                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
            } else if (contex.get(chatId).equals("registration")) {
                String[] params = messageFromUser.split(" ");
                Volunteer volunteer = new Volunteer(params[0], params[1], params[2], true, chatId);
                volunteerService.addVolunteer(volunteer);
                contex.remove(chatId);

                SendMessage msg = new SendMessage()
                        .setText("Поздравляем Вы зарегистрированы!")
                        .setReplyMarkup(markupInline)
                        .setChatId(chatId);
                rowInline.add(new InlineKeyboardButton().setText("Онлайн").setCallbackData("online"));
                rowInline.add(new InlineKeyboardButton().setText("Офлайн").setCallbackData("offline"));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            long chatId = callbackQuery.getFrom().getId();
            String button = callbackQuery.getData();
            if (button.equals("registration")) {
                SendMessage msg = new SendMessage()
                        .setText("Введите ваше Имя Фамилию возвраст, через пробел")
                        .setChatId(chatId)
                        .setReplyMarkup(markupInline);
                contex.put(chatId, "registration");
                try {
                    execute(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


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
            } else if (button.startsWith("accept")) {
                int pensionerId = Integer.parseInt(button.split("#")[1]);
                Pensioner pensioner = pensionerService.getPensioner(pensionerId);
                if (pensioner.isWaiting()) {
                    pensioner.setWaiting(false);
                    pensionerService.updatePensioner(pensioner);
                    Volunteer volunteer = volunteerService.getVolunteerByChatId(chatId);
                    sendMessage("Спасибо за ваш отклик! Вас ждут!", chatId);
                    sendCanceledMessage(volunteer);
                } else {
                    sendMessage("Спасибо, но эту заявку уже забрали :(", chatId);
                }
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