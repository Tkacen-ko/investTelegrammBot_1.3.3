package ru.tckachenko.investVankaBot.dataProcessor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AllDataTikerRealTimeMaper implements RowMapper<AllDataTikerRealTime> {
    @Override
    public AllDataTikerRealTime mapRow(ResultSet rs, int rowNum) throws SQLException {
        AllDataTikerRealTime tiketCapitalization = new AllDataTikerRealTime();

        tiketCapitalization.setPrice(rs.getInt("price"));
        tiketCapitalization.setCapitalizationmlrdru(rs.getDouble("capitalizationmlrdru"));
        tiketCapitalization.setCapitalizationmlrddollars(rs.getDouble("capitalizationmlrddollars"));
        tiketCapitalization.setChangesday(rs.getDouble("changesday"));
        tiketCapitalization.setChangesweek(rs.getDouble("changesweek"));
        tiketCapitalization.setChangesmonth(rs.getDouble("changesmonth"));
        tiketCapitalization.setChangesyear(rs.getDouble("changesyear"));

        return tiketCapitalization;
    }
}
