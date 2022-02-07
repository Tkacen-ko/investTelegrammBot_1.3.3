package ru.tckachenko.investVankaBot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.UserData;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.dao.writingDatabase.RecordingUserData;

import java.util.List;

@Component
public class CheckingUser {
    private final GettingDataFromDatabase gettingDataFromDatabase;
    private final RecordingUserData recordingUserData;

    @Autowired
    public CheckingUser(GettingDataFromDatabase gettingDataFromDatabase, RecordingUserData recordingUserData) {
        this.gettingDataFromDatabase = gettingDataFromDatabase;
        this.recordingUserData = recordingUserData;
    }

    public void check(Message message) {
        List<UserData> userData = GettingDataFromDatabase.userInformationRealTime;
        try {
            if (!(userData.stream().anyMatch(p -> p.getId().equals(message.getChatId().toString())))) {
                recordingUserData.saveUserData(message);
                gettingDataFromDatabase.loadingUserData();
            }
        } catch (DuplicateKeyException e) {
            System.out.println("Пользователь " + message.getFrom().getUserName() + " уже существует в базе");
        }

    }


}
