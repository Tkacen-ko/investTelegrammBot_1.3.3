package ru.tckachenko.investVankaBot;



import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tckachenko.investVankaBot.bot.ReactionToMessage;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase.RatingCapitalistsKit;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingTodayData;

import java.util.List;


@SpringBootApplication
@EnableScheduling
public class Main {
    @SneakyThrows
    public static void main(String[] args){
        new AnnotationConfigApplicationContext(SpringConfig.class)
                .getBean(GettingDataFromDatabase.class).loadingDataToTikerRealTime();
        new TelegramBotsApi(DefaultBotSession.class).registerBot(new ReactionToMessage());
        SpringApplication.run(Main.class, args);
//        new AnnotationConfigApplicationContext(SpringConfig.class)
//                .getBean("loadingTodayData",LoadingTodayData.class)
//                .saitDataLoadDb();
//        RatingCapitalistsKit ratingCapitalistsKit = new RatingCapitalistsKit();
//
//            System.out.println(ratingCapitalistsKit.message);
//
//
    }
}