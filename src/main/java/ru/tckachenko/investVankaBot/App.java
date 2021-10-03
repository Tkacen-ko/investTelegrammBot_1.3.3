package ru.tckachenko.investVankaBot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.config.SpringConfig;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(SpringConfig.class);
        Parser parser = applicationContext.getBean("parser", Parser.class);
        try {
            parser.getPage();
        }catch (Exception e){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }

    }
}