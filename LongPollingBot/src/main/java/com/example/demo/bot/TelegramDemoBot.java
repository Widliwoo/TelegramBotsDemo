package com.example.demo.bot;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramDemoBot extends TelegramLongPollingBot {

    private String botUsername;
    private String botToken;

    public TelegramDemoBot(@Value("${bot.username}") String name, @Value("${bot.token}") String botToken) {
        this.botUsername = name;
        this.botToken = botToken;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMessage = update.getMessage();
            String inText = inMessage.getText();
            if (inText.startsWith("/test")) {
                String[] args = inText.split(" ");
                if (args.length != 2) {
                    sendInvalidCommandMessage(inMessage.getChatId());
                }
                sendValidMessage(inMessage.getChatId(), args[1]);
            } else {
                sendNotCommandMessage(inMessage.getChatId());
            }
        }
    }

    private void sendNotCommandMessage(Long chatId) {
        SendMessage message = new SendMessage();
        message.setText("Я принимаю только команды");
        message.setChatId(chatId.toString());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Чёт поломалось....");
        }
    }

    private void sendValidMessage(Long chatId, String arg) {
        SendMessage message = new SendMessage();
        message.setText(String.format("Команда получена, аргумент %s",arg));
        message.setChatId(chatId.toString());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Чёт поломалось....");
        }
    }

    private void sendInvalidCommandMessage(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("Ошибка команды. Исполнение отменено...");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Чёт поломалось....");
        }
    }
}
