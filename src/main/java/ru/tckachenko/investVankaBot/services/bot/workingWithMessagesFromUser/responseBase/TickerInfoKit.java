package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Collections;
import java.util.List;

public class TickerInfoKit extends AnswerWithTextAndButtons {
    private static final String message = "Отправьте ответным сообщением наименование тикера" +
            "(_короткое буквенное обозначение ценное бумаги_(Например *GAZP*, *SBER*, *YNDX*)), " +
            "о котором вы хотите получить информацию.\n" +
            "Команда «*Список тикеров*» - отобразит 30 случайных тикеров доступных для получения" +
            " данных.";
    private final List<String> buttons = Collections.singletonList("Список тикеров");

    public List<String> getButtons() {
        return buttons;
    }

    public String getMessage() {
        return message;
    }
}
