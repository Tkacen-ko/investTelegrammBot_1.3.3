package ru.tckachenko.investVankaBot.dao.writingDatabase;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.URL;


public abstract class BasicLoadingFunctionalityBd implements RequestsBDInterface {
    public int numberColumnsForParsing;
    protected final JdbcTemplate jdbcTemplate;

    @Autowired
    public BasicLoadingFunctionalityBd(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @SneakyThrows
    public Elements siteDataParser() {
        String trueUrl = setUrl();
        if (trueUrl.length() == 0) {
            return null;
        }
        Document page = Jsoup.parse(new URL(trueUrl), 3000);
        Element table = page.select("table[class=simple-little-table trades-table]").first();
        return table.getElementsByTag("tr");  //webElementNotFoundException либо Optional
    }

    // метод для чистки значений от лишних знаков
    public Double deletingСharacters(String s) {
        if (s.length() != 0) {
            System.out.println(s.replaceAll("[+% ]", ""));
            return Double.parseDouble(s.replaceAll("[% ]", ""));
        }
        return 0.0;
    }

    public abstract void siteDataLoadDb();

    public abstract void createTable(Element elTd);

    public abstract void saveDataTable(Element elTd);

    public abstract String setUrl();
}
