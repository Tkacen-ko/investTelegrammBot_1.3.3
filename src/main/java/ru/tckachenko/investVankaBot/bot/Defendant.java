package ru.tckachenko.investVankaBot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Defendant {
    public static SendMessage messageHandler(String masegeText, String chatID) throws TelegramApiException {
        switch (masegeText){
            case "/start":
                return messageAndButtons(chatID, StarterKit.MESSAGE, StarterKit.BUTTONS);
            case "Базовые команды":
                return messageAndButtons(chatID, BasicComandKit.MESSAGE, BasicComandKit.BUTTONS);

        }
        return null;
    }
    public static class StarterKit{
        public static final String MESSAGE = "Приветствую тебя пользователь!\n" +
                "Меня зовут InvestGooseBot.\nЯ бот созданный, для облегчения работы с ценными бумагами! "+
                "Я не даю, каких либо советов или прогнозов, а только отображаю статистику, необходимую  "+
                "для принятию быстрого и правильного решения. Выберете необходимое действие бота. "+
                "Кнопка «Базовые команды» - позволяют запросить полезную информацию по акциям, "+
                "представленным на Московской бирже. Кнопка «Подписки» позволит выбрать "+
                "информационную рассылку на случай резкого роиста или падения на бирже:";
        public static final List<String> BUTTONS = Arrays.asList("Базовые команды", "Подписки");
    }
    public static class BasicComandKit{
        public static final String MESSAGE = "Выберете необходимые для отображения данные:";
        public static final List<String> BUTTONS = Arrays.asList("Лидеры роста",
                "Лидеры падения", "TOP Капиталисты", "TickerInfo");
    }

    public static final String LEADERS_GROWTH_AND_DECLINE_TEXT = "Выберите период, за который " +
                                                                   "необходимо получить данные:";
    public static final String TICKERINFO_TEXT = "Отправьте ответным сообщением наименование тикера"+
            "(короткое буквенное обозначение ценное бумаги (Например GAZP, SBER, YNDX)), "+
            "о котором вы хотите получить информацию:";
    public static final String SUBSCRIPTIONS_TEXT = "Выберите необходимое изменение в подписках:";
    public static final String SUBSCRIBE_TEXT = "InvestGooseBot может оповещать вас о резких падениях " +
            "курса акции на московской бирже. Но поскольку акции с различным уровнем капитализации " +
            "(общей суммарной стоимостью) имеют разную волатильность, вам необходимо выбрать " +
            "категорию акций и процент падения для оповещения. Все акции разделены на 3 категории:\n" +
            "Gold – первая десятка компаний по капитализации (Газпром, Сбер, Роснефть).\n" +
            "Silver – компании с 11 по 25 места по уроню капитализации\n" +
            "Bronze - компании с 26 по 50 места по уроню капитализации\n" +
            "Dno – небольшие по капитализации компании\n" +
            "Выберете порог оповещения для каждой из категорий. \n" +
            "Для начала для Gold компаний (отправьте в чат значение " +
            "от 1 до 100 в формате «Gold 6»)):\n";
    public static final String SILVER_TEXT = "Теперь для Silver компаний " +
            "(отправьте в чат значение от 1 до 100 в формате «Silver 11»):";
    public static final String BRONZE_TEXT = "Bronze  компании (отправьте " +
            "в чат значение от 1 до 100 в формате «Bronze 15»):";
    public static final String DNO_TEXT = "И наконец, для Dno компании: " +
            "(отправьте в чат значение от 1 до 100 в формате «Dno 28»)";
    public static final String NOTIFICATION_FREQUENCY_TEXT = "Отлично! Выберете частоту оповещения:\n" +
            "1. Как можно чаще\n" +
            "2. Каждые час\n" +
            "3. Два раза в день (13.00 и 17.00)\n" +
            "4. Каждый вечер (в 19.30)\n";
    public static final String CHANGE_ALERTS_TEXT = "В данном меню вы можете изменить " +
            "порог оповещения по необходимым вам категориям или частоту оповещения."+
            "Напоминаю "+ Defendant.StarterKit.MESSAGE.substring(29);
    public static final String CANCEL_SUBSCRIPTION_TEXT = "Подписка отменена, " +
            "бот больше не будет присылать вам уведомления о падении стоимости на бирже.";
    public static final String UNKNOWN_COMMANDS_TEXT = "Неизвестная команда!\n" +
            "Воспользуйтесь одной перечисленных ниже команд, " +
            "для получения необходимых вам данных:";

    public static final List<String> LEADERS_GROWTH_AND_DECLINE_BUTTON = Arrays.asList( "Сейчас",
            "Неделя", "Месяц", "Год");
    public static final List<String> TICKERINFO_BUTTONS = Arrays.asList("Список тикеров");
    public static final List<String> SUBSCRIPTIONS_BUTTONS = Arrays.asList("Оформить подписку",
            "Изменить оповещения", "Отменить подписку");
    public static final List<String> UNKNOWN_COMMAND_BUTTONS = Arrays.asList("Список команд");

//    public static List<String> textAllButton(){
//        List<String> textAllButton =  new ArrayList<String>();
//        textAllButton.addAll(Defendant.StarterKit.START_BUTTONS);
//        textAllButton.addAll(BASIC_BUTTON_BUTTONS);
//        textAllButton.addAll(LEADERS_GROWTH_AND_DECLINE_BUTTON);
//        textAllButton.addAll(TICKERINFO_BUTTONS);
//        textAllButton.addAll(SUBSCRIPTIONS_BUTTONS);
//        textAllButton.addAll(UNKNOWN_COMMAND_BUTTONS);
//        return textAllButton;
//    }
    private static SendMessage messageAndButtons(String chatI, String introductoryMessage,  List<String> button) throws TelegramApiException {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(addButton(button));
        return SendMessage.builder()
                .text(introductoryMessage)
                .chatId(chatI)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }

    private static List<InlineKeyboardButton> addButton(List<String> mas){
        List <InlineKeyboardButton> but = new ArrayList<>();
        for(String buty : mas){
            but.add(InlineKeyboardButton.builder().text(buty).callbackData(buty).build());
        }
        return but;
    }

}
