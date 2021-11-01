package ru.tckachenko.investVankaBot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.tckachenko.investVankaBot.bot.SendMessageUserTelegtam;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.MailingMasegeToUsers;
import ru.tckachenko.investVankaBot.dataProcessor.UserData;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingHistoricalData;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.LoadingTodayData;


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
    public LoadingHistoricalData loadingHistoricalData(){
        return new LoadingHistoricalData(jdbcTemplate());
    }

    @Bean
    public LoadingTodayData loadingTodayData(){return new LoadingTodayData(jdbcTemplate());
    }

    @Bean
    public DefaultBotOptions defaultBotOptions(){return new DefaultBotOptions();}

    @Bean
    public SendMessageUserTelegtam sendMessageUserTelegtam(){ return new SendMessageUserTelegtam(defaultBotOptions());}

    @Bean
    public GettingDataFromDatabase gettingDataFromDatabase(){return new GettingDataFromDatabase(jdbcTemplate());}

    @Bean
    public UserData userData(){return new UserData();}

    @Bean
    public MailingMasegeToUsers mailingMasegeToUsers(){return new MailingMasegeToUsers(userData());}
}
