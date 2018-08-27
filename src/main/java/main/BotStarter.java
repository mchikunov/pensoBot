package main;

import bot.SimpleBot;
import controller.ServletBot;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import util.DbHelper;

import java.net.Authenticator;
import java.net.PasswordAuthentication;


public class BotStarter {
    private static String PROXY_HOST = "zoine.xyz" /* proxy host */;
    private static Integer PROXY_PORT = 9288 /* proxy port */;
    private static String PROXY_USER = "telegram" /* proxy user */;
    private static String PROXY_PASSWORD = "HRwjQLfcV2R63n8h" /* proxy password */;

    public static SimpleBot startBot() throws Exception {
        // Create the Authenticator that will return auth's parameters for proxy authentication
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
            }
        });
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        // Set up Http proxy
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        botOptions.setProxyHost(PROXY_HOST);
        botOptions.setProxyPort(PROXY_PORT);
        // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        SimpleBot tgBot = new SimpleBot(botOptions);
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("bot started");
        return tgBot;
    }
}
