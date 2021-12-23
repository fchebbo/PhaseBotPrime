package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.PhaseBotListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public abstract class MessageHandler {

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

    public MessageHandler(){
        Logger.getLogger(this.getClass().getSimpleName()).info("Starting: " + this.getClass().getSimpleName());
    }
    /**
     * Allows you to add args to the help desc, useful for cases where the command has arguments
     **/
    public String getArgDesc() {
        return "";
    }

    /**
     * The description of this handler to be returned by the help message.
     **/
    public abstract String getDesc();

    /**
     * The string used to trigger this handler.
     **/
    public abstract String getTrigger();

    /**
     * Abstract method to handle this message event!
     * @param event The event
     * @param s the string to do stuff with
     * @throws JsonProcessingException
     */
    public abstract void handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException;
}
