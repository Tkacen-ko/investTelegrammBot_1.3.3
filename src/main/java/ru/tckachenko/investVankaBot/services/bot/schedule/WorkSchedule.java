package ru.tckachenko.investVankaBot.services.bot.schedule;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tckachenko.investVankaBot.services.bot.ReactionToMessage;
import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.MailingMessageToUsers;
import ru.tckachenko.investVankaBot.dao.BasicInformation;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;


@Component
public class WorkSchedule {

    private final BasicInformation basicInformation;
    private final MailingMessageToUsers mailingMessageToUsers;
    private final ReactionToMessage reactionToMessage;

    @Autowired
    public WorkSchedule(BasicInformation basicInformation, MailingMessageToUsers mailingMessageToUsers, ReactionToMessage reactionToMessage) {
        this.basicInformation = basicInformation;
        this.mailingMessageToUsers = mailingMessageToUsers;
        this.reactionToMessage = reactionToMessage;
    }

    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        new TelegramBotsApi(DefaultBotSession.class).registerBot(reactionToMessage);
        basicInformation.startDataInitialization();
    }


    @SneakyThrows
    @Scheduled(cron = "0 0/10 10-20 * * MON-FRI")
    public void updatingDataAndNotificationVigilant() {
        basicInformation.getBasicInformation();
        GettingDataFromDatabase.userInformationRealTime.stream()
                .filter(s -> s.getNotificationFrequency() == 1)
                .forEach(mailingMessageToUsers::mailing);
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 11-20 * * MON-FRI")
    public void hourlyAlerts() {
        GettingDataFromDatabase.userInformationRealTime.stream()
                .filter(s -> s.getNotificationFrequency() == 2)
                .forEach(mailingMessageToUsers::mailing);
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 13,17 * * MON-FRI")
    public void notificationLunchAndEvening() {
        GettingDataFromDatabase.userInformationRealTime.stream()
                .filter(s -> s.getNotificationFrequency() == 3)
                .forEach(mailingMessageToUsers::mailing);
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 19 * * MON-FRI")
    public void notificationEvening() {
        GettingDataFromDatabase.userInformationRealTime.stream()
                .filter(s -> s.getNotificationFrequency() == 4)
                .forEach(mailingMessageToUsers::mailing);
    }
}

