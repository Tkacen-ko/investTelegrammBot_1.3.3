package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class UserData {
    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String languageCode;
    private double alertThresholdTickerGold;
    private double alertThresholdTickerSilver;
    private double alertThresholdTickerBronze;
    private double alertThresholdTickerDno;
    private int notificationFrequency;
}
