package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.comparators.TiketInformationComparatorToCapitalizationmlrdru;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.List;
import java.util.stream.Collectors;

public class RatingCapitalistsKit extends AnswerWithTextOnly {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
    GettingDataFromDatabase gettingDataFromDatabase = bean.getBean(GettingDataFromDatabase.class);
    int numb= 1;

    public String message = gettingDataFromDatabase.getDataToTikerRealTime().stream().filter(s->s.getPrice()!=0.0)
            .sorted(new TiketInformationComparatorToCapitalizationmlrdru()).limit(50)
            .map((p)->(numb++)+". "+p.getTiket() + " - "+p.getCapitalizationmlrdru() + " (млрд. руб.)\n").collect(Collectors.joining());

    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public List<String> getButtons() {
        return null;
    }
}
