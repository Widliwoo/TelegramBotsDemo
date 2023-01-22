package com.example.demo.config;

import com.example.demo.bot.TelegramDemoBot;
import com.example.demo.exception.TelegramBotInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class ApplicationConfig {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramDemoBot bot) {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(bot);
            return api;
        } catch (TelegramApiException e) {
            throw new TelegramBotInitializationException("Failed to register bots in api.", e);
        }
    }

    @Bean
    public TelegramDemoBot telegramDemoBot() {
        return new TelegramDemoBot(botUsername, botToken);
    }

}
