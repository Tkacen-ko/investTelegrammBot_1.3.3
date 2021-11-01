package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class SetValueForBronzeTikerKit extends AnswerWithTextOnly {
    private double valueAlertThreshold;
    private String id;
    String tikerTipe;
    public SetValueForBronzeTikerKit(double valueAlertThreshold, String id, String tikerTipe){
        this.valueAlertThreshold = valueAlertThreshold;
        this.id = id;
        this.tikerTipe = tikerTipe;
    }
    public static final String message = "Порог оповещения для Bronze тикета установлен!\n" +
            "Теперь для Dno компаний:(отправьте в чат значение от 1 до 100 в формате «Dno-28»)\n";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).updateUserTikerValue(valueAlertThreshold, id, tikerTipe);
        return message;
    }
}