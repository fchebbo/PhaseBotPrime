package fc.awesome.phaseBot;

import discord4j.core.GatewayDiscordClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PhaseBotApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PhaseBotApplication.class, args);
	}
}
