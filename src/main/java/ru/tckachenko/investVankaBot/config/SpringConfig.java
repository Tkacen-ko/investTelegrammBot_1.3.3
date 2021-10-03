package ru.tckachenko.investVankaBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.tckachenko.investVankaBot.workingByDatabase.LoadingHistoricalData;


import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.tckachenko.investVankaBot.config")
public class SpringConfig {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/smart_lab_db?serverTimezone=Europe/Moscow");
        dataSource.setUsername("Router");
        dataSource.setPassword("coZ&rma6/Lrm");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public LoadingHistoricalData parser(){
        return new LoadingHistoricalData(jdbcTemplate());
    }

}
