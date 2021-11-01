package ru.tckachenko.investVankaBot.dataProcessor;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Data
public class UserData {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String languageCode;
    private double alertThresholdTikerGold;
    private double alertThresholdTikerSilver;
    private double alertThresholdTikerBronze;
    private double alertThresholdTikerDno;
    private int notificationFrequency;
}
