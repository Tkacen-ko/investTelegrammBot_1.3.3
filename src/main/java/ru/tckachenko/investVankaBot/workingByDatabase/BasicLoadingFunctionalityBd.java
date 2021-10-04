package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

public abstract class BasicLoadingFunctionalityBd implements RequestsBDInterface {
    String url;
    public int numberlColumsForParsing;


    public Elements siteDataParser(){

            String trueUrl = setUrl();
            if (trueUrl.length() == 0) {
                return null;
            }
            Document page = null;
            try {
                page = Jsoup.parse(new URL(trueUrl), 3000);
            }catch (IOException e){
                System.out.println("Судя по всему интернет пошёл по пизде");
            }
            Element table = page.select("table[class=simple-little-table trades-table]").first();
            return table.getElementsByTag("tr");
    }

    public void saitDataLoadDb(){
        do {
            Elements allLines = siteDataParser();
            try {
                for (int i = 1; i <= allLines.size() - 1; i++) {
                    Element line = allLines.get(i);
                    if (line.select("td").size() == numberlColumsForParsing) {
                        createTable(line);
                    }
                }
            } catch (Exception e) {
                System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nВнимание я влетел в блок иключения\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
                e.printStackTrace();
            }
        }
        while (dataBDLoadStarter());
    }
    public abstract boolean dataBDLoadStarter();
    public abstract void createTable(Element elTd);
    public abstract void saveDataTable(Element elTd);
    public abstract String setUrl();

}
