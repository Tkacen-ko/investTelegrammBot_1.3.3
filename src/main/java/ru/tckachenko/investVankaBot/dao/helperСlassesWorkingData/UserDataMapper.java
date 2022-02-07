package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataMapper implements RowMapper<UserData> {
    @Override
    public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserData userDataMapper = new UserData();

        userDataMapper.setId(rs.getString("id"));
        userDataMapper.setFirstName(rs.getString("firstName"));
        userDataMapper.setLastName(rs.getString("lastName"));
        userDataMapper.setUserName(rs.getString("userName"));
        userDataMapper.setLanguageCode(rs.getString("languageCode"));
        userDataMapper.setAlertThresholdTickerGold(rs.getDouble("alertThresholdTickerGold"));
        userDataMapper.setAlertThresholdTickerSilver(rs.getDouble("alertThresholdTickerSilver"));
        userDataMapper.setAlertThresholdTickerBronze(rs.getDouble("alertThresholdTickerBronze"));
        userDataMapper.setAlertThresholdTickerDno(rs.getDouble("alertThresholdTickerDno"));
        userDataMapper.setNotificationFrequency(rs.getInt("notificationFrequency"));

        return userDataMapper;
    }
}