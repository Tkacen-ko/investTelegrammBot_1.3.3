package ru.tckachenko.investVankaBot.schedule;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingHistoricalData;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingTodayData;


@Component
public class WorkSchedule {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);


//    @SneakyThrows
//    @Scheduled(cron = "0/1 * * * * *")
//    public void prevetTron(){
//        new AnnotationConfigApplicationContext(SpringConfig.class)
//                .getBean("loadingTodayData", LoadingTodayData.class).saitDataLoadDb();
//    }
//    @SneakyThrows
//    @Scheduled(cron = "0/5 * * * * *")
//    public void test(){
//        System.out.println("Жульен");
//    }

}

