package tipa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@ComponentScan("parser")
public class Parser {
    public static final String urdSmarlLabNoDate = "https://smart-lab.ru/q/shares/order_by_issue_capitalization/desc/?date=";
    private static Date dateStart;
    private static final Date nowDate= new Date();
    static {
        try {
            dateStart = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "01.01.2015" );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Parser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // метод для чистки значений от лишних знаков
    public Double ingratro(String s) {
        if (s.length() != 0) {
            System.out.println(s.replaceAll("[% ]", ""));
            return Double.parseDouble(s.replaceAll("[% ]", ""));
        }
        return 0.000;
    }

    // метод предоставляющий ссылку URL для считывания данных
    public String getUrl() {
        if (dateStart.toString().equals(nowDate.toString())){
            return null;
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String urlForReturn = urdSmarlLabNoDate + formatForDateNow.format(dateStart);
        System.out.println("Дата до изменения " +dateStart);
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateStart);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        dateStart = instance.getTime();
        System.out.println("Дата до после " +dateStart);
        return urlForReturn;
    }

    public void getPage() throws Exception {
        while(true){
        String url = getUrl();
        if (url.length() == 0) {
            break;
        }
        Document page = Jsoup.parse(new URL(url), 3000);
        if(page.select("table[class=simple-little-table trades-table]").size()==0){
            continue;
        }
        Element table = page.select("table[class=simple-little-table trades-table]").first();
        Elements el2 = table.getElementsByTag("tr");
        Collection<Collection<String>> mass = new ArrayList<>();
        try {
            for (int i = 0; i <= 283; i++) {
                Element e = el2.get(i);
                Date dateNow = new Date();
                SimpleDateFormat date = new SimpleDateFormat("hh:mm:ss");
                String ss = date.format(dateNow).toString();
                if (e.select("td").size() == 18) {
                    //Date date=new SimpleDateFormat("dd:MM:yyyy").parse(e.select("td").get(1).text());
                    jdbcTemplate.update("INSERT INTO alldatatiket VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            i,
                            e.select("td").get(1).text(),
                            e.select("td").get(2).text(),
                            ingratro(e.select("td").get(6).text()),
                            ingratro(e.select("td").get(13).text()),
                            ingratro(e.select("td").get(14).text()),

                            ingratro(e.select("td").get(7).text()),
                            ingratro(e.select("td").get(9).text()),
                            ingratro(e.select("td").get(10).text()),
                            ingratro(e.select("td").get(12).text())
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("!!! \nВнимание я влетел в блок иключения\n");
            e.printStackTrace();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
}

