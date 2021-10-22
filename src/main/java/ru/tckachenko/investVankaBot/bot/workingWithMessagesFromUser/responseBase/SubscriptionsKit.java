package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class SubscriptionsKit extends AnswerWithTextAndButtons {
    public String message = "Выберите необходимое изменение в подписках:";
    public List<String> buttons = Arrays.asList("Оформить подписку",
            "Изменить оповещения", "Отменить подписку");

    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
