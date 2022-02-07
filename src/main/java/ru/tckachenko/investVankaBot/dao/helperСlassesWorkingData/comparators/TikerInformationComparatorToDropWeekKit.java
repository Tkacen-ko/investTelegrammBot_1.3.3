package ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators;

import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;

import java.util.Comparator;

public class TikerInformationComparatorToDropWeekKit implements Comparator<TikerInformation> {

    @Override
    public int compare(TikerInformation tickerInformation1, TikerInformation tickerInformation2) {
        return Double.compare(tickerInformation1.getChangesweek(), tickerInformation2.getChangesweek());
    }
}