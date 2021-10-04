package ru.tckachenko.investVankaBot.workingByDatabase;

import org.jsoup.nodes.Element;

public interface RequestsBDInterface {
    public void createTable(Element elTd);
    public void saveDataTable(Element elTd);
}
