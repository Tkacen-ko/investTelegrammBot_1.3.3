package ru.tckachenko.investVankaBot.bot;

import lombok.SneakyThrows;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@ComponentScan
public class ReactionToMessage extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            execute(Defendant.messageHandler(update.getMessage().getText(),
                    update.getMessage().getChatId().toString()));

        }
        if (update.hasCallbackQuery()){
            execute(Defendant.messageHandler(update.getCallbackQuery().getData(),
                    update.getCallbackQuery().getMessage().getChatId().toString()));
        }
    }

    @Override
    public String getBotUsername() {
        return "@InvestVankaBot";
    }

    @Override
    public String getBotToken() {
        return "1946166751:AAEnDhq5Wcg4wUbARz2CRo6cwYIIKSWBScM";
    }

}
