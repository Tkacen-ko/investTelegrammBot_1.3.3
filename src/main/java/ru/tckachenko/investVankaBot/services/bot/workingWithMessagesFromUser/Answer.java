package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface Answer {
    SendMessage correctAnswer(String chatID, String introductoryMessage, List<String> button);

    List<String> getButtons();

    String getMessage();
}
