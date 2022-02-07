package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class FrequencyNotificationsKit extends AnswerWithTextOnly {
    private static final String message = "Отлично! Расписание оповещений установленно!";
    private Integer notifications;
    private String id;
    private GettingDataFromDatabase gettingDataFromDatabase;


    public FrequencyNotificationsKit(int notifications, String id, GettingDataFromDatabase gettingDataFromDatabase) {
        this.notifications = notifications;
        this.id = id;
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }


    @Override
    public String getMessage() {
        gettingDataFromDatabase.updateFrequencyNotifications(notifications, id);
        return message;
    }

    @Override
    public List<String> getButtons() {
        return null;
    }
}
