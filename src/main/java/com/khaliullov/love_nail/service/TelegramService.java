package com.khaliullov.love_nail.service;

import com.khaliullov.love_nail.entity.Client;
import com.khaliullov.love_nail.entity.Order;
import com.khaliullov.love_nail.repo.ClientRepository;
import com.khaliullov.love_nail.repo.MasterRepository;
import com.khaliullov.love_nail.repo.OrderRepository;
import com.khaliullov.love_nail.telegram.BotKeyboard;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.time.LocalDate;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class TelegramService {

    String courseText = "Привет, это Люба! Рада видеть тебя здесьU+263A";

    @Autowired
    MasterRepository masterRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;

    public BotApiMethod<?> handleUpdate(Update update) {
        long chatId;
        Order order = null;

        if (update.hasCallbackQuery()){
            chatId = update.getCallbackQuery().getMessage().getChatId();
            log.info(update.getCallbackQuery().getData());
            String data = update.getCallbackQuery().getData();
//            return sendMessage(chatId, "hihi", null);
            switch (data){
                case "/prev":
                {
                    EditMessageReplyMarkup x = new EditMessageReplyMarkup();
                    x.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    x.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    x.setReplyMarkup(BotKeyboard.getCalendarKeyboard(BotKeyboard.date.minusMonths(1)));
                    return x;
                }
                case "/next":
                {
                    EditMessageReplyMarkup x = new EditMessageReplyMarkup();
                    x.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    x.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    x.setReplyMarkup(BotKeyboard.getCalendarKeyboard(BotKeyboard.date.plusMonths(1)));
                    return x;

                }

            }
            return sendMessage(chatId, "hih", null);
        }

        if (update.hasEditedMessage()){
            log.info("edited message");
        }
        chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if (text.matches("/start\s[0-9]+")) {
            log.info(text);
            long secretKey = Long.parseLong(text.split("\s")[1]);
            order = orderRepository.findBySecretKey(secretKey);
            Client client = clientRepository.findByChatId(chatId);
            if (order != null) {
                //потом удалить:
                if (client == null) {
                    client = new Client(order.getName(), order, chatId);
                    clientRepository.save(client);
                }
                order.setClient(client);
                orderRepository.save(order);
            }
            if (order.getTime() != null && order.getDate() != null) {

            }else {
                return sendMessage(chatId, "Давай выберем время когда тебе будет удобно прийти?", BotKeyboard.getCalendarKeyboard(LocalDate.now()));
            }


        }
        if (text.startsWith("/start\scourse")){
            sendMessage(chatId, courseText, null);

        }





        LocalDate date = LocalDate.now();
        return sendMessage(chatId, "unknown command", BotKeyboard.getCalendarKeyboard(date));
    }

    private SendMessage sendMessage(Long chatId, String text, BotApiObject object) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(text);
        sm.setReplyMarkup((ReplyKeyboard) object);
        return sm;
    }

}