package ru.tckachenko.investVankaBot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;
import ru.tckachenko.investVankaBot.dao.writingDatabase.LoadingTodayData;

@Component
public class BasicInformation {
    private final GettingDataFromDatabase gettingDataFromDatabase;
    private final LoadingTodayData loadingTodayData;

    @Autowired
    public BasicInformation(LoadingTodayData loadingTodayData, GettingDataFromDatabase gettingDataFromDatabase) {
        this.loadingTodayData = loadingTodayData;
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }

    public void getBasicInformation() {

        loadingTodayData.siteDataLoadDb();
        gettingDataFromDatabase.loadingDataToTikerRealTime();
        gettingDataFromDatabase.loadingUserData();
    }

    public void startDataInitialization() {
        gettingDataFromDatabase.loadingUserData();
        gettingDataFromDatabase.loadingDataToTikerRealTime();
    }

}
