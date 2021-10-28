package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase.*;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

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
        List<TiketInformation> getNameTiker = new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).getDataToTikerRealTime();
        Map<String, Answer> defendant = new HashMap<>();
        defendant.put("/start", new StarterKit());
        defendant.put("базовые команды", new BasicComandKit());
        defendant.put("подписки", new SubscriptionsKit());
        defendant.put("лидеры падения", new SubscriptionsKit());
        defendant.put("лидеры роста", new LeadersGrowthKit());
        defendant.put("tickerinfo", new TickerInfoKit());
        defendant.put("список тикеров", new ListTikersKit(getNameTiker));
        defendant.put("оформить подписку", new SubscribeKit());
        defendant.put("top50-капиталистов", new RatingCapitalistsKit());
        defendant.put("растут сейчас", new PlugKit());
        defendant.put("растут неделю", new PlugKit());
        defendant.put("растут месяц", new PlugKit());
        defendant.put("растут год", new PlugKit());
        defendant.put("падают сейчас", new PlugKit());
        defendant.put("падают неделю", new PlugKit());
        defendant.put("падают месяц", new PlugKit());
        defendant.put("падают год", new PlugKit());
        defendant.put("изменить оповещения", new PlugKit());
        defendant.put("отменить подписку", new PlugKit());
        for(TiketInformation tiketInformation : getNameTiker){
            if(tiketInformation.getTiket().length()!=0) {
                defendant.put(tiketInformation.getTiket().toLowerCase().trim(), new DataAboutTikerKit(tiketInformation.getTiket().toLowerCase(), getNameTiker));
            }
        }
        return defendant;
    }

}
