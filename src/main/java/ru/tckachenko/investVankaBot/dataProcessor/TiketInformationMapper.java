package ru.tckachenko.investVankaBot.dataProcessor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiketInformationMapper implements RowMapper<TiketInformation> {
    @Override
    public TiketInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiketInformation tiketInformation = new TiketInformation();
        tiketInformation.setTiket(rs.getString("Tables_in_smart_lab_db"));
        return tiketInformation;
    }
}
