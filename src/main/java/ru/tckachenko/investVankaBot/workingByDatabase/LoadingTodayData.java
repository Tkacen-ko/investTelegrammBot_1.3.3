package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.nodes.Element;

public class LoadingTodayData extends BasicLoadingFunctionalityBd {
    @Override
    public boolean dataBDLoadStarter() {
        return false;
    }

    @Override
    public void createTable(Element elTd) {

    }

    @Override
    public void saveDataTable(Element elTd) {

    }

    @Override
    public String setUrl() {
        return null;
    }

    @Override
    public String  getDateStart(){
        return null;
    }
}
