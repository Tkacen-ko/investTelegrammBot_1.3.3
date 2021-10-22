package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface Answer {
    public SendMessage correctAnswer(String chatID, String introductoryMessage, List<String> button);
    public List<String> getButtons();
    public String getMessage();
}
