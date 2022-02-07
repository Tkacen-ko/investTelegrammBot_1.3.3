package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.List;


public class CancelSubscriptionKit extends AnswerWithTextOnly {
    private final int notifications;
    private final String id;
    private final GettingDataFromDatabase gettingDataFromDatabase;


    public CancelSubscriptionKit(int notifications, String id, GettingDataFromDatabase gettingDataFromDatabase) {
        this.notifications = notifications;
        this.id = id;
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }

    private static final String message = "Подписка отменена, " +
            "бот больше не будет присылать вам уведомления о падении стоимости на бирже.";

    public List<String> getButtons() {
        return null;
    }

    public String getMessage() {
        gettingDataFromDatabase.updateFrequencyNotifications(notifications, id);
        return message;
    }
}