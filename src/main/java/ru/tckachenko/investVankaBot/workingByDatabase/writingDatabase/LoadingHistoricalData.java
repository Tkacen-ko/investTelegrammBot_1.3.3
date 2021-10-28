package ru.tckachenko.investVankaBot.workingByDatabase.writingDatabase;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@ComponentScan
public class LoadingHistoricalData extends BasicLoadingFunctionalityBd{
    public static final String url = "https://smart-lab.ru/q/shares/order_by_issue_capitalization/desc/?date=";
    private String dateFormatToBD = "01.10.2021"; // Дата для старта анализа
    private Date dateStart;
    private static final Date nowDate = new Date();

    public LoadingHistoricalData(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    {
        try {
            dateStart = new SimpleDateFormat("dd.MM.yyyy").parse(dateFormatToBD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        numberlColumsForParsing = 18;
    }


    public void saitDataLoadDb(){
        try {
            do {
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
                if(allLines.size()==1){
                    System.out.println("Данных на этот день: " +getDateStart() +" нет");
                }
                try {
                    for (int i = 1; i <= allLines.size() - 1; i++) {
                        Element line = allLines.get(i);
                        if (line.select("td").size() == numberlColumsForParsing) {
                            createTable(line);
                            saveInformationAboutTiket(line, i);
                        }
                    }
                    changeDateStart();
                    System.out.println("Я отработал ещё одну итерацию цикла, послю 3 секундочки");
                    System.out.println();
                    Thread.sleep((int)((Math.random() * ((10000 - 1) + 1)) + 1));
                } catch (Exception e) {
                    System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nВнимание я влетел в блок иключения\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                }
            }
            while (dataBDLoadStarter());
        }
        finally {
            changeDateStart();
        }
    }

    // метод предоставляющий ссылку URL для считывания данных
    @Override
    public String setUrl() {
        if (dateStart.toString().equals(nowDate.toString())) {
            return null;
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String urlForReturn = url + formatForDateNow.format(dateStart);
        return urlForReturn;
    }

    public void changeDateStart(){
        SimpleDateFormat formatForDateNow2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateStart);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        dateFormatToBD = formatForDateNow2.format(dateStart);
        dateStart = instance.getTime();
    }

    public void createTable(Element elTd){
        if(elTd.select("td").get(2).text().length()==0){
            return;
        }
        try {
            jdbcTemplate.execute("create table "+elTd.select("td").get(2).text()+" (" +
                    "    data date PRIMARY KEY," +
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
            saveDataTable(elTd);
        }
    }


    public void saveDataTable(Element elTd){
        try {
            jdbcTemplate.update("INSERT INTO "+ elTd.select("td").get(2).text()+" VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    dateFormatToBD,
                    ingratro(elTd.select("td").get(6).text())==000.000?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(13).text())==000.00?null:ingratro(elTd.select("td").get(13).text()),
                    ingratro(elTd.select("td").get(14).text())==000.00?null:ingratro(elTd.select("td").get(14).text()),
                    ingratro(elTd.select("td").get(7).text())==000.00?null:ingratro(elTd.select("td").get(7).text()),
                    ingratro(elTd.select("td").get(9).text())==000.00?null:ingratro(elTd.select("td").get(9).text()),
                    ingratro(elTd.select("td").get(10).text())==000.00?null:ingratro(elTd.select("td").get(10).text()),
                    ingratro(elTd.select("td").get(12).text())==000.00?null:ingratro(elTd.select("td").get(12).text())
            );
            }catch (DuplicateKeyException e){
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\nЗначение под датой "+dateFormatToBD+" уже существует в БД\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
        }
    }
    public boolean dataBDLoadStarter() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String newTimeDite = formatForDateNow.format(new Date());
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateStart);
        instance.add(Calendar.DAY_OF_MONTH, -1);
        Date dateToCheck = instance.getTime();
        String dataStartTrueFormat = formatForDateNow.format(dateToCheck);
        if (newTimeDite.equals(dataStartTrueFormat)) {
            System.out.println("Программа записала все данные за указанный период\nПрограмма завершена");
        }
        return !(newTimeDite.equals(dataStartTrueFormat));
    }
    public String getDateStart(){
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        return formatForDateNow.format(dateStart);
    }
    public void setDateFormatToBD(String setDateFormatToBD){
        this.dateFormatToBD = setDateFormatToBD;
    }

    public void saveInformationAboutTiket(Element elTd, int rankTiket){
        try {
            jdbcTemplate.update("INSERT INTO allinformationabouttiket VALUES(?, ?, ?)",
                    elTd.select("td").get(2).text(),
                    elTd.select("td").get(1).text().replaceAll("[+% ]", ""),
                    setRankTiket(rankTiket)
            );
        }catch (DuplicateKeyException e){
            jdbcTemplate.update("UPDATE allinformationabouttiket SET ranktiket=? WHERE tiket =?;", setRankTiket(rankTiket), elTd.select("td").get(2).text());
        }
    }
    public String setRankTiket(int tiketСapitalization){

        if (tiketСapitalization<=10){
            return "gold";
        }
        else if (tiketСapitalization>11 && tiketСapitalization<=25){
            return "silver";
        }
        else if (tiketСapitalization>26 && tiketСapitalization<=50){
            return "bronze";
        }
        else{
            return "dno";
        }
    }
}

