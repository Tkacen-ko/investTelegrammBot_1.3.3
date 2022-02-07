package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData;

import lombok.Data;

@Data
public class TikerInformation {
    private String ticker;
    private String nameCompany;
    private String rankTicker;
    private double price;
    private double capitalizationmlrdru;
    private double capitalizationmlrddollars;
    private double changesday;
    private double changesweek;
    private double changesmonth;
    private double changesyear;
    private String sector;
    private String industry;
    private String companyDescription;
}
