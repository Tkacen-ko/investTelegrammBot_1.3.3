package ru.tckachenko.investVankaBot.dataProcessor;

import lombok.Data;

@Data
public class TiketInformation {
    private String tiket;
    private String nameCompany;
    private String rankTiket;
    private int price;
    private double capitalizationmlrdru;
    private double capitalizationmlrddollars;
    private double changesday;
    private double changesweek;
    private double changesmonth;
    private double changesyear;
    }
