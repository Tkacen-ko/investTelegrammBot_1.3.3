package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class ListCommandsKit extends AnswerWithTextOnly {

    private static final String message =
            " • /commands - получить полезную информацию " +
                    "по акциям, представленным на Московской бирже.\n" +
                    " • /subscriptions - позволяет оформить информационную " +
                    "рассылку на случай резкого падения↘️ стоимости " +
                    "акций на бирже.\n" +
                    " • /start - вызов стартового меню выбора действий.";

    public List<String> getButtons() {
        return null;
    }

    public String getMessage() {
        return message;
    }
}
