package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators.TikerInformationComparatorToCapitalizationmlrdru;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators.TikerInformationComparatorToGrowingYearKit;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrowingYearKit extends AnswerWithTextOnly {
    private int numb;
    private final GettingDataFromDatabase gettingDataFromDatabase;

    @Autowired
    public GrowingYearKit(GettingDataFromDatabase gettingDataFromDatabase) {
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }

    public List<String> getButtons() {
        return null;
    }

    public String getMessage() {
        numb = 1;
        String message = "Среди акции категории GOLD\uD83E\uDD47 в лидерах роста, за год, находятся:\n";
        message += gettingDataFromDatabase.getDataToTickerRealTime()
                .stream()
                .filter(s -> s.getPrice() != 0.0)
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru()).limit(10)
                .sorted(new TikerInformationComparatorToGrowingYearKit()).limit(5)
                .filter(s -> s.getChangesyear() > 0.0)
                .map((p) -> (numb++) + ". " + p.getTicker() + " (+" + p.getChangesyear() + "%)\n").collect(Collectors.joining());
        numb = 1;
        message += "\nСреди акции категории SILVER\uD83E\uDD48 в лидерах роста, за год, находятся:\n" + gettingDataFromDatabase.getDataToTickerRealTime()
                .stream()
                .filter(s -> s.getPrice() != 0.0)
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .skip(10)
                .limit(25)
                .sorted(new TikerInformationComparatorToGrowingYearKit())
                .limit(10)
                .filter(s -> s.getChangesyear() > 0.0)
                .map((p) -> (numb++) + ". " + p.getTicker() + " (+" + p.getChangesyear() + "%)\n").collect(Collectors.joining());
        numb = 1;
        message += "\nСреди акции категории BRONZE\uD83E\uDD49 в лидерах роста, за год, находятся:\n" + gettingDataFromDatabase.getDataToTickerRealTime()
                .stream()
                .filter(s -> s.getPrice() != 0.0)
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .skip(25)
                .limit(50)
                .sorted(new TikerInformationComparatorToGrowingYearKit()).limit(10)
                .filter(s -> s.getChangesyear() > 0.0)
                .map((p) -> (numb++) + ". " + p.getTicker() + " (+" + p.getChangesyear() + "%)\n").collect(Collectors.joining());
        numb = 1;
        message += "\nСреди акции категории DNO\uD83C\uDFA2 в лидерах роста, за год, находятся:\n" + gettingDataFromDatabase.getDataToTickerRealTime()
                .stream()
                .filter(s -> s.getPrice() != 0.0)
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru())
                .skip(50)
                .sorted(new TikerInformationComparatorToGrowingYearKit()).limit(10)
                .filter(s -> s.getChangesyear() > 0.0)
                .map((p) -> (numb++) + ". " + p.getTicker() + " (+" + p.getChangesyear() + "%)\n").collect(Collectors.joining());
        return message;
    }
}