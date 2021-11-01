package ru.tckachenko.investVankaBot.dataProcessor.comparators;

import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;

import java.util.Comparator;

public class TiketInformationComparatorToDropWeekKit implements Comparator<TiketInformation> {

    @Override
    public int compare(TiketInformation tiketInformation1, TiketInformation tiketInformation2) {
        if (tiketInformation1.getChangesweek() == tiketInformation2.getChangesweek())
            return 0;
        if (tiketInformation1.getChangesweek() > tiketInformation2.getChangesweek())
            return 1;
        else
            return -1;
    }
}