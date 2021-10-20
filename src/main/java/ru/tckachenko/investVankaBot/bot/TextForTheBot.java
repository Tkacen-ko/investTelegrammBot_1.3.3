package ru.tckachenko.investVankaBot.bot;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum TextForTheBot {
    START;
    private String textToStart = "Текст для стартового окна";
    public List<String> buttons(){
        List<String> buttons = new ArrayList<>();
        buttons.add("Базовые команды");
        buttons.add("Подписки");
        return buttons;
    }
}
