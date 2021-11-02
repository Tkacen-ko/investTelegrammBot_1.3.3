package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class StarterKit extends AnswerWithTextAndButtons {
    private String message = "✋*Приветствую тебя пользователь!*✋\n" +
                              "         Меня зовут *InvestGooseBot* \uD83E\uDD86\n\nЯ бот созданный, для облегчения работы с ценными бумагами!\n"+
                "*Я не даю, каких либо советов или прогнозов*, а только отображаю статистику, необходимую  "+
                "для принятию быстрого и правильного решения.\n\nВыберете необходимую команду для бота:"+
                "\n • «*Базовые команды*» - запрос полезной информацию по акциям, "+
                "представленным на *Московской бирже*.\n • «*Подписки*» - позволяет оформить "+
                "информационную рассылку на случай резкого падения↘ стоимости акций на бирже:";
    private List<String> buttons = Arrays.asList("Базовые команды", "Подписки\uD83D\uDCE3");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }


}
