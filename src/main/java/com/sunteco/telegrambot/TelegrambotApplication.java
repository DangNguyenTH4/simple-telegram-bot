package com.sunteco.telegrambot;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.ai.client.AiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegrambotApplication implements CommandLineRunner {
	@Value("sunteco.telegram.token:")
	private String token;
	@Autowired
	private AiClient aiClient;

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
		SpringApplication.run(TelegrambotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
		telegramBotsApi.registerBot(new AiBotHandlers(System.getenv("TELEGRAM_BOT_TOKEN")).withAiClient(aiClient));
	}
}
