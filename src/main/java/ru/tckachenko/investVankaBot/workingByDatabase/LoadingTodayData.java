package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.nodes.Element;

import java.util.Date;

public class LoadingTodayData extends BasicLoadingFunctionalityBd {

    // неиспользуемый в данной реализации метод, нужно затереть из родительского класса!!!
    @Override
    public void changeDateStart() {
    }

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
