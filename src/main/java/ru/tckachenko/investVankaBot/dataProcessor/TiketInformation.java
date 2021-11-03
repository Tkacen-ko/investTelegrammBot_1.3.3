package ru.tckachenko.investVankaBot.dataProcessor;

import lombok.Data;

@Data
public class TiketInformation {
    private String tiket;
    private String nameCompany;
    private String rankTiket;
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
