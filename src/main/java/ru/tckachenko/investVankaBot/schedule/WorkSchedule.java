package ru.tckachenko.investVankaBot.schedule;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.config.SpringConfig;


@Component
public class WorkSchedule {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);


//    @SneakyThrows
//    @Scheduled(cron = "0/15 * * * * *")
//    public void prevetTron(){
//        bean.getBean("sendMessageUserTelegtam", SendMessageUserTelegtam.class).execute(SendMessage.builder().chatId("891228156").text("Я шлю ПРИВЕТЫ каждые 15 секунд").build());
//    }
//    @SneakyThrows
//    @Scheduled(cron = "0/5 * * * * *")
//    public void test(){
//        System.out.println("Жульен");
//    }

}

