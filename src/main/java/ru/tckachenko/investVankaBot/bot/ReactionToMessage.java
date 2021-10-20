package ru.tckachenko.investVankaBot.bot;

import lombok.SneakyThrows;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ComponentScan
public class ReactionToMessage extends TelegramLongPollingBot {
    private String startingMessage = "Приветствую тебя пользователь!\nМеня зовут InvestVanka. Я бот созданный каким-то чижиком, для облегчения работы с ценными бумагами! " +
            "Я не даю каких либо советов или прогнозов, а только отображаю статистику, необходимую  для принятию быстрого и правильного решения. " +
            "Выберете необходимое действие бота: ";
    private String unknownСommand = "Неизвестная команда!\n" +
            "Воспользуйтесь одной перечисленных ниже команд, " +
            "для получения необходимых вам данных:\n" +
            "/now_statistics\n" +
            "/week_statistics\n" +
            "/month_statistics\n" +
            "/year_statistics\n" +
            "/information_ticket";
    private String commands =
            "/now_statistics\n" +
                    "/week_statistics\n" +
                    "/month_statistics\n" +
                    "/year_statistics\n" +
                    "/information_ticket";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMassage(update.getMessage());
        }
        if (update.hasCallbackQuery()){
            handleCallback(update);
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

    @SneakyThrows
    private void handleMassage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "/start".equals(e.getText())).findFirst();

            if (commandEntity.isPresent()) {
                String comand = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (comand) {
                    case "/start":
                        MessageAndButtons(message, TextForTheBot.START);
                        return;
                }
            }
        }

    }
    @SneakyThrows
    private void handleCallback(Update update){
        String masege = update.getCallbackQuery().getData();
        switch (masege) {
            case "Базовые команды":
                execute(SendMessage.builder().text("Реакция на БАЗОВЫЕ КОМАНДЫ").chatId(update.getCallbackQuery().getFrom().getId().toString()).build());
                break;
            case "Подписки":
                execute(SendMessage.builder().text("Реакция на Подписки").chatId(update.getCallbackQuery().getFrom().getId().toString()).build());
                break;
        }

    }
    private void MessageAndButtons(Message message, TextForTheBot mas) throws TelegramApiException {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(allButtonAdds(mas));
        execute(SendMessage.builder()
                .text(mas.getTextToStart())
                .chatId(message.getChatId().toString())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build());
    }
    private List<InlineKeyboardButton> allButtonAdds(TextForTheBot mas){
        List <InlineKeyboardButton> but = new ArrayList<>();
        for(String buty : mas.buttons()){
            but.add(InlineKeyboardButton.builder().text(buty).callbackData(buty).build());
        }
        return but;
    }
}
