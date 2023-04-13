package com.khaliullov.love_nail.service;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;

//import java.time.LocalDate;
//import java.time.Period;
//import java.util.*;
//
//public class TelegramCalendar {
//    private static final String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
//    private static final String[] DAYS_OF_WEEK = {"Понидельник", "Втроник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
//    private static final String PREV_BUTTON = "<<";
//    private static final String NEXT_BUTTON = ">>";
//    private static final String MIDDLE_BUTTON_DAY = "{month} {year}";
//    private static final String MIDDLE_BUTTON_MONTH = "{year}";
//    private static final String MIDDLE_BUTTON_YEAR = " ";
//    private static final String BACK_TO_BUTTON = "<<< {name}";
//    private static final String EMPTY_NAV_BUTTON = "×";
//    private static final String EMPTY_DAY_BUTTON = " ";
//    private static final String EMPTY_MONTH_BUTTON = " ";
//    private static final String EMPTY_YEAR_BUTTON = " ";
//    private static final int SIZE_YEAR = 2;
//    private static final int SIZE_YEAR_COLUMN = 2;
//    private static final int SIZE_MONTH = 3;
//    private static final int SIZE_DAY = 7;
//    private static final int SIZE_ADDITIONAL_BUTTONS = 2;
//    private Object _keyboard;
//    private Object step;
//
//    public TelegramCalendar(int calendarId, Date currentDate, String[] additionalButtons, String locale, Date minDate, Date maxDate, boolean telethon, boolean isRandom, Object kwargs) {
//        if (currentDate == null) {
//            currentDate = LocalDate.now();
//        }
//        if (minDate == null) {
//            minDate = LocalDate.of(1, 1, 1);
//        }
//        if (maxDate == null) {
//            maxDate = LocalDate.of(2999, 12, 31);
//        }
//
//        public void setCalendarId(String calendarId) {
//            this.calendarId = calendarId;
//        }
//
//        public void setCurrentDate(Date currentDate) {
//            this.currentDate = currentDate;
//        }
//
//        public void setLocale(Locale locale) {
//            this.locale = locale;
//        }
//
//        public void setMinDate(Date minDate) {
//            this.minDate = minDate;
//        }
//
//        public void setMaxDate(Date maxDate) {
//            this.maxDate = maxDate;
//        }
//
//        public void setTelethon(boolean telethon) {
//            this.telethon = telethon;
//            if (this.telethon && !TELETHON_INSTALLED) {
//                throw new ImportError("Telethon is not installed. Please install telethon or use pip install python-telegram-bot-calendar[telethon]");
//            }
//        }
//
//        public void setIsRandom(boolean isRandom) {
//            this.isRandom = isRandom;
//        }
//
//        public void setAdditionalButtons(List<Button> additionalButtons) {
//            if (additionalButtons == null) {
//                additionalButtons = new ArrayList<>();
//            }
//            this.additionalButtons = rows(additionalButtons, this.sizeAdditionalButtons);
//        }
//
//        public void setPrevButtonYear(Button prevButtonYear) {
//            this.prevButtonYear = prevButtonYear;
//        }
//
//        public void setNextButtonYear(Button nextButtonYear) {
//            this.nextButtonYear = nextButtonYear;
//        }
//
//        public void setPrevButtonMonth(Button prevButtonMonth) {
//            this.prevButtonMonth = prevButtonMonth;
//        }
//
//        public void setNextButtonMonth(Button nextButtonMonth) {
//            this.nextButtonMonth = nextButtonMonth;
//        }
//
//        public void setPrevButtonDay(Button prevButtonDay) {
//            this.prevButtonDay = prevButtonDay;
//        }
//
//        public void setNextButtonDay(Button nextButtonDay) {
//            this.nextButtonDay = nextButtonDay;
//        }
//
//        public void setNavButtons() {
//            this.navButtons = new HashMap<>();
//            this.navButtons.put("YEAR", Arrays.asList(this.prevButtonYear, this.middleButtonYear, this.nextButtonYear));
//            this.navButtons.put("MONTH", Arrays.asList(this.prevButtonMonth, this.middleButtonMonth, this.nextButtonMonth));
//            this.navButtons.put("DAY", Arrays.asList(this.prevButtonDay, this.middleButtonDay, this.nextButtonDay));
//        }
//    }
//
//    public static boolean func(int calendar_id, boolean telethon) {
//        boolean inn(String callback) {
//            String start = CB_CALENDAR + "_" + Integer.toString(calendar_id);
//            return telethon ? callback.decode("utf-8").startswith(start) : callback.data.startswith(start);
//        }
//        return inn;
//    }
//
//    public void build(self) {
//        if (this._keyboard == null) {
//            this._build();
//        }
//        return this._keyboard, this.step;
//    }
//
//    public void process(call_data) {
//        return this._process(call_data);
//    }
//
//    private void _build(Object[] args, Map<String, Object> kwargs) {
//        // Build the keyboard and set _keyboard.
//    }
//
//    private void _process(Object call_data, Object[] args, Map<String, Object> kwargs) {
//        // callback data
//        // return (result, keyboard, message); if no result: result = None
//        // TODO: implement
//    }
//
//    private String _build_callback(String action, String step, Date data, Object[] args, boolean is_random, Map<String, Object> kwargs) {
//        String[] params;
//        if (action.equals("NOTHING")) {
//            params = new String[] {CB_CALENDAR, String.valueOf(this.calendar_id), action};
//        } else {
//            String[] dataStr = new String[3];
//            for (int i = 0; i < 3; i++) {
//                dataStr[i] = String.valueOf(data.timetuple()[i]);
//            }
//            params = new String[] {CB_CALENDAR, String.valueOf(this.calendar_id), action, step};
//            params = ArrayUtils.addAll(params, dataStr);
//        }
//
//        // Random is used here to protect bots from being spammed by some 'smart' users.
//        // Random callback data will not produce api errors "Message is not modified".
//        // However, there is still a chance (1 in 1e18) that the same callbacks are created.
//        String salt = "_" + String.valueOf(Math.random() * 1e18) : "");
//        if (is_random) {
//            salt = "_" + String.valueOf(Math.random() * 1e18);
//        }
//
//        return String.join("_", params) + salt;
//    }
//
//    ublic Object buildButton(String text, String action, String step, String data, boolean isRandom, Object... kwargs) {
//        if (telethon) {
//            return Button.inline(text=str(text), data=buildCallback(action, step, data, isRandom));
//        } else {
//            return {
//                    'text': text,
//                    'callback_data': buildCallback(action, step, data, isRandom)
//            };
//        }
//    }
//
//    public Object buildKeyboard(Object[] buttons) {
//        if (telethon) {
//            return buttons;
//        }
//        return buildJsonKeyboard(buttons);
//    }
//
//    public String buildJsonKeyboard(Object[] buttons) {
//        return json.dumps({"inline_keyboard": buttons + additionalButtons});
//    }
//
//    public boolean _valid_date(Date d) {
//        return this.min_date <= d && d <= this.max_date;
//    }
//
//    public List<Date> _get_period(String step, Date start, int diff, Object[] args, Map<String, Object> kwargs) {
//    /*
//    Used for getting period of dates with a given step, start date and difference.
//    It allows to create empty dates if they are not in the given range.
//    */
//        String lstep = LSTEP.get(step) + "s";
//        List<Date> dates = new ArrayList<>();
//
//        int empty_before = 0;
//        int empty_after = 0;
//
//        for (int i = 0; i < diff; i++) {
//            Date n_date = start.plus(Period.of(0, 0, i));
//            if (this.min_date > max_date(n_date, step)) {
//                empty_before++;
//            } else if (this.max_date < min_date(n_date, step)) {
//                empty_after++;
//            } else {
//                dates.add(n_date);
//            }
//        }
//        List<Date> result = new ArrayList<>();
//        for (int i = 0; i < empty_before; i++) {
//            result.add(null);
//        }
//        result.addAll(dates);
//        for (int i = 0; i < empty_after; i++) {
//            result.add(null);
//        }
//        return result;
//    }
//
//    public List<List<Object>> rows(List<Object> buttons, int row_size) {
//    /*
//    Build rows for the keyboard. Divides list of buttons to list of lists of buttons.
//    */
//        List<List<Object>> result = new ArrayList<>();
//        for (int i = 0; i < Math.max(buttons.size() - row_size, 0) + 1; i += row_size) {
//            result.add(buttons.subList(i, i + row_size));
//        }
//        return result;
//    }
//
//    public static Calendar maxDate(Calendar d, int step) {
//        // Returns the "biggest" possible date for a given step.
//        // It is used for navigations buttons when it is needed to check if prev/next page exists.
//        if (step == Calendar.YEAR) {
//            d.set(Calendar.MONTH, Calendar.DECEMBER);
//            d.set(Calendar.DAY_OF_MONTH, 31);
//            return d;
//        } else if (step == Calendar.MONTH) {
//            d.set(Calendar.DAY_OF_MONTH, d.getActualMaximum(Calendar.DAY_OF_MONTH));
//            return d;
//        } else {
//            return d;
//        }
//    }
//
//    public static Calendar minDate(Calendar d, int step) {
//        if (step == Calendar.YEAR) {
//            d.set(Calendar.MONTH, Calendar.JANUARY);
//            d.set(Calendar.DAY_OF_MONTH, 1);
//            return d;
//        } else if (step == Calendar.MONTH) {
//            d.set(Calendar.DAY_OF_MONTH, 1);
//            return d;
//        } else {
//            return d;
//        }
//    }
//
//}