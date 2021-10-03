package ru.tckachenko.investVankaBot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.workingByDatabase.LoadingHistoricalData;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(SpringConfig.class);
        LoadingHistoricalData loadingHistoricalData = applicationContext.getBean("parser", LoadingHistoricalData.class);
        try {
            loadingHistoricalData.getPage();
        }catch (Exception e){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}