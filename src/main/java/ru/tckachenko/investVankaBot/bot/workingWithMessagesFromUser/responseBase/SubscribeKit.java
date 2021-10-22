package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.responseBase;

import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.AnswerWithTextOnly;

import java.util.List;

public class SubscribeKit extends AnswerWithTextOnly {
    public static final String message = "InvestGooseBot может оповещать вас о резких падениях " +
            "курса акции на московской бирже. Но поскольку акции с различным уровнем капитализации " +
            "(общей суммарной стоимостью) имеют разную волатильность, вам необходимо выбрать " +
            "категорию акций и процент падения для оповещения. Все акции разделены на 3 категории:\n" +
            "Gold – первая десятка компаний по капитализации (Газпром, Сбер, Роснефть).\n" +
            "Silver – компании с 11 по 25 места по уроню капитализации\n" +
            "Bronze - компании с 26 по 50 места по уроню капитализации\n" +
            "Dno – небольшие по капитализации компании\n" +
            "Выберете порог оповещения для каждой из категорий. \n" +
            "Для начала для Gold компаний (отправьте в чат значение " +
            "от 1 до 100 в формате «Gold-6»)):\n";
    public List<String> getButtons() {
        return null;
    }
    public String getMessage() {
        return message;
    }
}
