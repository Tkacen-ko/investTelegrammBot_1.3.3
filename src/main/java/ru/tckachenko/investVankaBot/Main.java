package ru.tckachenko.investVankaBot;



import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tckachenko.investVankaBot.bot.ReactionToMessage;
import ru.tckachenko.investVankaBot.config.SpringConfig;


@SpringBootApplication
@EnableScheduling
public class Main {
    @SneakyThrows
    public static void main(String[] args){
        new TelegramBotsApi(DefaultBotSession.class).registerBot(new ReactionToMessage());
        SpringApplication.run(Main.class, args);
    }
}