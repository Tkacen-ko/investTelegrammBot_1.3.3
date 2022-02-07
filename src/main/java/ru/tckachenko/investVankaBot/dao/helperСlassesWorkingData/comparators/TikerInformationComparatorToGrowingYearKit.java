package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators;

import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;

import java.util.Comparator;

public class TikerInformationComparatorToGrowingYearKit implements Comparator<TikerInformation> {

    @Override
    public int compare(TikerInformation tickerInformation1, TikerInformation tickerInformation2) {
        if (tickerInformation1.getChangesyear() == tickerInformation2.getChangesyear())
            return 0;
        if (tickerInformation1.getChangesyear() > tickerInformation2.getChangesyear())
            return -1;
        else
            return 1;
    }
}