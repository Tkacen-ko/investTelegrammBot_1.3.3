package ru.tckachenko.investVankaBot;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.tckachenko.investVankaBot.bot.ReactionToMessage;
import ru.tckachenko.investVankaBot.bot.SendMessageUserTelegtam;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingHistoricalData;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingTodayData;


@Component
public class WorkSchedule {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);


    @SneakyThrows
    @Scheduled(cron = "0/15 * * * * *")
    public void prevetTron(){
        bean.getBean("sendMessageUserTelegtam", SendMessageUserTelegtam.class).execute(SendMessage.builder().chatId("891228156").text("Я шлю ПРИВЕТЫ каждые 15 секунд").build());
    }
    @SneakyThrows
    @Scheduled(cron = "0/5 * * * * *")
    public void test(){
        System.out.println("хуй");
    }

}

