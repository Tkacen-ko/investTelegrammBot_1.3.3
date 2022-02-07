package ru.tckachenko.investVankaBot.dao;

import java.io.FileInputStream;
import java.util.Properties;

public class GetDataFromProperties {
    private FileInputStream fis;
    private final Properties property = new Properties();
    private String myData;

    public String getData(String name) {

        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            myData = property.getProperty(name);

        } catch (Exception e) {
            System.err.println("ОШИБКА: Файл свойств отсутствует!");
        }
        return myData;
    }
}
