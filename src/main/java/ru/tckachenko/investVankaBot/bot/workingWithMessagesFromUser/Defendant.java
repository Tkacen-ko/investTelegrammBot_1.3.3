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
        if (getClassAnswer(masegeText, chatID).get(masegeText)!=null){
            Answer answer = getClassAnswer(masegeText, chatID).get(masegeText);
            return answer.correctAnswer(chatID, answer.getMessage(), answer.getButtons());
        }
        else{
            UnknownCommandsKit unknownCommandsKit = new UnknownCommandsKit();
            return unknownCommandsKit.correctAnswer(chatID, unknownCommandsKit.getMessage(), unknownCommandsKit.getButtons());
        }
    }



    public Map<String, Answer> getClassAnswer(String masegeText, String chatID){
        List<TiketInformation> getNameTiker = new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).getDataToTikerRealTime();
        Map<String, Answer> defendant = new HashMap<>();
        defendant.put("/start", new StarterKit());

        defendant.put("базовые команды", new BasicComandKit());
        defendant.put("подписки\uD83D\uDCE3", new SubscriptionsKit());

        defendant.put("лидеры падения\uD83D\uDCC9", new LeadersDeclineKit());
        defendant.put("лидеры роста\uD83D\uDCC8", new LeadersGrowthKit());
        defendant.put("ticker informationℹ", new TickerInfoKit());
        defendant.put("top50-капиталистов\uD83D\uDD1D", new RatingCapitalistsKit());

        defendant.put("оформить подписку", new SubscribeKit());
        defendant.put("отменить подписку", new CancelSubscriptionKit());
        defendant.put("изменить частоту оповещений", new ChangeAlertsKit());

        defendant.put("список тикеров", new ListTikersKit(getNameTiker));

        defendant.put("растут сейчас", new GrowingNowKit());
        defendant.put("растут неделю", new GrowingWeekKit());
        defendant.put("растут месяц", new GrowingMonthKit());
        defendant.put("растут год", new GrowingYearKit());

        defendant.put("падают сейчас", new DropNowKit());
        defendant.put("падают неделю", new DropWeekKit());
        defendant.put("падают месяц", new DropMonthKit());
        defendant.put("падают год", new DropYearKit());

        defendant.put("1. как можно чаще", new FrequencyNotificationsKit(1, chatID));
        defendant.put("2. каждые час", new FrequencyNotificationsKit(2, chatID));
        defendant.put("3. два раза в день (13.00 и 17.00)", new FrequencyNotificationsKit(3, chatID));
        defendant.put("4. каждый вечер (в 19.30)", new FrequencyNotificationsKit(4, chatID));


        for(TiketInformation tiketInformation : getNameTiker){
            if(tiketInformation.getTiket().length()!=0) {
                defendant.put(tiketInformation.getTiket().toLowerCase().trim(),
                        new DataAboutTikerKit(tiketInformation.getTiket().toLowerCase(),
                                getNameTiker));
            }
        }
        if (masegeText.contains("-")){
            String[] strings = masegeText.split("-");
            if (strings.length==2){
                if(strings[0].toLowerCase().equals("gold")){
                    if(checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForGoldTikerKit((Double.parseDouble(strings[1])), chatID, "Gold"));
                    }
                }
                if(strings[0].toLowerCase().equals("silver")){
                    if(checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForSilverTikerKit((Double.parseDouble(strings[1])), chatID, "Silver"));
                    }
                }
                if(strings[0].toLowerCase().equals("bronze")){
                    if(checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForBronzeTikerKit((Double.parseDouble(strings[1])), chatID, "Bronze"));
                    }
                }
                if(strings[0].toLowerCase().equals("dno")){
                    if(checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForDnoTikerKit((Double.parseDouble(strings[1])), chatID, "Dno"));
                    }
                }
            }
        }

        return defendant;
    }


    public boolean checkingForNumber(String myNymber){
        try {
            Double.parseDouble(myNymber);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

}
