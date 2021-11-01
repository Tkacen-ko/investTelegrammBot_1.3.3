package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class FrequencyNotificationsKit extends AnswerWithTextOnly {
    public static final String message = "Отлично! Расписание оповещений установленно!";
    private int notifications;
    private String id;
    public FrequencyNotificationsKit(int  notifications, String id){
        this.notifications = notifications;
        this.id = id;
    }


    @Override
    public String getMessage() {
        new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).updateFrequencyNotifications(notifications, id);
        return message;
    }
    @Override
    public List<String> getButtons() {return null;}
}
