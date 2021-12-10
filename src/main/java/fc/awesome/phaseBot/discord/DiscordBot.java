package fc.awesome.phaseBot.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
public class DiscordBot {
    @Value("${discord.token}")
    private String discordToken;

    //TODO: maybe also include activity type..maybe...
    @Value("${discord.activity:God, type !pb for a list of options}")
    public String discordActivity;

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

    @Autowired
    PhaseBotEventListener phaseBotEventListener;

    public DiscordBot() {
        System.out.println("I Started");
    }

    // This now gets run after setup, pretty neat I guess
    @PostConstruct
    private void testSetting() throws LoginException {
        JDA jda = JDABuilder.createDefault(discordToken).
                addEventListeners(phaseBotEventListener, phaseBotListenerAdapter).
                build();

        jda.getPresence().setActivity(Activity.playing(discordActivity));
        System.out.println("Discord Bot started!");
    }
}
