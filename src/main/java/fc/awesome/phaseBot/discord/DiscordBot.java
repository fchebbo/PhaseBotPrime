package fc.awesome.phaseBot.discord;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import fc.awesome.phaseBot.discord.messageHandlers.MessageHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import javax.security.auth.login.LoginException;

@Configuration
public class DiscordBot {
    @Value("${discord.trigger:!pb}")
    private String discordTrigger;

    @Value("${discord.token}")
    private String discordToken;

    //TODO: maybe also include activity type..maybe...
    @Value("${discord.activity:God, type !pb for a list of options}")
    public String discordActivity;

    static org.slf4j.Logger logger = LoggerFactory.getLogger(DiscordBot.class);

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

    public DiscordBot() {
        System.out.println("I Started");
    }

    // This now gets run after setup, pretty neat I guess
    @Bean
    public GatewayDiscordClient gatewayDiscordClient(MessageHandler [] handlers) throws LoginException {
        logger.info("Starting discord bot!");
        GatewayDiscordClient client = DiscordClientBuilder.create(discordToken)
                .build()
                .login()
                .block();

        logger.info("Discord bot online!");

        System.out.println(handlers);

        for (MessageHandler handler : handlers) {
            phaseBotListenerAdapter.registerMessageHandler(handler);
        }

        // This is legit crazy, holy shit.  Reactive programming
        client.on(MessageCreateEvent.class)
                .filter(messageCreateEvent -> messageCreateEvent.getMessage().getAuthor().map(user->!user.isBot()).orElse(false))
                .filter(messageCreateEvent -> messageCreateEvent.getMessage().getContent().startsWith(discordTrigger))
                .log()
                .flatMap(phaseBotListenerAdapter:: onMessageReceived)
                .subscribe();
        return client;
    }

    //public Mono<Void> processCommand(MessageCreateEvent eventMessage) {
        //return Mono.just(eventMessage)
         //       .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
       //         .filter(message -> message.getContent().equalsIgnoreCase("!todo"))
     //           .flatMap(Message::getChannel)
   //             .flatMap(channel -> channel.createMessage("Things to do today:\n - write a bot\n - eat lunch\n - play a game"))
  //              .then();
//    }
}

