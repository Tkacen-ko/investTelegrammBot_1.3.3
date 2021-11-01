package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class ChangeAlertsKit extends AnswerWithTextOnly {
    String masegeFromStart = new SubscribeKit().getMessage();
    public static final String message = "В данном меню вы можете изменить " +
                "порог оповещения по необходимым вам категориям или частоту оповещения."+
                "Напоминаю ";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        return message + masegeFromStart;
    }
}