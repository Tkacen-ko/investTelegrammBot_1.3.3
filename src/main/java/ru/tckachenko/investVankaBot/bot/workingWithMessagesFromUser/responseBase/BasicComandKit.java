package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class BasicComandKit extends AnswerWithTextAndButtons {
    public String message = "Выберете команду для отображения необходимых данных:\n\n" +
            " • «*Лидеры роста*\uD83D\uDCC8» - предоставит компании демонстрирующие " +
            "рост↗ стоимости акций за указанный_(в следующем меню)_ период времени\n\n" +
            " • «*Лидеры падения*\uD83D\uDCC9» - предоставит компании демонстрирующие падение↘" +
            "стоимости акций за указанный_(в следующем меню)_ период времени\n\n" +
            " • «*TOP50-Капиталистов*\uD83D\uDD1D» - предоставит первые 50 компаний лидирующих по" +
            "капитализации на Московской бирже\n\n" +
            " • «*Ticker Information*ℹ» - предоставляет информацию о большинстве тикеров " +
            "Московской биржи";

    public List<String> buttons = Arrays.asList("Лидеры роста\uD83D\uDCC8",
            "Лидеры падения\uD83D\uDCC9", "TOP50-Капиталистов\uD83D\uDD1D", "Ticker Informationℹ");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }
}
