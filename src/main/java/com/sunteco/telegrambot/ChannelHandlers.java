package com.sunteco.telegrambot;

import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButtonRequestChat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.List;

public class ChannelHandlers extends TelegramLongPollingBot {
    private String username;
    public ChannelHandlers withUsername(String username){
        this.username = username;
        return this;
    }
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (!update.getMessage().hasText()) {
                return;
            }
            String command = null;
            if (update.getMessage().getText().startsWith("/start ")) {
                String[] commands = update.getMessage().getText().split(" ");
                if (commands.length < 2) {
                    return;
                }
                command = String.format("/%s", commands[1]);
            }else{
                command = update.getMessage().getText();
            }
            if (!"/chat_id".equals(command)) {
                return;
            }
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Your current group chat id is: " + update.getMessage().getChatId().toString());
            sendMessage.setReplyMarkup(ReplyKeyboardMarkup.builder()
                    .inputFieldPlaceholder("Show chat id")
                    .oneTimeKeyboard(true)
                    .keyboard(
                            List.of(new KeyboardRow(
                                    List.of(KeyboardButton.builder().text("/chat_id").build()
                                    )
                            ))).build());
            execute(sendMessage);
            SendMessage showUsernameMessage = SendMessage.builder().chatId(update.getMessage().getChatId())
                    .text("If you have other group. Copy exactly my name: " +this.username + " and add me to your group.").build();
            execute(showUsernameMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String getBotUsername() {
        return "dangtonytestbot";
    }

    public ChannelHandlers(String botToken) {
        super(botToken);
    }

}
