package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class LeadersGrowthKit extends AnswerWithTextAndButtons {
    private static final String message = "Выберите период, за который " +
            "необходимо получить данные.\n\n" +
            "Все ценные бумаги будут отсортированы по 4-м категориям:\n" +
            "1. Gold\uD83E\uDD47 - Акции российских компаний, лидеров по " +
            "капитализации на рынке\n" +
            "2. Silver\uD83E\uDD48 - Акции российских компаний, с средней" +
            "капитализацией\n" +
            "3. Bronze\uD83E\uDD49 - Акции российских компаний, с капитализацией" +
            "ниже среднего уровня\n" +
            "3. Dno\uD83C\uDFA2 - Акции российских компаний, с маленькой " +
            "капитализацией\n";
    public List<String> buttons = Arrays.asList("Растут сейчас",
            "Растут неделю", "Растут месяц", "Растут год");

    public List<String> getButtons() {
        return buttons;
    }

    public String getMessage() {
        return message;
    }
}
