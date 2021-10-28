package ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.tckachenko.investVankaBot.dataProcessor.AllDataTikerRealTime;
import ru.tckachenko.investVankaBot.dataProcessor.AllDataTikerRealTimeMaper;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformationMapper;

import java.util.List;

@ComponentScan
public class GettingDataFromDatabase {
    private static List<TiketInformation> dataToTikerRealTime;
    protected final JdbcTemplate jdbcTemplate;
    @Autowired
    public GettingDataFromDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<TiketInformation> loadingDataToTikerRealTime(){
        List<TiketInformation> valuesWithoutCapitalization = jdbcTemplate.query("SELECT * FROM allinformationabouttiket ", new TiketInformationMapper());
        for (TiketInformation tiket : valuesWithoutCapitalization){
            String nameTiketToLowerCase = "today"+tiket.getTiket().toLowerCase();
            System.out.println(nameTiketToLowerCase);
            AllDataTikerRealTime allDataTikerRealTime = null;
            try {

                allDataTikerRealTime = jdbcTemplate.query("SELECT price ,capitalizationmlrdru, " +
                        "capitalizationmlrddollars, changesday, changesweek, changesmonth, changesyear FROM "
                                + nameTiketToLowerCase +" WHERE data = (SELECT MAX(data) FROM "+nameTiketToLowerCase+")",
                        new AllDataTikerRealTimeMaper()).stream().findFirst().orElse(null);
                tiket.setPrice(allDataTikerRealTime.getPrice());
                tiket.setCapitalizationmlrdru(allDataTikerRealTime.getCapitalizationmlrdru());
                tiket.setCapitalizationmlrddollars(allDataTikerRealTime.getCapitalizationmlrddollars());
                tiket.setChangesday(allDataTikerRealTime.getChangesday());
                tiket.setChangesweek(allDataTikerRealTime.getChangesweek());
                tiket.setChangesmonth(allDataTikerRealTime.getChangesmonth());
                tiket.setChangesyear(allDataTikerRealTime.getChangesyear());

            }catch (Exception e){
                System.out.println("\n!\nДанные по данному тикеру отсутствуют\n!\n");
                //e.printStackTrace();
            }
        }
        dataToTikerRealTime = valuesWithoutCapitalization;
        return valuesWithoutCapitalization;
    }
    public List<TiketInformation> getDataToTikerRealTime(){
        return dataToTikerRealTime;
    }

}
