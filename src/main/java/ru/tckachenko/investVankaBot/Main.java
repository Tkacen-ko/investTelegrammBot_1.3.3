package ru.tckachenko.investVankaBot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.LoadingHistoricalData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(SpringConfig.class);
        LoadingHistoricalData loadingHistoricalData = applicationContext.getBean("loadingHistoricalData", LoadingHistoricalData.class);
        try {
            loadingHistoricalData.saitDataLoadDb();
        }catch (Exception e){
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
        }
    }
}