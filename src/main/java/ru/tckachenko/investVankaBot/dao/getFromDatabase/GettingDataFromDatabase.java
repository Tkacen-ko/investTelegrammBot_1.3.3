package ru.tckachenko.investVankaBot.dao.getFromDatabase;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GettingDataFromDatabase {
    public static List<TikerInformation> dataToTickerRealTime;
    public static List<UserData> userInformationRealTime;
    protected final JdbcTemplate jdbcTemplate;

    public void loadingDataToTikerRealTime() {
        List<TikerInformation> valuesWithoutCapitalization = jdbcTemplate.query("SELECT * FROM allinformationaboutticker ", new TiketInformationMapper());
        for (TikerInformation ticker : valuesWithoutCapitalization) {
            String nameTickerToLowerCase = "today" + ticker.getTicker().toLowerCase();
            AllDataTikerRealTime allDataTikerRealTime;
            try {
                allDataTikerRealTime = jdbcTemplate.query("SELECT price ,capitalizationmlrdru, " +
                                "capitalizationmlrddollars, changesday, changesweek, changesmonth, changesyear FROM "
                                + nameTickerToLowerCase + " WHERE data = (SELECT MAX(data) FROM " + nameTickerToLowerCase + ")",
                        new AllDataTikerRealTimeMaper()).stream().findFirst().get();
                //optional переделать
                ticker.setPrice(allDataTikerRealTime.getPrice());
                ticker.setCapitalizationmlrdru(allDataTikerRealTime.getCapitalizationmlrdru());
                ticker.setCapitalizationmlrddollars(allDataTikerRealTime.getCapitalizationmlrddollars());
                ticker.setChangesday(allDataTikerRealTime.getChangesday());
                ticker.setChangesweek(allDataTikerRealTime.getChangesweek());
                ticker.setChangesmonth(allDataTikerRealTime.getChangesmonth());
                ticker.setChangesyear(allDataTikerRealTime.getChangesyear());
            } catch (Exception e) {
                System.out.println("\n!\nДанные по данному тикеру отсутствуют\n!\n");
            }
        }
        dataToTickerRealTime = valuesWithoutCapitalization;
    }

    public List<TikerInformation> getDataToTickerRealTime() {
        return dataToTickerRealTime;
    }

    public void loadingUserData() {
        List<UserData> valuesWithoutCapitalization = jdbcTemplate.query("SELECT * FROM userdata", new UserDataMapper());
        userInformationRealTime = valuesWithoutCapitalization;
    }

    public void updateUserTikerValue(double alertThresholdTickerGold, String id, String tickerType) {
        jdbcTemplate.update("UPDATE userdata SET alertThresholdTicker" + tickerType + "=? WHERE id=?",
                alertThresholdTickerGold, id);
    }

    public void updateFrequencyNotifications(int notificationFrequency, String id) {
        jdbcTemplate.update("UPDATE userdata SET notificationFrequency=? WHERE id=?",
                notificationFrequency, id);
    }
}
