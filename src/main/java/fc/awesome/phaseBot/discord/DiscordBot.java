package fc.awesome.phaseBot.discord;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.twitter.AutoTweeter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

@Component
public class DiscordBot {
    @Value("${discord.token}")
    private String discordToken;

    //TODO: maybe also include activity type..maybe...
    @Value("${discord.activity:God, type !pb for a list of options}")
    public String discordActivity;

    static org.slf4j.Logger logger = LoggerFactory.getLogger(DiscordBot.class);

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

    @Autowired
    PhaseBotEventListener phaseBotEventListener;

    public DiscordBot() {
        System.out.println("I Started");
    }

    // This now gets run after setup, pretty neat I guess
    @PostConstruct
    private void startup() throws LoginException {
        logger.info("Starting discord bot!");
        System.out.println(discordToken);
        //JDA jda = JDABuilder.createDefault(discordToken).
       //         addEventListeners(phaseBotEventListener, phaseBotListenerAdapter).
       //         setWebsocketFactory(null).
       //         build();
       // jda.getPresence().setActivity(Activity.playing(discordActivity));
        GatewayDiscordClient client = DiscordClientBuilder.create(discordToken)
                .build()
                .login()
                .block();
        System.out.println("Discord Bot started!");
        client.on(MessageCreateEvent.class).subscribe(event -> phaseBotListenerAdapter.onMessageReceived(event));
    }
}
