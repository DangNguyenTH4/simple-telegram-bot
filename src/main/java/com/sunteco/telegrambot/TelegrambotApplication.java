package com.sunteco.telegrambot;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegrambotApplication {
	@Value("stc.telegram.token:")
	private String token;
	@PostConstruct
	@SneakyThrows
	void init(){
		try {

		}catch (Exception exception){
			exception.printStackTrace();
		}
	}
	@SneakyThrows
	public static void main(String[] args) {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		telegramBotsApi.registerBot(new ChannelHandlers(""));
		//Sunteco
		telegramBotsApi.registerBot(new ChannelHandlers(""));
	}

}
