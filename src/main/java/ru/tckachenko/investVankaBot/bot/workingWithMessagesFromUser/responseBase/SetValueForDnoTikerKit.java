package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.Arrays;
import java.util.List;

public class SetValueForDnoTikerKit extends AnswerWithTextAndButtons {
    private double valueAlertThreshold;
    private String id;
    String tikerTipe;
    public SetValueForDnoTikerKit(double valueAlertThreshold, String id, String tikerTipe){
        this.valueAlertThreshold = valueAlertThreshold;
        this.id = id;
        this.tikerTipe = tikerTipe;
    }
    public static final String message = "Отлично! Порог определён! Выберете частоту оповещения:";
    private List<String> buttons = Arrays.asList("1. Как можно чаще", "2. Каждые час",
            "3. Два раза в день (13.00 и 17.00)", "4. Каждый вечер (в 19.30)");

    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).updateUserTikerValue(valueAlertThreshold, id, tikerTipe);
        return message;
    }
}