package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class UnknownCommandsKit extends AnswerWithTextAndButtons {
    private String message = "Неизвестная команда!\n" +
            "Воспользуйтесь одной перечисленных ниже команд, " +
            "для получения необходимых вам данных:";
    private List<String> buttons = Arrays.asList("Список команд");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
