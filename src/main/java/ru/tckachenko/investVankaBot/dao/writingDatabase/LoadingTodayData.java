package ru.tckachenko.investVankaBot.dao.writingDatabase;

import lombok.SneakyThrows;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LoadingTodayData extends BasicLoadingFunctionalityBd {

    @Autowired
    public LoadingTodayData(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        numberColumnsForParsing = 20;
    }

    @SneakyThrows
    public void siteDataLoadDb() {
        Elements allLines = siteDataParser();
        try {
            for (int i = 1; i <= allLines.size() - 1; i++) {
                Element line = allLines.get(i);
                if (line.select("td").size() == numberColumnsForParsing) {
                    createTable(line);
                    saveDataTable(line);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred when requesting data");
        }
    }


    @Override
    public void createTable(Element elTd) {

        try {
            jdbcTemplate.execute("create table " + "today" + elTd.select("td").get(3).text() + " (" +
                    "    data DATETIME PRIMARY KEY," +
                    "    price DOUBLE," +
                    "    capitalizationmlrdru DOUBLE," +
                    "    capitalizationmlrddollars DOUBLE," +
                    "    changesday DOUBLE," +
                    "    changesweek DOUBLE," +
                    "    changesmonth DOUBLE," +
                    "    changesyear DOUBLE" +
                    ")");
        } catch (Exception e) {
            System.out.println("Error creating the table");
        }
    }

    @Override
    public void saveDataTable(Element elTd) {
        try {
            jdbcTemplate.update("INSERT INTO " + "today" + elTd.select("td").get(3).text() + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    todayDate() + " " + elTd.select("td").get(1).text(),
                    deletingСharacters(elTd.select("td").get(7).text()) == 000.000 ? null : deletingСharacters(elTd.select("td").get(7).text()),
                    deletingСharacters(elTd.select("td").get(14).text()) == 000.00 ? null : deletingСharacters(elTd.select("td").get(14).text()),
                    deletingСharacters(elTd.select("td").get(15).text()) == 000.00 ? null : deletingСharacters(elTd.select("td").get(15).text()),
                    deletingСharacters(elTd.select("td").get(8).text()) == 000.00 ? null : deletingСharacters(elTd.select("td").get(8).text()),
                    deletingСharacters(elTd.select("td").get(10).text()) == 000.00 ? null : deletingСharacters(elTd.select("td").get(10).text()),
                    deletingСharacters(elTd.select("td").get(11).text()) == 000.00 ? null : deletingСharacters(elTd.select("td").get(11).text()),
                    deletingСharacters(elTd.select("td").get(13).text()) == 000.00 ? null : deletingСharacters(elTd.select("td").get(13).text())
            );
        } catch (Exception e) {
            System.out.println("An error occurred while adding the value");
        }
    }

    public void dropAllTodayTables() {
        Elements allLines = siteDataParser();
        try {
            for (int i = 1; i <= allLines.size() - 1; i++) {
                jdbcTemplate.update("DROP TABLE " + "today" + allLines.select("td").get(3).text());
            }
        } catch (Exception e) {
            System.out.println("An error occurred when deleting tables");
        }
    }

    @Override
    public String setUrl() {
        return "https://smart-lab.ru/q/shares/";
    }

    public String todayDate() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy:MM:dd");
        return formatForDateNow.format(new Date());
    }
}
