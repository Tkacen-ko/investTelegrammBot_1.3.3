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
    private static String dateDataBD;
    private static final Date nowDate = new Date();
    static {
        try {
            dateStart = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public Parser(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void getPage() throws Exception {
        while (true) {
            String url = getUrl();
            if (url.length() == 0) {
                break;
            }
            Document page = Jsoup.parse(new URL(url), 3000);
            if (page.select("table[class=simple-little-table trades-table]").first().getElementsByTag("tr").size() == 1) {
                continue;
            }
            Element table = page.select("table[class=simple-little-table trades-table]").first();
            Elements el2 = table.getElementsByTag("tr");
            try {
                for (int i = 1; i <= el2.size()-1; i++) {
                    Element e = el2.get(i);
                    if (e.select("td").size() == 18) {
                        createTable(e, url);
                    }
                }
                System.out.println();
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
        if (dateStart.toString().equals(nowDate.toString())) {
            return null;
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String urlForReturn = urdSmarlLabNoDate + formatForDateNow.format(dateStart);
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateStart);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        dateDataBD = formatForDateNow.format(dateStart);
        System.out.println(dateDataBD);
        dateStart = instance.getTime();
        return urlForReturn;
    }
    public void createTable(Element elTd, String url){
        try {
            jdbcTemplate.execute("create table "+elTd.select("td").get(2).text()+"Ticet (" +
                    "    dateData data," +
                    "    name character varying(255)," +
                    "    tiket character varying(255)," +
                    "    price numeric," +
                    "    capitalizationrub numeric," +
                    "    capitalizationdollars numeric," +
                    "    changesday numeric," +
                    "    changesweek numeric," +
                    "    changesmonth numeric," +
                    "    changesyear numeric" +
                    ")");
        }
        catch (Exception e){
                        jdbcTemplate.update("INSERT INTO "+ elTd.select("td").get(2).text()+"Ticet VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                elTd.select(dateDataBD),
                                elTd.select("td").get(1).text(),
                                elTd.select("td").get(2).text(),
                                ingratro(elTd.select("td").get(6).text()),
                                ingratro(elTd.select("td").get(13).text()),
                                ingratro(elTd.select("td").get(14).text()),

                                ingratro(elTd.select("td").get(7).text()),
                                ingratro(elTd.select("td").get(9).text()),
                                ingratro(elTd.select("td").get(10).text()),
                                ingratro(elTd.select("td").get(12).text())
                        );
        }
    }
}

