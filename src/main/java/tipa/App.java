package tipa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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