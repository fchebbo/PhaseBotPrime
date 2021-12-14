package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.PhaseBotListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public abstract class MessageHandler {

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

    /**
     * Allows you to add args to the help desc, useful for cases where the command has arguments
     **/
    public String getArgDesc()
    {
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
     * Defines what happens when this handler is invoked
     *
     * @param event The event that invoked this handler
     * @param s     Anything that the user sent that wasn't the command itself (not always used)
     */
    public abstract void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException;

    @PostConstruct
    private void registerHandler() {
        phaseBotListenerAdapter.registerMessageHandler(getTrigger(), this);
    }
}
