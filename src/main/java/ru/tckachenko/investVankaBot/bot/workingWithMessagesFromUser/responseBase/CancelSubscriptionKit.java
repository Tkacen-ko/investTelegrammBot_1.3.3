package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class CancelSubscriptionKit extends AnswerWithTextOnly {
    private int notifications;
    private String id;
    public CancelSubscriptionKit(int  notifications, String id){
        this.notifications = notifications;
        this.id = id;
    }
    public static final String message = "Подписка отменена, " +
            "бот больше не будет присылать вам уведомления о падении стоимости на бирже.";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).updateFrequencyNotifications(notifications, id);
        return message;}
}