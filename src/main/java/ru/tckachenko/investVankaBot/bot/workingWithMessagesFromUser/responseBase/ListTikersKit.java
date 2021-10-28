package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;

import java.util.List;

public class ListTikersKit extends AnswerWithTextOnly {
    private List<TiketInformation> getNameTiker;
    public ListTikersKit(List<TiketInformation> getNameTiker){
        this.getNameTiker = getNameTiker;
    }

    public String message = "Перечень тикеров по которым " +
            "можно получить базовую, текущую информацию."+
            "Отправьте ответным сообщением наименование тикера"+
            "(короткое буквенное обозначение ценное бумаги (Например GAZP, SBER, YNDX)), "+
            "о котором вы хотите получить данные:\n";

    @Override
    public String getMessage() {
        int i = 0;
        for(TiketInformation tiketInformation :getNameTiker){
            i++;
            message = message + tiketInformation.getTiket()+" ("+tiketInformation.getNameCompany()+")\n";
            if(i==50)break;
        }
        return message;
    }
    @Override
    public List<String> getButtons() {return null;}
}
