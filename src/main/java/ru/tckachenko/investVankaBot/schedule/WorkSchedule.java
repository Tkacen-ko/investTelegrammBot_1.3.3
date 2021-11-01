package ru.tckachenko.investVankaBot.schedule;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.MailingMasegeToUsers;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.UserData;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingTodayData;


@Component
public class WorkSchedule {
    @SneakyThrows
    @Scheduled(cron = "0 0/10 9-20 * * MON-FRI")
    public void updatingDataAndNotificationVigilant() {
        new AnnotationConfigApplicationContext(SpringConfig.class).getBean(LoadingTodayData.class).saitDataLoadDb();
        new AnnotationConfigApplicationContext(SpringConfig.class).getBean(GettingDataFromDatabase.class).loadingDataToTikerRealTime();
        new AnnotationConfigApplicationContext(SpringConfig.class).getBean(GettingDataFromDatabase.class).loadingUserData();
        for (UserData userData : GettingDataFromDatabase.userInformationRealTime) {
            if (userData.getNotificationFrequency() == 1) {
                new AnnotationConfigApplicationContext(SpringConfig.class).getBean(MailingMasegeToUsers.class).mailing(userData);
            }
        }
    }
    @SneakyThrows
    @Scheduled(cron = "0 0 9-20 * * MON-FRI")
    public void hourlyAlerts() {
        for (UserData userData : GettingDataFromDatabase.userInformationRealTime) {
            if (userData.getNotificationFrequency() == 2) {
                new AnnotationConfigApplicationContext(SpringConfig.class).getBean(MailingMasegeToUsers.class).mailing(userData);
            }
        }
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 13,17 * * MON-FRI")
    public void notificationLunchAndEvening() {
        for (UserData userData : GettingDataFromDatabase.userInformationRealTime) {
            if (userData.getNotificationFrequency() == 3) {
                new AnnotationConfigApplicationContext(SpringConfig.class).getBean(MailingMasegeToUsers.class).mailing(userData);
            }
        }
    }

    @SneakyThrows
    @Scheduled(cron = "0 0 19 * * MON-FRI")
    public void notificationEvening() {
        for (UserData userData : GettingDataFromDatabase.userInformationRealTime) {
            if (userData.getNotificationFrequency() == 4) {
                new AnnotationConfigApplicationContext(SpringConfig.class).getBean(MailingMasegeToUsers.class).mailing(userData);
            }
        }
    }
}

