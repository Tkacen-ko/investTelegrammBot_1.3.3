package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ComponentScan("loadingHistoricalData")
public class LoadingHistoricalData {
    public static final String urdSmarlLabNoDate = "https://smart-lab.ru/q/shares/order_by_issue_capitalization/desc/?date=";
    private static Date dateStart;
    private static String dateDataBD;
    private static final Date nowDate = new Date();
    private final JdbcTemplate jdbcTemplate;

    static {
        try {
            dateStart = new SimpleDateFormat("dd.MM.yyyy").parse("23.06.2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public LoadingHistoricalData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void getPage() throws Exception {
        while (true) {
            System.out.println("\n\nI work by "+dateDataBD+"\n\n");
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
                        createTable(e);
                    }
                }
            } catch (Exception e) {
                System.out.println(" \n\n!!!\nВнимание я влетел в блок иключения");
                e.printStackTrace();
                for(Element el : el2){
                    System.out.println(el.text());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    // метод для чистки значений от лишних знаков
    public Double ingratro(String s) {
        if (s.length() != 0) {
            System.out.println(s.replaceAll("[+% ]", ""));
            return Double.parseDouble(s.replaceAll("[% ]", ""));
        }
        return 000.000;
    }

    // метод предоставляющий ссылку URL для считывания данных
    public String getUrl() {
        if (dateStart.toString().equals(nowDate.toString())) {
            return null;
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatForDateNow2 = new SimpleDateFormat("yyyy-MM-dd");
        String urlForReturn = urdSmarlLabNoDate + formatForDateNow.format(dateStart);
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateStart);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        dateDataBD = formatForDateNow2.format(dateStart);
        dateStart = instance.getTime();
        return urlForReturn;
    }

    public void createTable(Element elTd){
        if(elTd.select("td").get(2).text().length()==0){
            return;
        }
        try {
            jdbcTemplate.execute("create table "+elTd.select("td").get(2).text()+" (" +
                    "    data date PRIMARY KEY," +
                    "    price DOUBLE," +
                    "    capitalizationmlrdru DOUBLE," +
                    "    capitalizationmlrddollars DOUBLE," +
                    "    changesday DOUBLE," +
                    "    changesweek DOUBLE," +
                    "    changesmonth DOUBLE," +
                    "    changesyear DOUBLE" +
                    ")");
        }
        catch (Exception e){
            saveDataDb(elTd);
        }
    }


    public void saveDataDb(Element elTd){
        try {
            jdbcTemplate.update("INSERT INTO "+ elTd.select("td").get(2).text()+" VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    dateDataBD,
                    ingratro(elTd.select("td").get(6).text())==000.000?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(13).text())==000.00?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(14).text())==000.00?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(7).text())==000.00?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(9).text())==000.00?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(10).text())==000.00?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(12).text())==000.00?null:ingratro(elTd.select("td").get(6).text())
            );
        }catch (DuplicateKeyException e){
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\nЗначение под датой "+dateDataBD+" уже существует в БД\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
        }

    }
}

