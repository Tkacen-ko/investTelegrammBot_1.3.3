package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.comparators.TikerInformationComparatorToCapitalizationmlrdru;
import ru.tckachenko.investVankaBot.dao.getFromDatabase.GettingDataFromDatabase;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RatingCapitalistsKit extends AnswerWithTextOnly {
    private final GettingDataFromDatabase gettingDataFromDatabase;
    int numb = 1;

    @Autowired
    public RatingCapitalistsKit(GettingDataFromDatabase gettingDataFromDatabase) {
        this.gettingDataFromDatabase = gettingDataFromDatabase;
    }


    @Override
    public String getMessage() {

        String message = gettingDataFromDatabase.getDataToTickerRealTime().stream().filter(s -> s.getPrice() != 0.0000)
                .sorted(new TikerInformationComparatorToCapitalizationmlrdru()).limit(50)
                .map((p) -> (numb++) + ". " + p.getTicker() + " - " + p.getCapitalizationmlrdru() + " (млрд. руб.)\n").collect(Collectors.joining());
        numb = 1;
        return message;
    }

    @Override
    public List<String> getButtons() {
        return null;
    }
}
