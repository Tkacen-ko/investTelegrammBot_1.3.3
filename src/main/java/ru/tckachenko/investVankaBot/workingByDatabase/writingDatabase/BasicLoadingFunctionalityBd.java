package ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.net.URL;


public abstract class BasicLoadingFunctionalityBd implements RequestsBDInterface {
    public int numberlColumsForParsing;
    protected final JdbcTemplate jdbcTemplate;

    @Autowired
    public BasicLoadingFunctionalityBd(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public Elements siteDataParser(){

            String trueUrl = setUrl();
            if (trueUrl.length() == 0) {
                return null;
            }
            Document page;
            try {
                page = Jsoup.parse(new URL(trueUrl), 3000);
            }catch (IOException e){
                return null;
            }
            Element table = page.select("table[class=simple-little-table trades-table]").first();
            return table.getElementsByTag("tr");
    }

    // метод для чистки значений от лишних знаков
    public Double ingratro(String s) {
        if (s.length() != 0) {
            System.out.println(s.replaceAll("[+% ]", ""));
            return Double.parseDouble(s.replaceAll("[% ]", ""));
        }
        return 000.000;
    }
    public abstract void saitDataLoadDb();
    public abstract void createTable(Element elTd);
    public abstract void saveDataTable(Element elTd);
    public abstract String setUrl();
}
