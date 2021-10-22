package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class LeadersDeclineKit extends AnswerWithTextAndButtons {
    private String message = "Выберите период, за который " +
            "необходимо получить данные:";
    private List<String> buttons = Arrays.asList( "Падают сейчас",
            "Падают неделю", "Падают месяц", "Падают год");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
