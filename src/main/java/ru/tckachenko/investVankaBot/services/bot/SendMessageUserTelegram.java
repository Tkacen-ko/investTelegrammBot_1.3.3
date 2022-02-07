package ru.tckachenko.investVankaBot.services.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.tckachenko.investVankaBot.dao.GetDataFromProperties;

/**
 * Класс-обработчик поступающих к боту сообщений.
 */
@Component
public class SendMessageUserTelegram extends DefaultAbsSender {

    public SendMessageUserTelegram(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return new GetDataFromProperties().getData("telegram.bot.token");
    }
}
