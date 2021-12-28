package fc.awesome.phaseBot.discord;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.messageHandlers.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class PhaseBotListenerAdapter {
    @Value("${discord.trigger:!pb}")
    private String botTrigger;

    Logger logger = LoggerFactory.getLogger(PhaseBotListenerAdapter.class);

    public Map<String, MessageHandler> handlerMap = new HashMap<>();

    public String getBotTrigger() {
        return botTrigger;
    }

    public PhaseBotListenerAdapter()
    {
        logger.info("Initialized PhaseBotListenerAdapter!");
    }

    /**
     * Rofl, the abstract class does not fucking have java documentation.  Though, I guess to be fair, this is a
     * simple method
     *
     * @param event The message receive event to which we can do something with
     */
    public Mono<Void> onMessageReceived(MessageCreateEvent event) {

        String message = event.getMessage().getContent();
        String[] msgTokens = message.split(" ", 3);

        // using toLoserCase, to save headaches
        if (msgTokens.length > 0) {

            // using toLowercase because i don't want confusion of "catfact" vs "catFact"
            String cmd = (msgTokens.length > 1) ? msgTokens[1].toLowerCase() : "help";
            String args = (msgTokens.length > 2) ? msgTokens[2] : "";

            MessageHandler handler = handlerMap.get(cmd);

            try {
                if (handler != null) {
                    return handler.handleMessageEvent(event, args);
                } else if (handlerMap.get("help") != null && msgTokens.length == 1) {
                    return handlerMap.get("help").handleMessageEvent(event, args);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return Mono.empty();
    }

    // Registers a handler to this adapter
    // Using synchronized due to thread safety I guess
    public synchronized void registerMessageHandler(MessageHandler handler) {
        handlerMap.put(handler.getTrigger(), handler);
        logger.info("Added " + handler.getClass().getSimpleName() + " to phaseBotListenerAdapter");
    }
}
