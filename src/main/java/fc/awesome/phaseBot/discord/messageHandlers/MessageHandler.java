package fc.awesome.phaseBot.discord.messageHandlers;

import fc.awesome.phaseBot.discord.PhaseBotListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class MessageHandler {

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

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
    public abstract void handleMessage(MessageReceivedEvent event, String s);

    @PostConstruct
    private void registerHandler() {
        phaseBotListenerAdapter.registerMessageHandler(getTrigger(), this);
    }
}
