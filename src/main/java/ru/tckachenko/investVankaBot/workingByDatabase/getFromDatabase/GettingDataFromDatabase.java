package ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformationMapper;

import java.util.List;

public class GettingDataFromDatabase {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
    private JdbcTemplate jdbcTemplate = bean.getBean("jdbcTemplate", JdbcTemplate.class);

    public List<TiketInformation> getAllTables(){
        return jdbcTemplate.query("SHOW TABLES", new TiketInformationMapper());
    }
}
