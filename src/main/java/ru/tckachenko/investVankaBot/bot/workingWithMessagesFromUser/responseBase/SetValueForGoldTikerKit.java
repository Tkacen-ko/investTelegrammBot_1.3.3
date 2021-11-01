package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class SetValueForGoldTikerKit extends AnswerWithTextOnly {
    private double valueAlertThreshold;
    private String id;
    String tikerTipe;
    public SetValueForGoldTikerKit(double valueAlertThreshold, String id, String tikerTipe){
        this.valueAlertThreshold = valueAlertThreshold;
        this.id = id;
        this.tikerTipe = tikerTipe;
    }

    public static final String message = "Порог оповещения для Gold тикета установлен!\n" +
            "Теперь для Silver компаний (отправьте в чат " +
            "значение от 1 до 100 в формате «Silver-11»):\n";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).updateUserTikerValue(valueAlertThreshold, id, tikerTipe);
        return message;
    }
}