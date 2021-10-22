package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class BasicComandKit extends AnswerWithTextAndButtons {
    public String message = "Выберете необходимые для отображения данные:";
    public List<String> buttons = Arrays.asList("Лидеры роста",
            "Лидеры падения", "TOP-Капиталистов", "TickerInfo");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
