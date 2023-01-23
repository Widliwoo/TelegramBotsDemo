package com.example.demo;

import com.example.demo.bot.TelegramDemoBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {

    public static void main(String[] args) throws TelegramApiException, InterruptedException {
        TelegramDemoBot bot = new TelegramDemoBot(
                "MyMadDemoBot",
                "5703604660:AAHYaJxsj2073aTLdPrPiW7MXlw9IpCeTEg"
        );

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        System.out.println("Bot registered!");
        while (true) {
            System.out.println("Working......");
            Thread.sleep(2000);
        }
    }

}
