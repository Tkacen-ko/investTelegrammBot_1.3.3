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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@ComponentScan("parser")
public class Parser {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Parser(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public Double ingratro(String s) {
        if (s.length() != 0) {
            System.out.println(s.replaceAll("[% ]", ""));
            return Double.parseDouble(s.replaceAll("[% ]", ""));
        }
        return 0.000;
    }
    public void getPage() throws Exception{
        String url = "https://smart-lab.ru/q/shares/order_by_issue_capitalization/desc/?date=30.09.2021";
        Document page = Jsoup.parse(new URL(url), 3000);
        Element table = page.select("table[class=simple-little-table trades-table]").first();
        Elements el2 = table.getElementsByTag("tr");
        Collection<Collection<String>>mass = new ArrayList<>();
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
        }
        catch (Exception e) {
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

