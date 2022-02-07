package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.Arrays;
import java.util.List;


public class SetValueForDnoTikerKit extends AnswerWithTextAndButtons {
    private final double valueAlertThreshold;
    private final String id;
    private final String tickerType;
    private final GettingDataFromDatabase gettingDataFromDatabase;

    public SetValueForDnoTikerKit(double valueAlertThreshold, String id, String tickerType, GettingDataFromDatabase gettingDataFromDatabase) {
        this.valueAlertThreshold = valueAlertThreshold;
        this.id = id;
        this.tickerType = tickerType;
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }

    private static final String message = "Отлично! Порог определён! Выберете частоту оповещения:";
    private static final List<String> buttons = Arrays.asList("1. Как можно чаще", "2. Каждые час",
            "3. Два раза в день (13.00 и 17.00)", "4. Каждый вечер (в 19.30)");

    public List<String> getButtons() {
        return buttons;
    }

    public String getMessage() {
        gettingDataFromDatabase.updateUserTikerValue(valueAlertThreshold, id, tickerType);
        return message;
    }
}