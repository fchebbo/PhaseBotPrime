package fc.awesome.phaseBot.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class MessageHandler {

    @Autowired
    PhaseBotListenerAdapter phaseBotListenerAdapter;

    protected String desc;

    protected String trigger;

    public MessageHandler()
    {
        setDescAndTrigger();
    }

    public abstract void setDescAndTrigger();

    public String getTrigger()
    {
        return trigger;
    }

    public String getDesc(){
        return desc;
    }
    /**
     * Defines what happens when this handler is invoked
     * @param event The event that invoked this handler
     * @param s Anything that the user sent that wasn't the command itself (not always used)
     */
    public abstract void handleMessage(MessageReceivedEvent event, String s);

    @PostConstruct
    private void registerHandler(){
        phaseBotListenerAdapter.registerMessageHandler(trigger,this);
    }
}
