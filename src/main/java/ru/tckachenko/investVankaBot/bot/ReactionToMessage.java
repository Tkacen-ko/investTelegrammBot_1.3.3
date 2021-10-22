package ru.tckachenko.investVankaBot.bot;

import lombok.SneakyThrows;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.tckachenko.investVankaBot.bot.workingWithMessagesFromUser.Defendant;

@ComponentScan
public class ReactionToMessage extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            execute(new Defendant().batyaReshala(update.getMessage().getText(),
                    update.getMessage().getChatId().toString()));
        }
        if (update.hasCallbackQuery()){
            execute(new Defendant().batyaReshala(update.getCallbackQuery().getData(),
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
