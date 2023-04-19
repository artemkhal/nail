package com.khaliullov.love_nail.telegram;

import com.khaliullov.love_nail.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.*;
import java.time.format.TextStyle;
import java.util.*;

@Component
@Slf4j
public class BotKeyboard extends InlineKeyboardMarkup {
    private static final Integer FIRST_DAY_OF_MONTH = 1;

    public static InlineKeyboardMarkup getCalendarKeyboard(LocalDate date) {

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInline.addAll(getMenuKeyboard(date));
        rowsInline.addAll(getDaysOfWeekKeyboard());
        rowsInline.addAll(getDaysOfMonthKeyboard(date));
        inlineKeyboard.setKeyboard(rowsInline);

        return inlineKeyboard;
    }

    public static InlineKeyboardMarkup getYNKeyboard(String updateData){
        InlineKeyboardMarkup ikm = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();

        for (int i = 0; i < 2; i++){
            InlineKeyboardButton btn = new InlineKeyboardButton();
            if (i > 0){
                btn.setText("NO");
                btn.setCallbackData(updateData + "NO");
            } else{
                btn.setText("YES");
                btn.setCallbackData(updateData + "YES");
            }
            keyboardButtons.add(btn);
        }
        rowsList.add(keyboardButtons);
        ikm.setKeyboard(rowsList);
        return ikm;
    }
    private static List<List<InlineKeyboardButton>> getMenuKeyboard(LocalDate date){

        String month = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")).toUpperCase(Locale.ROOT);
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton prev = new InlineKeyboardButton();
        InlineKeyboardButton monthBtn = new InlineKeyboardButton();
        InlineKeyboardButton next = new InlineKeyboardButton();
        prev.setText("<<");
        prev.setCallbackData("/prev:" + date);
        monthBtn.setText(month);
        monthBtn.setCallbackData("/month:" + month);
        next.setText(">>");
        next.setCallbackData("/next:" + date);
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
            btn.setCallbackData("/selectedDate:" + LocalDate.of(date.getYear(), date.getMonth(), i));
            btn.setText(Integer.toString(i));
            daysRow.add(btn);
            if (daysRow.size() >= 7){
                daysRows.add(daysRow);
                daysRow = new ArrayList<>();
            }
        }
        while (daysRow.size() > 0 && daysRow.size() < 7){
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText("-");
            btn.setCallbackData("/nextMonth");
            daysRow.add(btn);
        }
        daysRows.add(daysRow);
        return daysRows;
    }

    public static InlineKeyboardMarkup getTimeKeyboard(LocalDate selectedDate) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowButton = new ArrayList<>();

        InlineKeyboardButton back = new InlineKeyboardButton();
        back.setText("<<BACK");
        back.setCallbackData("/backDate:" + selectedDate);
        rowButton.add(back);

        InlineKeyboardButton date = new InlineKeyboardButton();
        date.setText(selectedDate.toString());
        date.setCallbackData("/date:" + selectedDate);
        rowButton.add(date);

        InlineKeyboardButton next = new InlineKeyboardButton();
        next.setText("NEXT>>");
        next.setCallbackData("/nextDate:" + selectedDate);
        rowButton.add(next);

        rowsInline.add(rowButton);
        rowButton = new ArrayList<>();

        rowsInline.addAll(timeKeyboardBuilder(selectedDate));

        InlineKeyboardButton calendar = new InlineKeyboardButton();
        calendar.setText("Назад в календарь");
        calendar.setCallbackData("/calendar:" + selectedDate.toString());
        rowButton.add(calendar);
        rowsInline.add(rowButton);


        inlineKeyboard.setKeyboard(rowsInline);
        return inlineKeyboard;
    }

    private static List<List<InlineKeyboardButton>> timeKeyboardBuilder(LocalDate selectedDate){
        List<InlineKeyboardButton> btnList = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
        InlineKeyboardButton btn = null;

        for (LocalTime i = LocalTime.of(8, 0); i.isBefore(LocalTime.of(17, 0)); i = i.plusMinutes(30)){
            btn = new InlineKeyboardButton();
            btn.setText(i.toString());
            btn.setCallbackData("/selectedDate:" + selectedDate + "&selectedTime:" + i);
            btnList.add(btn);
            if (btnList.size() == 3){
                rowsList.add(btnList);
                btnList = new ArrayList<>();
            }
        }


        rowsList.add(btnList);
        return rowsList;


    }

}
