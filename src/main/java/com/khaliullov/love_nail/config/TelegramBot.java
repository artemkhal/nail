package com.khaliullov.love_nail.config;


import com.khaliullov.love_nail.service.TelegramService;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Getter
@Setter
public class TelegramBot extends SpringWebhookBot {
    private String botPath;
    private String botUsername;
    private String botToken;

    private TelegramService telegramService;

    public TelegramBot(TelegramService telegramService, DefaultBotOptions options, SetWebhook setWebhook) {
        super(options, setWebhook);
        this.telegramService = telegramService;
    }
    public TelegramBot(TelegramService telegramService, SetWebhook setWebhook) {
        super(setWebhook);
        this.telegramService = telegramService;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramService.handleUpdate(update);
    }
}