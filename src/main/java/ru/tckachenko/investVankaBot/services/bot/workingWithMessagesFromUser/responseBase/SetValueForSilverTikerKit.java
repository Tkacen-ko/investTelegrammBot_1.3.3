package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class SetValueForSilverTikerKit extends AnswerWithTextOnly {
    private final double valueAlertThreshold;
    private final String id;
    private final String tickerType;
    private final GettingDataFromDatabase gettingDataFromDatabase;

    public SetValueForSilverTikerKit(double valueAlertThreshold, String id, String tickerType, GettingDataFromDatabase gettingDataFromDatabase) {
        this.valueAlertThreshold = valueAlertThreshold;
        this.id = id;
        this.tickerType = tickerType;
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }

    public static final String message = "Порог оповещения для Silver тикета установлен!\n" +
            "Теперь для Bronze " +
            "компаний(отправьте в чат значение от 1 до 100 в формате «Bronze-15»):\n";

    public List<String> getButtons() {
        return null;
    }

    public String getMessage() {
        gettingDataFromDatabase.updateUserTikerValue(valueAlertThreshold, id, tickerType);
        return message;
    }
}