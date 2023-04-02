package com.khaliullov.love_nail.service.tgbot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TelegramBotConfig {
    String webHookPath = "https://c8a2-37-147-54-76.eu.ngrok.io";
    String userName = "@LoveNailBot";
    String botToken = "5608583424:AAHORxK53F2CP6QQvXIhWWVIIATUv9mlHek";


}
