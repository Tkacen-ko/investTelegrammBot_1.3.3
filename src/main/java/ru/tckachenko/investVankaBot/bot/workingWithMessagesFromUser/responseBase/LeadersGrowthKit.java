package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class LeadersGrowthKit extends AnswerWithTextAndButtons {
    public String message = "Выберите период, за который " +
            "необходимо получить данные:";
    public List<String> buttons = Arrays.asList( "Растут сейчас",
            "Растут неделю", "Растут месяц", "Растут год");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
