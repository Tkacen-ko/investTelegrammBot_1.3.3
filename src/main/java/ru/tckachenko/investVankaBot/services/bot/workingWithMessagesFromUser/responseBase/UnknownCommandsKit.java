package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Collections;
import java.util.List;

public class UnknownCommandsKit extends AnswerWithTextAndButtons {
    private static final String message = "Неизвестная команда!\n" +
            "Воспользуйтесь одной перечисленных ниже команд, " +
            "для получения необходимых вам данных:";
    private static final List<String> buttons = Collections.singletonList("Список команд");

    public List<String> getButtons() {
        return buttons;
    }

    public String getMessage() {
        return message;
    }
}
