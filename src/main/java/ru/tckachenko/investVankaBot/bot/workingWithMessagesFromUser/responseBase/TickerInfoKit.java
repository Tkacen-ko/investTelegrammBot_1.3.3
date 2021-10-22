package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class TickerInfoKit extends AnswerWithTextOnly {
    public static final String message = "Отправьте ответным сообщением наименование тикера"+
            "(короткое буквенное обозначение ценное бумаги (Например GAZP, SBER, YNDX)), "+
            "о котором вы хотите получить информацию:";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        return message;
    }
}
