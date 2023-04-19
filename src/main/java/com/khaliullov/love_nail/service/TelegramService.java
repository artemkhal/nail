package com.khaliullov.love_nail.service;

import com.khaliullov.love_nail.entity.Client;
import com.khaliullov.love_nail.entity.Master;
import com.khaliullov.love_nail.entity.Order;
import com.khaliullov.love_nail.repo.ClientRepository;
import com.khaliullov.love_nail.repo.MasterRepository;
import com.khaliullov.love_nail.repo.OrderRepository;
import com.khaliullov.love_nail.telegram.BotKeyboard;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class TelegramService {

    EditMessageReplyMarkup editMessageReplyMarkup;
    SendMessage sendMessage;
    long masterId = 0;

    Update update;

    final String COURSE_TEXT = "Привет! Рада видеть тебя здесь";

    @Autowired
    MasterRepository masterRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    OrderRepository orderRepository;

    public BotApiMethod<?> handleUpdate(Update update) {
        this.update = update;
        long chatId;
        Order order = null;

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            log.info(update.getCallbackQuery().getData());
            String data = update.getCallbackQuery().getData();

            if (data.startsWith("/prev:")) {
                return sendEditedMessage(BotKeyboard.getCalendarKeyboard(getSelectedDate(data).minusMonths(1)));
            }
            if (data.startsWith("/next:")) {
                return sendEditedMessage(BotKeyboard.getCalendarKeyboard(getSelectedDate(data).plusMonths(1)));
            }
            if (data.startsWith("/backDate:")) {
                return sendEditedMessage(BotKeyboard.getTimeKeyboard(getSelectedDate(data).minusDays(1)));
            }
            if (data.startsWith("/nextDate:")) {
                return sendEditedMessage(BotKeyboard.getTimeKeyboard(getSelectedDate(data).plusDays(1)));
            }
            if (data.startsWith("/calendar:")) {
                return sendEditedMessage(BotKeyboard.getCalendarKeyboard(getSelectedDate(data)));
            }
            if (data.startsWith("/selectedDate:")) {
                if (getSelectedDate(data).isAfter(LocalDate.now()) || getSelectedDate(data).isEqual(LocalDate.now())) {
                    return sendEditedMessage(BotKeyboard.getTimeKeyboard(getSelectedDate(data)));
                } else return sendMessage(chatId, "Неверно, выбери дату позже " + dateFormat(LocalDate.now()), null);
            }
            if (data.startsWith("/renameMaster:")) {
                String text = data.split(":")[1];
                if (text.equals("YES")) {
                    masterId = chatId;
                    return sendMessage(chatId, "Введите ваше имя", null);
                }else return sendMessage(chatId, "OK", null);
            }
            if (data.startsWith("/old") || data.startsWith("/nextMonth")
                    || data.startsWith("/month:") || data.matches("/presDayOfWeek:[А-Я]+")
                    || data.matches("/date:\\d{4}-\\d{2}-\\d{2}") || data.matches("/-")) {
                return sendMessage(chatId, "", null);
            }
            if (data.startsWith("/newOrder")){
                String dat[] = data.split(":");
                String meta[] = dat[0].split("&");
                if (dat[1].equals("YES")){
                    return sendMessage(Long.parseLong(meta[1]), "Вы записаны на " + meta[2], null);
                }else return sendMessage(Long.parseLong(meta[1]), "Мастер не согласова дату, давайте выберем другое время:",
                        BotKeyboard.getCalendarKeyboard(LocalDate.now()));
            }
        }

        if (update.hasEditedMessage()) {
            log.info("edited message");
            return sendMessage(update.getEditedMessage().getChatId(), "edited message", null);
        }
        chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if (text.matches("/start\s[0-9]+")) {
            log.info(text);
            long secretKey = Long.parseLong(text.split("\s")[1]);
            order = orderRepository.findBySecretKey(secretKey);
            Client client = clientRepository.findByChatId(chatId);
            if (order != null) {
                if (client == null) {
                    client = new Client(order.getName(), order, chatId);
                } else client.getOrders().add(order);
                clientRepository.save(client);
                order.setClient(client);
                orderRepository.save(order);
            } else return sendMessage(chatId, "Произошла ошибка, попробуй повторить позже", null);
            if (order.getTime() != null && order.getDate() != null) {
                return sendMessage(order.getMaster().getChatId(),
                        "Новая запись" + dateFormat(order.getDate()) + " в " + order.getTime(),
                        BotKeyboard.getYNKeyboard("/newOrder&" + chatId + "&" + LocalDateTime.of(order.getDate(), order.getTime()) + ":"));
            } else {
                return sendMessage(chatId, "Давай выберем время когда тебе будет удобно прийти?", BotKeyboard.getCalendarKeyboard(LocalDate.now()));
            }
        }

        if (text.matches("/registration")) {
            Master master = masterRepository.findMasterByChatId(chatId);
            if (master != null) {
                return sendMessage(chatId, "Вы уже зарегистрированы под именем " + master.getName() + "\n Заменить?",
                        BotKeyboard.getYNKeyboard("/renameMaster:"));
            } else {
                masterId = chatId;
                return sendMessage(chatId, "Введите ваше имя", null);
            }

        }
        if (masterId == chatId) {
            masterRepository.save(new Master(text, chatId));
            masterId = 0;
            return sendMessage(chatId,"Вы зарегистрированы под именем " + text,null);
        }

        if (text.matches("/start\scourse")) {
            return sendMessage(chatId, COURSE_TEXT, null);
        }

        return
                sendMessage(chatId, "unknown command", null);
    }

    private LocalDate getSelectedDate(String data) {
        String date[] = data.split(":")[1].split("-");
        return LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }

    private BotApiMethod<?> sendEditedMessage(InlineKeyboardMarkup keyboard) {
        editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMessageReplyMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessageReplyMarkup.setReplyMarkup(keyboard);
        return editMessageReplyMarkup;
    }

    private SendMessage sendMessage(Long chatId, String text, BotApiObject object) {
        sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup((ReplyKeyboard) object);
        return sendMessage;
    }

    private String dateFormat(LocalDate date) {
        return date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru"))
                + " " + date.getYear() + " (" + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")) + ")";
    }


}