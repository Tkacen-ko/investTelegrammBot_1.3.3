package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class CancelSubscriptionKit extends AnswerWithTextOnly {
    public static final String message = "Подписка отменена, " +
            "бот больше не будет присылать вам уведомления о падении стоимости на бирже.";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {return message;
    }
}