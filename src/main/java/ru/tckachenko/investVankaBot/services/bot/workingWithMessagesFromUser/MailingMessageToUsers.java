package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.tckachenko.investVankaBot.services.bot.SendMessageUserTelegram;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.UserData;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators.TikerInformationComparatorToCapitalizationmlrdru;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.stream.Collectors;

@Component
public class MailingMessageToUsers {

    private final SendMessageUserTelegram sendMessageUserTelegram;

    @Autowired
    public MailingMessageToUsers(SendMessageUserTelegram sendMessageUserTelegram) {
        this.sendMessageUserTelegram = sendMessageUserTelegram;
    }

    @SneakyThrows
    public void mailing(UserData userData) {
        String message = "";
        if (getFallGoldTiker(userData).length() != 0) {
            message += "Данные тикер(-ы) в категории Gold\uD83E\uDD47 упали на указанный вами порог:\n";
            message += getFallGoldTiker(userData);
        }
        System.out.println(getFallSilverTiker(userData).length());
        if (getFallSilverTiker(userData).length() != 0) {
            message += "\nДанные тикер(-ы) в категории Silver\uD83E\uDD48 упали на указанный вами порог:\n";
            message += getFallSilverTiker(userData);
        }
        if (getFallBronzeTiker(userData).length() != 0) {
            message += "\nДанные тикер(-ы) в категории Bronze\uD83E\uDD49 упали на указанный вами порог:\n";
            message += getFallBronzeTiker(userData);
        }
        if (getFallDnoTiker(userData).length() != 0) {
            message += "\nДанные тикер(-ы) в категории Dno\uD83C\uDFA2 упали на указанный вами порог:\n";
            message += getFallDnoTiker(userData);
        }
        if (message.length() != 0) {
            message = "    ‼*Внимание*‼\n*Падение стоимости* ценных бумаг, в рамках установленных вами порогов:\n" + message;
        }
        if (message.length() != 0) {
            sendMessageUserTelegram.execute(SendMessage.builder()
                    .chatId(userData.getId())
                    .parseMode("Markdown")
                    .text(message).build());
        }
    }

    public String getFallGoldTiker(UserData userData) {
        return GettingDataFromDatabase.dataToTickerRealTime
                .stream()
                .filter(s -> s.getRankTicker().equals("gold"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .filter(s -> s.getChangesday() < (userData.getAlertThresholdTickerGold() * (-1)))
                .limit(10)
                .map((p) -> " • " + p.getTicker() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }

    public String getFallSilverTiker(UserData userData) {
        return GettingDataFromDatabase.dataToTickerRealTime
                .stream()
                .filter(s -> s.getRankTicker().equals("silver"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .filter(s -> s.getChangesday() < (userData.getAlertThresholdTickerSilver() * (-1)))
                .limit(10)
                .map((p) -> " • " + p.getTicker() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }

    public String getFallBronzeTiker(UserData userData) {
        return GettingDataFromDatabase.dataToTickerRealTime
                .stream()
                .filter(s -> s.getRankTicker().equals("bronze"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .filter(s -> s.getChangesday() < (userData.getAlertThresholdTickerBronze() * (-1)))
                .limit(10)
                .map((p) -> " • " + p.getTicker() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }

    public String getFallDnoTiker(UserData userData) {
        return GettingDataFromDatabase.dataToTickerRealTime
                .stream()
                .filter(s -> s.getRankTicker().equals("dno"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .filter(s -> s.getChangesday() < (userData.getAlertThresholdTickerDno() * (-1)))
                .limit(10)
                .map((p) -> " • " + p.getTicker() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());
    }
}
