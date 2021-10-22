package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase.*;

import java.util.*;

public class Defendant {

    public SendMessage batyaReshala(String masegeText, String chatID){
        if (getClassAnswer().get(masegeText)!=null){
            Answer answer = getClassAnswer().get(masegeText);
            return answer.correctAnswer(chatID, answer.getMessage(), answer.getButtons());
        }
        else{
            UnknownCommandsKit unknownCommandsKit = new UnknownCommandsKit();
            return unknownCommandsKit.correctAnswer(chatID, unknownCommandsKit.getMessage(), unknownCommandsKit.getButtons());
        }
    }

    public static final String SILVER_TEXT = "Теперь для Silver компаний " +
            "(отправьте в чат значение от 1 до 100 в формате «Silver-11»):";
    public static final String BRONZE_TEXT = "Bronze  компании (отправьте " +
            "в чат значение от 1 до 100 в формате «Bronze-15»):";
    public static final String DNO_TEXT = "И наконец, для Dno компании: " +
            "(отправьте в чат значение от 1 до 100 в формате «Dno-28»)";
    public static final String NOTIFICATION_FREQUENCY_TEXT = "Отлично! Выберете частоту оповещения:\n" +
            "1. Как можно чаще\n" +
            "2. Каждые час\n" +
            "3. Два раза в день (13.00 и 17.00)\n" +
            "4. Каждый вечер (в 19.30)\n";

    // НЕ ВЫПИЛИВАЙ А ПЕРЕПИШИ ГАВНО НИЖЕ
//    public static final String CHANGE_ALERTS_TEXT = "В данном меню вы можете изменить " +
//            "порог оповещения по необходимым вам категориям или частоту оповещения."+
//            "Напоминаю "+ Defendant.StarterKit.MESSAGE.substring(29);

    public static final String CANCEL_SUBSCRIPTION_TEXT = "Подписка отменена, " +
            "бот больше не будет присылать вам уведомления о падении стоимости на бирже.";
    public static final String UNKNOWN_COMMANDS_TEXT = "Неизвестная команда!\n" +
            "Воспользуйтесь одной перечисленных ниже команд, " +
            "для получения необходимых вам данных:";


    public Map<String, Answer> getClassAnswer(){
        Map<String, Answer> defendant = new HashMap<>();
        defendant.put("/start", new StarterKit());
        defendant.put("Базовые команды", new BasicComandKit());
        defendant.put("Подписки", new SubscriptionsKit());
        defendant.put("Лидеры падения", new SubscriptionsKit());
        defendant.put("Лидеры роста", new LeadersGrowthKit());
        defendant.put("TickerInfo", new TickerInfoKit());
        defendant.put("Оформить подписку", new SubscribeKit());

        defendant.put("TOP-Капиталистов", new PlugKit());
        defendant.put("Растут сейчас", new PlugKit());
        defendant.put("Растут неделю", new PlugKit());
        defendant.put("Растут месяц", new PlugKit());
        defendant.put("Растут год", new PlugKit());
        defendant.put("Падают сейчас", new PlugKit());
        defendant.put("Падают неделю", new PlugKit());
        defendant.put("Падают месяц", new PlugKit());
        defendant.put("Падают год", new PlugKit());
        defendant.put("Изменить оповещения", new PlugKit());
        defendant.put("Отменить подписку", new PlugKit());

        return defendant;
    }

}
