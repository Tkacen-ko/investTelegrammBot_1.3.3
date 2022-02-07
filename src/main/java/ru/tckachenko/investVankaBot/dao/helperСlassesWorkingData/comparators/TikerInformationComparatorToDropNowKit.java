package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators;

import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;

import java.util.Comparator;

public class TikerInformationComparatorToDropNowKit implements Comparator<TikerInformation> {

    @Override
    public int compare(TikerInformation tickerInformation1, TikerInformation tickerInformation2) {
        return Double.compare(tickerInformation1.getChangesday(), tickerInformation2.getChangesday());
    }
}