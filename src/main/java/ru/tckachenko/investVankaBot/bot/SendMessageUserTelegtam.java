package ru.tckachenko.investVankaBot.bot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.ui.context.Theme;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс-обработчик поступающих к боту сообщений.
 */
@ComponentScan
public class SendMessageUserTelegtam extends DefaultAbsSender{

    public SendMessageUserTelegtam(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
       return  "1946166751:AAEnDhq5Wcg4wUbARz2CRo6cwYIIKSWBScM";
    }
}