package ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.dataProcessor.UserData;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.io.IOException;
import java.net.URL;

public class CompanyInformation {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
    private JdbcTemplate jdbcTemplate = bean.getBean("jdbcTemplate", JdbcTemplate.class);

    public void dataRecordingTableAllInformation(){
        for (TiketInformation tiketInformation : GettingDataFromDatabase.dataToTikerRealTime){
            String dataSite = getDataWebsite(tiketInformation.getTiket());
            if (dataSite==null){
                continue;
            }
            System.out.println(dataSite);
            System.out.println();
            if (tiketInformation.getCompanyDescription() == null) {
                jdbcTemplate.update("UPDATE allinformationabouttiket " +
                        "SET companyDescription=? WHERE tiket=?", dataSite, tiketInformation.getTiket());
                System.out.println("\n\n!!!Дядя! Тут шо то кого-то сработало и мы чёт записали!!!\n\n");
            }
        }
    }


    public String getDataWebsite(String tikerName){
        String dataSite = null;
        String trueUrl = "https://www.tinkoff.ru/invest/stocks/"+tikerName+"/";
        System.out.println(trueUrl);
        Document page = null;
        try {
            page = Jsoup.parse(new URL(trueUrl), 3000);
        }catch (IOException e){
        }
        try {
            Element s = page.select("[class=TextLineCollapse__sizeS_21XX1]").first();
            Element s2 = s.select("[data-qa-file=SecurityInfo]").first();
            dataSite = s2.select("[data-qa-file=SecurityInfo]").get(1).text();
        }catch (Exception e){
            System.out.println("Данных по данному тикеру нет");
        }
        return dataSite;
    }
}
