package ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase;

import org.jsoup.nodes.Element;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tckachenko.investVankaBot.config.SpringConfig;

public class LoadingTiketInformation implements RequestsBDInterface{
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
    private JdbcTemplate jdbcTemplate = bean.getBean("jdbcTemplate", JdbcTemplate.class);
    
    @Override
    public void createTable(Element elTd) {
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
            saveDataTable(elTd);
        }
    }

    @Override
    public void saveDataTable(Element elTd) {

    }
}
