package fc.awesome.phaseBot.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class PhaseBotListenerAdapter extends ListenerAdapter {

    @Value("${discord.trigger:!pb}")
    private String botTrigger;

    public Map<String, MessageHandler> handlerMap = new HashMap<>();

    //blah blah, thread safety, blah blah
    public synchronized void registerMessageHandler(String handlerTrigger, MessageHandler handler)
    {
        handlerMap.put(handlerTrigger,handler);
    }

    /**
     * Rofl, the abstract class does not fucking have java documentation.  Though, I guess to be fair, this is a
     * simple method
     * @param event The message receive event to which we can do something with
     */
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw().trim();

        String[] msgTokens = {};

        // Only tokenize if these conditions are met..otherwise just gtfo
        if (!event.getAuthor().isBot() && (message.toLowerCase().startsWith(botTrigger.toLowerCase()))) {
            msgTokens = message.split(" ", 3);
        } else {
            return;
        }

        // using toLoserCase, to save headaches
        if (msgTokens.length > 0 && msgTokens[0].equals(botTrigger)) {

            // using toLowercase because i don't want confusion of "catfact" vs "catFact"
            String cmd = (msgTokens.length > 1) ? msgTokens[1].toLowerCase() : "help";
            String args = (msgTokens.length > 2) ? msgTokens[2] : "";

            if (msgTokens.length == 3) {
                System.out.println(msgTokens[2]);
            }

            MessageHandler handler = handlerMap.get(cmd);
            if (handler != null) {
                handler.handleMessage(event, args);
            }
        }
    }
}
