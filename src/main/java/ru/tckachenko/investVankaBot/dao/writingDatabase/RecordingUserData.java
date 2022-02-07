package ru.tckachenko.investVankaBot.dao.writingDatabase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class RecordingUserData {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RecordingUserData(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUserData(Message message) {
        try {
            jdbcTemplate.update("INSERT INTO userdata VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    message.getChatId().toString(),
                    message.getFrom().getFirstName(),
                    message.getFrom().getLastName(),
                    message.getFrom().getUserName(),
                    message.getFrom().getLanguageCode(),
                    0.0,
                    0.0,
                    0.0,
                    0.0,
                    0
            );
        } catch (DuplicateKeyException e) {
            System.out.println("A user with this name already exists");
        }
    }
}
