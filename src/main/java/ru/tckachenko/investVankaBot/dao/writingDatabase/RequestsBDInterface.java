package ru.tckachenko.investVankaBot.dao.writingDatabase;

import org.jsoup.nodes.Element;

public interface RequestsBDInterface {
    void createTable(Element elTd);

    void saveDataTable(Element elTd);
}
