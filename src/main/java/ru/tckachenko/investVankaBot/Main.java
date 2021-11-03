package ru.tckachenko.investVankaBot;



import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tckachenko.investVankaBot.bot.ReactionToMessage;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.CompanyInformation;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingTodayData;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.RecordingUserData;

import java.io.IOException;
import java.net.URL;


@SpringBootApplication
@EnableScheduling
public class Main {
    @SneakyThrows
    public static void main(String[] args){
        new AnnotationConfigApplicationContext(SpringConfig.class).getBean(LoadingTodayData.class).saitDataLoadDb();
        new AnnotationConfigApplicationContext(SpringConfig.class).getBean(GettingDataFromDatabase.class).loadingDataToTikerRealTime();
        new AnnotationConfigApplicationContext(SpringConfig.class).getBean(GettingDataFromDatabase.class).loadingUserData();
        new TelegramBotsApi(DefaultBotSession.class).registerBot(new ReactionToMessage());
        SpringApplication.run(Main.class, args);
    }
}