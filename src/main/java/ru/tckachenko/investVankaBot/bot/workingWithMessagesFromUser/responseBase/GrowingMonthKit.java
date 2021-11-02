package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.config.SpringConfig;
import ru.tckachenko.investVankaBot.dataProcessor.comparators.TiketInformationComparatorToCapitalizationmlrdru;
import ru.tckachenko.investVankaBot.dataProcessor.comparators.TiketInformationComparatorToGrowingMonthKit;
import ru.tckachenko.investVankaBot.dataProcessor.comparators.TiketInformationComparatorToGrowingWeekKit;
import ru.tckachenko.investVankaBot.workingByDatabase.getFromDatabase.GettingDataFromDatabase;

import java.util.List;
import java.util.stream.Collectors;

public class GrowingMonthKit extends AnswerWithTextOnly {
    AnnotationConfigApplicationContext bean = new AnnotationConfigApplicationContext(SpringConfig.class);
    GettingDataFromDatabase gettingDataFromDatabase = bean.getBean(GettingDataFromDatabase.class);
    int numb;
    public String message;



    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        numb = 1;
        message = "Среди акции категории GOLD в лидерах роста, за месяц, находятся:\n";
        message += gettingDataFromDatabase.getDataToTikerRealTime()
                .stream()
                .filter(s->s.getPrice()!=0.0)
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru()).limit(10)
                .sorted(new TiketInformationComparatorToGrowingMonthKit()).limit(5)
                .filter(s->s.getChangesmonth()>0.0)
                .map((p)->(numb++)+". "+p.getTiket() +" (+"+ p.getChangesmonth()+"%)\n").collect(Collectors.joining());
        numb = 1;
        message += "\nСреди акции категории BRONZE в лидерах роста, за месяц, находятся:\n" + gettingDataFromDatabase.getDataToTikerRealTime()
                .stream()
                .filter(s->s.getPrice()!=0.0)
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .skip(10)
                .limit(25)
                .sorted(new TiketInformationComparatorToGrowingMonthKit())
                .limit(10)
                .filter(s->s.getChangesmonth()>0.0)
                .map((p)->(numb++)+". "+p.getTiket() +" (+"+ p.getChangesmonth()+"%)\n").collect(Collectors.joining());
        numb = 1;
        message += "\nСреди акции категории SILVER в лидерах роста, за месяц, находятся:\n" + gettingDataFromDatabase.getDataToTikerRealTime()
                .stream()
                .filter(s->s.getPrice()!=0.0)
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .skip(25)
                .limit(50)
                .sorted(new TiketInformationComparatorToGrowingMonthKit()).limit(10)
                .filter(s->s.getChangesmonth()>0.0)
                .map((p)->(numb++)+". "+p.getTiket() +" (+"+ p.getChangesmonth()+"%)\n").collect(Collectors.joining());
        numb = 1;
        message += "\nСреди акции категории DNO в лидерах роста, за месяц, находятся:\n" + gettingDataFromDatabase.getDataToTikerRealTime()
                .stream()
                .filter(s->s.getPrice()!=0.0)
                .sorted(new TiketInformationComparatorToCapitalizationmlrdru())
                .skip(50)
                .sorted(new TiketInformationComparatorToGrowingMonthKit()).limit(10)
                .filter(s->s.getChangesmonth()>0.0)
                .map((p)->(numb++)+". "+p.getTiket() +" (+"+ p.getChangesmonth()+"%)\n").collect(Collectors.joining());
        return message;
    }
}