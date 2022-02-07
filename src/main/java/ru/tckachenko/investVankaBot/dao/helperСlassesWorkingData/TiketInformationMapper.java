package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TiketInformationMapper implements RowMapper<TikerInformation> {
    @Override
    public TikerInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
        TikerInformation tikerInformation = new TikerInformation();

        tikerInformation.setTicker(rs.getString("ticker"));
        tikerInformation.setNameCompany(rs.getString("nameCompany"));
        tikerInformation.setRankTicker(rs.getString("rankTicker"));
        tikerInformation.setSector(rs.getString("sector"));
        tikerInformation.setIndustry(rs.getString("industry"));
        tikerInformation.setCompanyDescription(rs.getString("companyDescription"));

        return tikerInformation;
    }
}
