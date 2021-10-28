package ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@ComponentScan
public class LoadingTodayData extends BasicLoadingFunctionalityBd {


    {
        numberlColumsForParsing = 20;
    }
    public LoadingTodayData(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public void saitDataLoadDb(){
        if(siteDataParser()==null){
            for (int i = 0; i < 100; i++) {
                System.out.println("Судя по всему интернет пошёл по пизде");
                if (siteDataParser()!=null) break;
                if (i == 99){
                    System.out.println("По причине отсутствия интернета в течении дохуилиона часов, программа , сейчас будет завершена");
                }
                try {
                    Thread.sleep(3000);
                }
                catch (Exception e){
                }
            }
        }
        Elements allLines = siteDataParser();
        try {
            for (int i = 1; i <= allLines.size() - 1; i++) {
                Element line = allLines.get(i);
                if (line.select("td").size() == numberlColumsForParsing) {
                    createTable(line);
                    saveDataTable(line);
                }
            }
        } catch (Exception e) {
            System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nВнимание я влетел в блок иключения\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
            e.printStackTrace();
        }
    }


    @Override
    public void createTable(Element elTd) {

        try {
            jdbcTemplate.execute("create table "+"today"+elTd.select("td").get(3).text()+" (" +
                    "    data DATETIME PRIMARY KEY," +
                    "    price DOUBLE," +
                    "    capitalizationmlrdru DOUBLE," +
                    "    capitalizationmlrddollars DOUBLE," +
                    "    changesday DOUBLE," +
                    "    changesweek DOUBLE," +
                    "    changesmonth DOUBLE," +
                    "    changesyear DOUBLE" +
                    ")");
        }
        catch (Exception e){
            System.out.println("При попытке создать таблицу что то ебнулось");
        }
    }

    @Override
    public void saveDataTable(Element elTd) {
        try {
            jdbcTemplate.update("INSERT INTO "+"today"+elTd.select("td").get(3).text()+" VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    todayDate() +" "+elTd.select("td").get(1).text(),
                    ingratro(elTd.select("td").get(7).text())==000.000?null:ingratro(elTd.select("td").get(7).text()),
                    ingratro(elTd.select("td").get(14).text())==000.00?null:ingratro(elTd.select("td").get(14).text()),
                    ingratro(elTd.select("td").get(15).text())==000.00?null:ingratro(elTd.select("td").get(15).text()),
                    ingratro(elTd.select("td").get(8).text())==000.00?null:ingratro(elTd.select("td").get(8).text()),
                    ingratro(elTd.select("td").get(10).text())==000.00?null:ingratro(elTd.select("td").get(10).text()),
                    ingratro(elTd.select("td").get(11).text())==000.00?null:ingratro(elTd.select("td").get(11).text()),
                    ingratro(elTd.select("td").get(13).text())==000.00?null:ingratro(elTd.select("td").get(13).text())
            );
        }catch (Exception e){
            System.out.println("Что-то в методе добавления значений пошло по пизде");
        }
    }

    public void dropAllTodayTables(){
        Elements allLines = siteDataParser();
        try {
            for (int i = 1; i <= allLines.size() - 1; i++) {
                jdbcTemplate.update("DROP TABLE "+"today"+ allLines.select("td").get(3).text());
            }
        } catch (Exception e) {
            System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nВнимание я влетел в блок иключения\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
        }
    }

    @Override
    public String setUrl() {
        return "https://smart-lab.ru/q/shares/";
    }

    public String todayDate(){
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy:MM:dd");
        return formatForDateNow.format(new Date());
    }
}
