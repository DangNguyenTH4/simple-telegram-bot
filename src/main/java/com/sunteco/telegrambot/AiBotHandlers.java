package com.sunteco.telegrambot;

import org.springframework.ai.client.AiClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class AiBotHandlers extends TelegramLongPollingBot {
    private String username;
    private AiClient aiClient;
    public AiBotHandlers withUsername(String username){
        this.username = username;
        return this;
    }
    public AiBotHandlers withAiClient(AiClient aiClient){
        this.aiClient = aiClient;
        return this;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (!update.getMessage().hasText()) {
                return;
            }
            String command = null;
            String raw = update.getMessage().getText();
            if (!raw.startsWith("/ask")) {
                return;
            }
            command = raw.substring(4);
            String response = aiClient.generate(command);
            SendMessage generated = SendMessage.builder().chatId(update.getMessage().getChatId())
                    .text(response).build();
            execute(generated);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String getBotUsername() {
        return "dangtonytestbot";
    }

    public AiBotHandlers(String botToken) {
        super(botToken);
    }

}
