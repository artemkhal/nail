package com.khaliullov.love_nail.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

@Component
@Slf4j
public class BotKeyboard {
    private static final Integer FIRST_DAY_OF_MONTH = 1;


    public static LocalDate date;

    public static InlineKeyboardMarkup getCalendarKeyboard(LocalDate date) {
        BotKeyboard.date = date;
        String month = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")).toUpperCase(Locale.ROOT);

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInline.addAll(getMenuKeyboard(month));
        rowsInline.addAll(getDaysOfWeekKeyboard());
        rowsInline.addAll(getDaysOfMonthKeyboard(date));
        inlineKeyboard.setKeyboard(rowsInline);
//
        return inlineKeyboard;
    }
    private static List<List<InlineKeyboardButton>> getMenuKeyboard(String month){
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton prev = new InlineKeyboardButton();
        InlineKeyboardButton monthBtn = new InlineKeyboardButton();
        InlineKeyboardButton next = new InlineKeyboardButton();
        prev.setText("<<");
        prev.setCallbackData("/prev");
        monthBtn.setText(month);
        monthBtn.setCallbackData("/month:" + month);
        next.setText(">>");
        next.setCallbackData("/next");
        rowInline.add(prev);
        rowInline.add(monthBtn);
        rowInline.add(next);
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        rowsList.add(rowInline);

        return rowsList;
    }
    private static List<List<InlineKeyboardButton>> getDaysOfWeekKeyboard(){
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        List<InlineKeyboardButton> daysOfWeekRow = new ArrayList<>();
        InlineKeyboardButton btn;
        for(int i = 1; i <= 7; i++){
            String dayOfWeek = DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("ru")).toUpperCase();
            btn = new InlineKeyboardButton();
            btn.setText(dayOfWeek);
            btn.setCallbackData("/presDayOfWeek:" + dayOfWeek);
            daysOfWeekRow.add(btn);
        }

        rowsList.add(daysOfWeekRow);
        return rowsList;
    }

    private static List<List<InlineKeyboardButton>> getDaysOfMonthKeyboard(LocalDate date) {
        List<List<InlineKeyboardButton>> daysRows = new ArrayList<>();
        List<InlineKeyboardButton> daysRow = new ArrayList<>();


        int monthLength = date.getMonth().minLength();
        for (int i = FIRST_DAY_OF_MONTH; i <= monthLength; i++){

            for (int dayOfWeek = 1; dayOfWeek <= 7; dayOfWeek++){
                if (i == FIRST_DAY_OF_MONTH && LocalDate.of(date.getYear(), date.getMonth(), i).getDayOfWeek().getValue() > dayOfWeek){
                    InlineKeyboardButton btn = new InlineKeyboardButton();
                    btn.setCallbackData("/old");
                    btn.setText("-");
                    daysRow.add(btn);
                }
            }
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setCallbackData("/" + LocalDate.of(date.getYear(), date.getMonth(), i));
            btn.setText(Integer.toString(i));
            daysRow.add(btn);
            if (daysRow.size() >= 7){
                daysRows.add(daysRow);
                daysRow = new ArrayList<>();
            }



        }
        while (daysRow.size() < 7 && daysRow.size() >0){
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText("-");
            btn.setCallbackData("/nextMonth");
            daysRow.add(btn);
        }
        daysRows.add(daysRow);
        return daysRows;
    }
}
