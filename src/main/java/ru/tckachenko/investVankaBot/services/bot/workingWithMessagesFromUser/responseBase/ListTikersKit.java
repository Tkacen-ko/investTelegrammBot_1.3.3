package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.AnswerWithTextOnly;
import ru.tckachenko.investVankaBot.dao.helperСlassesWorkingData.TikerInformation;

import java.util.HashSet;
import java.util.List;

public class ListTikersKit extends AnswerWithTextOnly {
    private final List<TikerInformation> getNameTicker;

    public ListTikersKit(List<TikerInformation> getNameTicker) {
        this.getNameTicker = getNameTicker;
    }

    private String message = "Ниже представлены *15 случайных тикеров*" +
            " по которым можно получить базовую информацию.\n" +
            "Отправьте ответным сообщением наименование нужного вам тикера" +
            "(_короткое буквенное обозначение ценное бумаги_(Например *GAZP*, *SBER*, *YNDX*)), " +
            "о котором вы хотите получить данные:\n\n";

    @Override
    public String getMessage() {
        for (int num : randomTiker()) {
            message += " • " + getNameTicker.get(num).getTicker() + " (" +
                    getNameTicker.get(num).getNameCompany() + ")\n";
        }
        return message;
    }

    @Override
    public List<String> getButtons() {
        return null;
    }

    public HashSet<Integer> randomTiker() {
        HashSet<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            int serialNumber = ((int) ((Math.random() * (((getNameTicker.size() - 1)) + 1)) + 0));

            if (getNameTicker.get(serialNumber).getTicker().length() == 0 ||
                    getNameTicker.get(serialNumber).getNameCompany().length() == 0) {
                continue;
            } else {
                numbers.add(serialNumber);
            }
            if (numbers.size() == 15) break;
        }
        return numbers;
    }
}
