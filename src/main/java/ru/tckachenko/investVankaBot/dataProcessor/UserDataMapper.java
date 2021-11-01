package ru.tckachenko.investVankaBot.dataProcessor;

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
        userDataMapper.setAlertThresholdTikerGold(rs.getDouble("alertThresholdTikerGold"));
        userDataMapper.setAlertThresholdTikerSilver(rs.getDouble("alertThresholdTikerSilver"));
        userDataMapper.setAlertThresholdTikerBronze(rs.getDouble("alertThresholdTikerBronze"));
        userDataMapper.setAlertThresholdTikerDno(rs.getDouble("alertThresholdTikerDno"));
        userDataMapper.setNotificationFrequency(rs.getInt("notificationFrequency"));

        return userDataMapper;
    }
}