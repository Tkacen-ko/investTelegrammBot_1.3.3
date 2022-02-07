package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase.*;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;
import java.util.*;

@Component
public class Defendant {

    private final GettingDataFromDatabase gettingDataFromDatabase;
    private final RatingCapitalistsKit ratingCapitalistsKit;
    private final DropNowKit dropNowKit;
    private final DropWeekKit dropWeekKit;
    private final DropMonthKit dropMonthKit;
    private final DropYearKit dropYearKit;
    private final GrowingNowKit growingNowKit;
    private final GrowingWeekKit growingWeekKit;
    private final GrowingMonthKit growingMonthKit;
    private final GrowingYearKit growingYearKit;


    @Autowired
    public Defendant(GettingDataFromDatabase gettingDataFromDatabase,
                     RatingCapitalistsKit ratingCapitalistsKit,
                     DropNowKit dropNowKit,
                     DropWeekKit dropWeekKit,
                     DropMonthKit dropMonthKit,
                     DropYearKit dropYearKit,
                     GrowingNowKit growingNowKit,
                     GrowingWeekKit growingWeekKit,
                     GrowingMonthKit growingMonthKit,
                     GrowingYearKit growingYearKit) {
        this.gettingDataFromDatabase = gettingDataFromDatabase;
        this.ratingCapitalistsKit = ratingCapitalistsKit;
        this.dropNowKit = dropNowKit;
        this.dropWeekKit = dropWeekKit;
        this.dropMonthKit = dropMonthKit;
        this.dropYearKit = dropYearKit;
        this.growingNowKit = growingNowKit;
        this.growingWeekKit = growingWeekKit;
        this.growingMonthKit = growingMonthKit;
        this.growingYearKit = growingYearKit;
    }

    public SendMessage batyaReshala(String masegeText, String chatID) {
        if (getClassAnswer(masegeText, chatID).get(masegeText) != null) {
            Answer answer = getClassAnswer(masegeText, chatID).get(masegeText);
            return answer.correctAnswer(chatID, answer.getMessage(), answer.getButtons());
        } else {
            UnknownCommandsKit unknownCommandsKit = new UnknownCommandsKit();
            return unknownCommandsKit.correctAnswer(chatID, unknownCommandsKit.getMessage(), unknownCommandsKit.getButtons());
        }
    }


    public Map<String, Answer> getClassAnswer(String masegeText, String chatID) {
        List<TikerInformation> getNameTiker = gettingDataFromDatabase.getDataToTickerRealTime();
        Map<String, Answer> defendant = new HashMap<>();
        defendant.put("/start", new StarterKit());

        defendant.put("базовые команды", new BasicComandKit());
        defendant.put("подписки\uD83D\uDCE3", new SubscriptionsKit());

        defendant.put("/commands", new BasicComandKit());
        defendant.put("/subscriptions", new SubscriptionsKit());

        defendant.put("лидеры падения\uD83D\uDCC9", new LeadersDeclineKit());
        defendant.put("лидеры роста\uD83D\uDCC8", new LeadersGrowthKit());
        defendant.put("ticker informationℹ", new TickerInfoKit());
        defendant.put("top50-капиталистов\uD83D\uDD1D", ratingCapitalistsKit);

        defendant.put("оформить подписку", new SubscribeKit());
        defendant.put("отменить подписку", new CancelSubscriptionKit(0, chatID, gettingDataFromDatabase));
        defendant.put("изменить частоту оповещений", new ChangeAlertsKit());

        defendant.put("список тикеров", new ListTikersKit(getNameTiker));

        defendant.put("растут сейчас", growingNowKit);
        defendant.put("растут неделю", growingWeekKit);
        defendant.put("растут месяц", growingMonthKit);
        defendant.put("растут год", growingYearKit);

        defendant.put("падают сейчас", dropNowKit);
        defendant.put("падают неделю", dropWeekKit);
        defendant.put("падают месяц", dropMonthKit);
        defendant.put("падают год", dropYearKit);

        defendant.put("1. как можно чаще", new FrequencyNotificationsKit(1, chatID, gettingDataFromDatabase));
        defendant.put("2. каждые час", new FrequencyNotificationsKit(2, chatID, gettingDataFromDatabase));
        defendant.put("3. два раза в день (13.00 и 17.00)", new FrequencyNotificationsKit(3, chatID, gettingDataFromDatabase));
        defendant.put("4. каждый вечер (в 19.30)", new FrequencyNotificationsKit(4, chatID, gettingDataFromDatabase));

        defendant.put("список команд", new ListCommandsKit());

        for (TikerInformation tikerInformation : getNameTiker) {
            if (tikerInformation.getTicker().length() != 0) {
                defendant.put(tikerInformation.getTicker().toLowerCase().trim(),
                        new DataAboutTikerKit(tikerInformation.getTicker().toLowerCase(),
                                getNameTiker));
            }
        }
        if (masegeText.contains("-")) {
            String[] strings = masegeText.split("-");
            if (strings.length == 2) {
                if (strings[0].equals("gold")) {
                    if (checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForGoldTikerKit((Double.parseDouble(strings[1])), chatID, "Gold", gettingDataFromDatabase));
                    }
                }
                if (strings[0].equals("silver")) {
                    if (checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForSilverTikerKit((Double.parseDouble(strings[1])), chatID, "Silver", gettingDataFromDatabase));
                    }
                }
                if (strings[0].equals("bronze")) {
                    if (checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForBronzeTickerKit((Double.parseDouble(strings[1])), chatID, "Bronze", gettingDataFromDatabase));
                    }
                }
                if (strings[0].equals("dno")) {
                    if (checkingForNumber(strings[1])) {
                        defendant.put(masegeText, new SetValueForDnoTikerKit((Double.parseDouble(strings[1])), chatID, "Dno", gettingDataFromDatabase));
                    }
                }
            }
        }

        return defendant;
    }

    public boolean checkingForNumber(String myNumber) {
        try {
            Double.parseDouble(myNumber);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
