package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators;

import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;

import java.util.Comparator;

public class TikerInformationComparatorToGrowingNowKit implements Comparator<TikerInformation> {

    @Override
    public int compare(TikerInformation tickerInformation1, TikerInformation tickerInformation2) {
        return Double.compare(tickerInformation2.getChangesday(), tickerInformation1.getChangesday());
    }
}