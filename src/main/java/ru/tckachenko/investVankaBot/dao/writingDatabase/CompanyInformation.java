package ru.tckachenko.investVankaBot.dao.writingDatabase;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.net.URL;

@Component
public class CompanyInformation {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyInformation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void dataRecordingTableAllInformation() {
        for (TikerInformation tikerInformation : GettingDataFromDatabase.dataToTickerRealTime) {
            String dataSite = getDataWebsite(tikerInformation.getTicker());
            if (dataSite != null) {
                if (tikerInformation.getCompanyDescription() == null) {
                    jdbcTemplate.update("UPDATE allinformationabouttiket " +
                            "SET companyDescription=? WHERE tiket=?", dataSite, tikerInformation.getTicker());
                }
            }
        }
    }

    @SneakyThrows
    public String getDataWebsite(String tikerName) {
        String dataSite = null;
        String trueUrl = "https://www.tinkoff.ru/invest/stocks/" + tikerName + "/";
        System.out.println(trueUrl);
        Document page = Jsoup.parse(new URL(trueUrl), 3000);

        try {
            Element s = page.select("[class=TextLineCollapse__sizeS_21XX1]").first();
            Element s2 = s.select("[data-qa-file=SecurityInfo]").first();
            dataSite = s2.select("[data-qa-file=SecurityInfo]").get(1).text();
        } catch (Exception e) {
            System.out.println("Данных по данному тикеру нет");
        }
        return dataSite;
    }
}
