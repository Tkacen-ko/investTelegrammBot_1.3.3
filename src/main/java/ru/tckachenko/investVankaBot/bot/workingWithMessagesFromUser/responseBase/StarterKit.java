package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextAndButtons;

import java.util.Arrays;
import java.util.List;

public class StarterKit extends AnswerWithTextAndButtons {
    private String message = "Приветствую тебя пользователь!\n" +
                "Меня зовут InvestGooseBot.\nЯ бот созданный, для облегчения работы с ценными бумагами! "+
                "Я не даю, каких либо советов или прогнозов, а только отображаю статистику, необходимую  "+
                "для принятию быстрого и правильного решения. Выберете необходимое действие бота. "+
                "Кнопка «Базовые команды» - позволяют запросить полезную информацию по акциям, "+
                "представленным на Московской бирже. Кнопка «Подписки» позволит выбрать "+
                "информационную рассылку на случай резкого роиста или падения на бирже:";
    private List<String> buttons = Arrays.asList("Базовые команды", "Подписки");
    public List<String> getButtons() {
        return buttons;
    }
    public String getMessage() {
        return message;
    }


}
