package ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public abstract class AnswerWithTextOnly implements Answer{
    public  SendMessage correctAnswer(String chatID, String introductoryMessage, List<String> button){
        return SendMessage.builder()
                .text(getMessage())
                .chatId(chatID)
                .build();
    }
    public abstract List<String> getButtons();
    public abstract String getMessage();
}
