package ru.tckachenko.investVankaBot.dao.writingDatabase;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoadingTickerInformation implements RequestsBDInterface {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoadingTickerInformation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createTable(Element elTd) {
        if (elTd.select("td").get(2).text().length() == 0) return;

        try {
            jdbcTemplate.execute("create table " + elTd.select("td").get(2).text() + " (" +
                    "    data date PRIMARY KEY," +
                    "    price DOUBLE," +
                    "    capitalizationmlrdru DOUBLE," +
                    "    capitalizationmlrddollars DOUBLE," +
                    "    changesday DOUBLE," +
                    "    changesweek DOUBLE," +
                    "    changesmonth DOUBLE," +
                    "    changesyear DOUBLE" +
                    ")");
        } catch (Exception e) {
            saveDataTable(elTd);
        }
    }

    @Override
    public void saveDataTable(Element elTd) {

    }
}
