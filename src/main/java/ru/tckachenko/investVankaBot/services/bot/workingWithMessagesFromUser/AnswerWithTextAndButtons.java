package ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public abstract class AnswerWithTextAndButtons implements Answer {
    public SendMessage correctAnswer(String chatID, String introductoryMessage, List<String> button) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (String but : button) {
            buttons.add(addButton(but));
        }
        return SendMessage.builder()
                .text(introductoryMessage)
                .chatId(chatID)
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .parseMode("Markdown")
                .build();
    }

    public abstract List<String> getButtons();

    public abstract String getMessage();

    private static List<InlineKeyboardButton> addButton(String mas) {
        List<InlineKeyboardButton> but = new ArrayList<>();
        but.add(InlineKeyboardButton.builder().text(mas).callbackData(mas).build());
        return but;
    }
}
