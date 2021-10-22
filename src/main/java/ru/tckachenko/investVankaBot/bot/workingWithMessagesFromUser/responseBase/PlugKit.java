package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class PlugKit extends AnswerWithTextOnly {
    public static final String message = "Кароче дядя. Это класс ещё в работе. Поэтому тут ничего нет. " +
            "Иди отсюда!";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        return message;
    }
}