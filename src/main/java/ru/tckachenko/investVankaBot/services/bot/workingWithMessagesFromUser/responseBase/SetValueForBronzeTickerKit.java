package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.List;

public class SetValueForBronzeTickerKit extends AnswerWithTextOnly {
    private final double valueAlertThreshold;
    private final String id;
    private final String tickerType;
    private final GettingDataFromDatabase gettingDataFromDatabase;

    public SetValueForBronzeTickerKit(double valueAlertThreshold, String id, String tickerType, GettingDataFromDatabase gettingDataFromDatabase) {
        this.valueAlertThreshold = valueAlertThreshold;
        this.id = id;
        this.tickerType = tickerType;
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }

    public static final String message = "Порог оповещения для Bronze тикета установлен!\n" +
            "Теперь для Dno компаний:(отправьте в чат значение от 1 до 100 в формате «Dno-28»)\n";

    public List<String> getButtons() {
        return null;
    }

    public String getMessage() {
        gettingDataFromDatabase.updateUserTikerValue(valueAlertThreshold, id, tickerType);
        return message;
    }
}