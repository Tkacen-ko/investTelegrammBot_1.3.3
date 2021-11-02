package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;

import java.util.Arrays;
import java.util.List;

public class TickerInfoKit extends AnswerWithTextAndButtons {
    public static String message = "Отправьте ответным сообщением наименование тикера"+
            "(короткое буквенное обозначение ценное бумаги (Например GAZP, SBER, YNDX)), "+
            "о котором вы хотите получить информацию.\n" +
            "Команда «*Список тикеров*» - отобразит 30 случайных тикеров доступных для получения" +
            " данных.";
    private List<String> buttons = Arrays.asList("Список тикеров");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
