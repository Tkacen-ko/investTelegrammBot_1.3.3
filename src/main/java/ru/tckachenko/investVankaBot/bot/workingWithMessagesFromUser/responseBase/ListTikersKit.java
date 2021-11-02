package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dataProcessor.TiketInformation;

import java.util.HashSet;
import java.util.List;

public class ListTikersKit extends AnswerWithTextOnly {
    private List<TiketInformation> getNameTiker;
    public ListTikersKit(List<TiketInformation> getNameTiker){
        this.getNameTiker = getNameTiker;
    }

    public String message = "Ниже представлены *15 случайных тикеров*" +
            " по которым можно получить базовую информацию.\n"+
            "Отправьте ответным сообщением наименование нужного вам тикера"+
            "(_короткое буквенное обозначение ценное бумаги_(Например *GAZP*, *SBER*, *YNDX*)), "+
            "о котором вы хотите получить данные:\n\n";

    @Override
    public String getMessage() {
        for (int num : randomToker()){
            message += " • "+getNameTiker.get(num).getTiket() + " (" +
                    getNameTiker.get(num).getNameCompany()+ ")\n";
        }
        return message;
    }

    @Override
    public List<String> getButtons() {return null;}

    public HashSet<Integer> randomToker(){
        HashSet<Integer> numbers = new HashSet<Integer>();
        for (int i = 0; i < 100; i++) {
            int serialNumber = ((int)((Math.random() * (((getNameTiker.size()-1) - 0) + 1)) + 0));

            if(getNameTiker.get(serialNumber).getTiket().length() == 0 ||
                    getNameTiker.get(serialNumber).getNameCompany().length() == 0){
                continue;
            }
            else {
                numbers.add(serialNumber);
            }
            if(numbers.size() == 15) break;
        }
        return numbers;
    }
}
