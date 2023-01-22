package com.example.demo.exception;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBotInitializationException extends RuntimeException {
    public TelegramBotInitializationException(String message, TelegramApiException e) {
        super(message, e);
    }
}
