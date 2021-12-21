package fc.awesome.phaseBot.discord;

import fc.awesome.phaseBot.twitter.TwitterClient;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PhaseBotEventListener implements EventListener {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(PhaseBotEventListener.class);

    /**
     * Handles any {@link GenericEvent GenericEvent}.
     *
     * <p>To get specific events with Methods like {@code onMessageReceived(MessageReceivedEvent event)}
     * take a look at: {@link ListenerAdapter ListenerAdapter}
     *
     * @param event The Event to handle.
     */
    public PhaseBotEventListener()
    {
        logger.info("Inizialized phaseBotEventListener!");
    }
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        //System.out.println("DEALING WITH EVENT: " + event);
        if (event instanceof GuildJoinEvent) {
            ((GuildJoinEvent) event).getGuild().getDefaultChannel().sendMessage("YOU EXPECTED A BOT, BUT IT WAS ME DIO! Type \"!pb\" for a list of options").complete();
        }
        if (event instanceof ReadyEvent) {
            System.out.println("Bot is currently live in " + event.getJDA().getGuilds().size() + " servers!");
        }
    }
}
