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
        tiketInformation.setSector(rs.getString("sector"));
        tiketInformation.setIndustry(rs.getString("industry"));
        tiketInformation.setCompanyDescription(rs.getString("companyDescription"));

        return tiketInformation;
    }
}
