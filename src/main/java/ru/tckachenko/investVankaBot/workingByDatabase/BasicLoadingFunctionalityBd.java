package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;


public abstract class BasicLoadingFunctionalityBd implements RequestsBDInterface {
    public int numberlColumsForParsing;



    public Elements siteDataParser(){

            String trueUrl = setUrl();
            if (trueUrl.length() == 0) {
                return null;
            }
            Document page;
            try {
                page = Jsoup.parse(new URL(trueUrl), 3000);
            }catch (IOException e){
                return null;
            }
            Element table = page.select("table[class=simple-little-table trades-table]").first();
            return table.getElementsByTag("tr");
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
                    }
                }
                changeDateStart();
                System.out.println("Я отработал ещё одну итерацию цикла, послю 3 секундочки");
                System.out.println();
                Thread.sleep((int)((Math.random() * ((3000 - 1) + 1)) + 1));
            } catch (Exception e) {
                System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nВнимание я влетел в блок иключения\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                e.printStackTrace();
            }
        }
        while (dataBDLoadStarter());
        }
        finally {
            changeDateStart();
        }
    }
    public abstract void changeDateStart();
    public abstract boolean dataBDLoadStarter();
    public abstract void createTable(Element elTd);
    public abstract void saveDataTable(Element elTd);
    public abstract String setUrl();
    public abstract String  getDateStart();

}
