package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.tckachenko.investVankaBot.bot.SendMessageUserTelegtam;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.dataProcessor.UserData;
import ru.tckachenko.investVankaBot.dataProcessor.comparators.TiketInformationComparatorToCapitalizationmlrdru;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.stream.Collectors;

@ComponentScan
public class MailingMasegeToUsers {
    UserData notificationFrequenc;
    public MailingMasegeToUsers(UserData notificationFrequency){
        this.notificationFrequenc = notificationFrequency;
    }
    public void scheduledNotification(){

    }
    private int num;
    @SneakyThrows
    public void mailing(UserData userData) {
        String message = "";
            System.out.println(getFallGoldTiker(userData));
            if(getFallGoldTiker(userData).length()!=0){
                message += "Данные тикер(-ы) в категории Gold\uD83E\uDD47 упали на указанный вами порог:\n";
                message += getFallGoldTiker(userData);
            }
            System.out.println(getFallSilverTiker(userData).length());
            if(getFallSilverTiker(userData).length()!=0){
                message += "\nДанные тикер(-ы) в категории Silver\uD83E\uDD48 упали на указанный вами порог:\n";
                message += getFallSilverTiker(userData);
            }
            if(getFallBronzeTiker(userData).length()!=0){
                message += "\nДанные тикер(-ы) в категории Bronze\uD83E\uDD49 упали на указанный вами порог:\n";
                message += getFallBronzeTiker(userData);
            }
            if(getFallDnoTiker(userData).length()!=0){
                message += "\nДанные тикер(-ы) в категории Dno\uD83C\uDFA2 упали на указанный вами порог:\n";
                message += getFallDnoTiker(userData);
            }
            if (message.length()!=0){
                message = "    ‼*Внимание*‼\n*Падение стоимости* ценных бумаг, в рамках установленных вами порогов:\n"+message;
            }
            if (message.length()!=0) {
                AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
                bean.getBean(SendMessageUserTelegtam.class)
                        .execute(SendMessage.builder()
                                .chatId(userData.getId())
                                .text(message).build());
            }
    }

    public String getFallGoldTiker(UserData userData){

        return GettingDataFromDatabase.dataToTikerRealTime
                .stream()
                .filter(s-> s.getRankTiket().equals("gold"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .filter(s->s.getChangesday()<(userData.getAlertThresholdTikerGold()*(-1)))
                .map((p) -> " • " + p.getTiket() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }
    public String getFallSilverTiker(UserData userData){

        return GettingDataFromDatabase.dataToTikerRealTime
                .stream()
                .filter(s-> s.getRankTiket().equals("silver"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .filter(s->s.getChangesday()<(userData.getAlertThresholdTikerSilver()*(-1)))
                .map((p) -> " • " + p.getTiket() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }
    public String getFallBronzeTiker(UserData userData){

        return GettingDataFromDatabase.dataToTikerRealTime
                .stream()
                .filter(s-> s.getRankTiket().equals("bronze"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .filter(s->s.getChangesday()<(userData.getAlertThresholdTikerBronze()*(-1)))
                .map((p) -> " • " + p.getTiket() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }
    public String getFallDnoTiker(UserData userData){
        return GettingDataFromDatabase.dataToTikerRealTime
                .stream()
                .filter(s-> s.getRankTiket().equals("dno"))
                .collect(Collectors.toList())
                .stream()
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .filter(s->s.getChangesday()<(userData.getAlertThresholdTikerDno()*(-1)))
                .map((p) -> " • " + p.getTiket() + " (" + p.getChangesday() + "%)\n").collect(Collectors.joining());

    }

}
