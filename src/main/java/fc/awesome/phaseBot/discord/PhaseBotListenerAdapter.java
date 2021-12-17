package fc.awesome.phaseBot.discord;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.messageHandlers.MessageHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

@Component
public class PhaseBotListenerAdapter extends ListenerAdapter {
    @Value("${discord.trigger:!pb}")
    private String botTrigger;

    Logger logger = LoggerFactory.getLogger(PhaseBotListenerAdapter.class);

    public Map<String, MessageHandler> handlerMap = new HashMap<>();

    public String getBotTrigger() {
        return botTrigger;
    }

    /**
     * Rofl, the abstract class does not fucking have java documentation.  Though, I guess to be fair, this is a
     * simple method
     *
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

            MessageHandler handler = handlerMap.get(cmd);

            try {
                if (handler != null) {
                    handler.handleMessage(event, args);
                } else if (handlerMap.get("help") != null && msgTokens.length == 1) {
                    handlerMap.get("help").handleMessage(event, args);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    // Registers a handler to this adapter
    // blah blah, thread safety, blah blah
    public synchronized void registerMessageHandler(String handlerTrigger, MessageHandler handler) {
        handlerMap.put(handlerTrigger, handler);
        logger.info("Added " + handler.getClass().getSimpleName() + " to phaseBotListenerAdapter");
    }
}
