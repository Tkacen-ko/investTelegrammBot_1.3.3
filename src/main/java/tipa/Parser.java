package tipa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class Parser {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Parser(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public Integer ingratro(String s) {
        return Integer.parseInt(s.replaceAll("[%]", ""));
    }
    public void getPage() throws Exception{
        String url = "https://smart-lab.ru/q/shares/order_by_issue_capitalization_usd/desc/";
        Document page = Jsoup.parse(new URL(url), 3000);
        Element table = page.select("table[class=simple-little-table trades-table]").first();
        Elements el2 = table.getElementsByTag("tr");
        Collection<Collection<String>>mass = new ArrayList<>();
        try {
            for (int i = 0; i <= 283; i++) {
                Element e = el2.get(i);
                if (e.select("td").size() == 20) {
                    Date date=new SimpleDateFormat("dd:MM:yyyy").parse(e.select("td").get(1).text());

                    jdbcTemplate.update("INSERT INTO smart_lab_db VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            date,
                            e.select("td").get(2).text(),
                            e.select("td").get(3).text(),
                            e.select("td").get(7).text(),
                            e.select("td").get(14).text(),
                            e.select("td").get(15).text(),
                            ingratro(e.select("td").get(8).text()),
                            ingratro(e.select("td").get(10).text()),
                            ingratro(e.select("td").get(11).text()),
                            ingratro(e.select("td").get(13).text())
                            );
                }
            }
        }
        catch (Exception e) {
            System.out.println("!!! \nВнимание я влетел в блок иключения\n");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    };
}

