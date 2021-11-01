package ru.tckachenko.investVankaBot.workingByDatabase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.dataProcessor.UserData;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase.RecordingUserData;

import java.util.List;

public class CheckingUser {
    Message message;
    public CheckingUser(Message message){
        this.message = message;
    }
    public void check(){
        List<UserData> userData = GettingDataFromDatabase.userInformationRealTime;
        try {
            if (!(userData.stream().anyMatch(p -> p.getId().equals(message.getChatId().toString())))){
                new RecordingUserData(message).saveUserData();
                new AnnotationConfigApplicationContext(SpringConfig.class)
                        .getBean(GettingDataFromDatabase.class)
                        .loadingUserData();

            }
        }catch (DuplicateKeyException e){
            System.out.println("Пользователь "+message.getFrom().getUserName() +" уже существует в базе");
        }

    }


}
