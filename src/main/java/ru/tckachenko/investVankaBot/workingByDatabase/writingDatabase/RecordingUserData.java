package ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tckachenko.investVankaBot.config.SpringConfig;

public class RecordingUserData {
    Message message;
    public RecordingUserData(Message message){
        this.message = message;
    }
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
    private JdbcTemplate jdbcTemplate = bean.getBean("jdbcTemplate", JdbcTemplate.class);

    public void saveUserData(){
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
        }catch (DuplicateKeyException e){
            System.out.println("\n\n\nШото таки не так пошло в классе RecordingUserData(Вероятно мы пытаемся создать пользователя который уже есть!\n\n\n");
            e.printStackTrace();
        }
    }
}
