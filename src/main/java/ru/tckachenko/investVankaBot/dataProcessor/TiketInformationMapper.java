package ru.tckachenko.investVankaBot.dataProcessor;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiketInformationMapper implements RowMapper<TiketInformation> {
    @Override
    public TiketInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        TiketInformation tiketInformation = new TiketInformation();

        tiketInformation.setTiket(rs.getString("tiket"));
        tiketInformation.setNameCompany(rs.getString("nameCompany"));
        tiketInformation.setRankTiket(rs.getString("rankTiket"));

        return tiketInformation;
    }
}
