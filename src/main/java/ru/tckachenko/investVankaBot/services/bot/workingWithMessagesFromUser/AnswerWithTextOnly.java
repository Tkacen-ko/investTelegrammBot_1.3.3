package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public abstract class AnswerWithTextOnly implements Answer {
    public SendMessage correctAnswer(String chatID, String introductoryMessage, List<String> button) {
        return SendMessage.builder()
                .text(getMessage())
                .chatId(chatID)
                .parseMode("Markdown")
                .build();
    }

    public abstract List<String> getButtons();

    public abstract String getMessage();
}
