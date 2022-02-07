package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class SubscriptionsKit extends AnswerWithTextAndButtons {
    private static final String message = "Выберите команду для необходимого " +
            "изменения в подписках:";
    private static final List<String> buttons = Arrays.asList("Оформить подписку", "Отменить подписку",
            "Изменить частоту оповещений");

    public List<String> getButtons() {
        return buttons;
    }

    public String getMessage() {
        return message;
    }
}
