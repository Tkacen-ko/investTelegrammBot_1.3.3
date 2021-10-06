package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static Date dateStart;
    private static String dateDataBD;
    private static final Date nowDate = new Date();
    private final JdbcTemplate jdbcTemplate;
    {
        numberlColumsForParsing = 18;

    }

    static {
        try {
            dateStart = new SimpleDateFormat("dd.MM.yyyy").parse("18.11.2015");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public LoadingHistoricalData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // метод для чистки значений от лишних знаков
    public Double ingratro(String s) {
        if (s.length() != 0) {
            System.out.println(s.replaceAll("[+% ]", ""));
            return Double.parseDouble(s.replaceAll("[% ]", ""));
        }
        return 000.000;
    }

    // метод предоставляющий ссылку URL для считывания данных
    @Override
    public String setUrl() {
        if (dateStart.toString().equals(nowDate.toString())) {
            return null;
        }
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatForDateNow2 = new SimpleDateFormat("yyyy-MM-dd");
        String urlForReturn = url + formatForDateNow.format(dateStart);
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateStart);
        instance.add(Calendar.DAY_OF_MONTH, 1);
        dateDataBD = formatForDateNow2.format(dateStart);
        dateStart = instance.getTime();
        return urlForReturn;
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
                    dateDataBD,
                    ingratro(elTd.select("td").get(6).text())==000.000?null:ingratro(elTd.select("td").get(6).text()),
                    ingratro(elTd.select("td").get(13).text())==000.00?null:ingratro(elTd.select("td").get(13).text()),
                    ingratro(elTd.select("td").get(14).text())==000.00?null:ingratro(elTd.select("td").get(14).text()),
                    ingratro(elTd.select("td").get(7).text())==000.00?null:ingratro(elTd.select("td").get(7).text()),
                    ingratro(elTd.select("td").get(9).text())==000.00?null:ingratro(elTd.select("td").get(9).text()),
                    ingratro(elTd.select("td").get(10).text())==000.00?null:ingratro(elTd.select("td").get(10).text()),
                    ingratro(elTd.select("td").get(12).text())==000.00?null:ingratro(elTd.select("td").get(12).text())
            );
        }catch (DuplicateKeyException e){
            System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\nЗначение под датой "+dateDataBD+" уже существует в БД\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
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
}

