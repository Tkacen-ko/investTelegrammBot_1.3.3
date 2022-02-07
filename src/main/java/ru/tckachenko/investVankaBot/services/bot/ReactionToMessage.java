package ru.tckachenko.investVankaBot.services.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tckachenko.investVankaBot.dao.GetDataFromProperties;
import ru.tckachenko.investVankaBot.services.bot.workingWithMessagesFromUser.Defendant;
import ru.tckachenko.investVankaBot.dao.CheckingUser;

@Component
public class ReactionToMessage extends TelegramLongPollingBot {

    private final Defendant defendant;
    private final CheckingUser checkingUser;

    @Autowired
    public ReactionToMessage(Defendant defendant, CheckingUser checkingUser) {
        this.defendant = defendant;
        this.checkingUser = checkingUser;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            checkingUser.check(update.getMessage());
            execute(defendant.batyaReshala(update.getMessage().getText().trim().toLowerCase(),
                    update.getMessage().getChatId().toString()));
        }
        if (update.hasCallbackQuery()) {
            execute(defendant.batyaReshala(update.getCallbackQuery().getData().trim().toLowerCase(),
                    update.getCallbackQuery().getMessage().getChatId().toString()));
        }
    }

    @Override
    public String getBotUsername() {
        return new GetDataFromProperties().getData("bot.name");
    }

    @Override
    public String getBotToken() {
        return new GetDataFromProperties().getData("telegram.bot.token");
    }

}
