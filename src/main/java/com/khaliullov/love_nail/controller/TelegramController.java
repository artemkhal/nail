package com.khaliullov.love_nail.controller;

import com.khaliullov.love_nail.config.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@Slf4j
public class TelegramController {

    private final TelegramBot telegramBot;

    public TelegramController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }


    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }

}
