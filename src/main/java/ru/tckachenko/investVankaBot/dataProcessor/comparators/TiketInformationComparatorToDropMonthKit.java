package ru.tckachenko.investVankaBot.dataProcessor.comparators;

import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;

import java.util.Comparator;

public class TiketInformationComparatorToDropMonthKit implements Comparator<TiketInformation> {

    @Override
    public int compare(TiketInformation tiketInformation1, TiketInformation tiketInformation2) {
        if (tiketInformation1.getChangesmonth() == tiketInformation2.getChangesmonth())
            return 0;
        if (tiketInformation1.getChangesmonth() > tiketInformation2.getChangesmonth())
            return 1;
        else
            return -1;
    }
}