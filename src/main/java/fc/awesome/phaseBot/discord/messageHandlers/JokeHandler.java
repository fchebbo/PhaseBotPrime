package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class JokeHandler extends MessageHandler{
    /**
     * Defines the description of the handler to be used in the help function
     *
     * @return the description
     */
    @Override
    public String getDesc() {
        return "Sends a random (probably bad) joke to the channel";
    }

    /**
     * Defines the trigger to invoke this handler, e.g. "joke"
     *
     * @return
     */
    @Override
    public String getTrigger() {
        return "joke";
    }

    @Override
    public void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException {
        event.getChannel().sendMessage(PhaseBotUtils.getJoke()).queue();
    }
}
