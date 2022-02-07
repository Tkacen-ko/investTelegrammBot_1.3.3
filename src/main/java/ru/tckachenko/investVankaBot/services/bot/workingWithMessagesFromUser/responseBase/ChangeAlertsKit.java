package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class ChangeAlertsKit extends AnswerWithTextOnly {
    private static final String massageFromStart = new SubscribeKit().getMessage();
    public static final String message = "В данном меню вы можете изменить " +
            "порог оповещения по необходимым вам категориям или частоту оповещения." +
            "Напоминаю ";

    public List<String> getButtons() {
        return null;
    }

    public String getMessage() {
        return message + massageFromStart;
    }
}