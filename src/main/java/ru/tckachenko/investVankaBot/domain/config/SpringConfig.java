package ru.tckachenko.investVankaBot.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.tckachenko.investVankaBot.dao.GetDataFromProperties;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.tckachenko.investVankaBot.domain.config")
public class SpringConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(new GetDataFromProperties().getData("driver.class.name.data.source"));
        dataSource.setUrl(new GetDataFromProperties().getData("url.data.source"));
        dataSource.setUsername(new GetDataFromProperties().getData("username.dataSource"));
        dataSource.setPassword(new GetDataFromProperties().getData("password.dataSource"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        return new DefaultBotOptions();
    }
}
