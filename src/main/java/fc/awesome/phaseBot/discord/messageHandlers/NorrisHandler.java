package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class NorrisHandler extends MessageHandler {
    @Override
    public String getDesc() {
        return "Sends a random 'Chuck Norris' joke to the channel (YES HE'S BACK!!)";
    }

    @Override
    public String getTrigger() {
        return "norris";
    }

    @Override
    public void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException {
        event.getChannel().sendMessage(PhaseBotUtils.getNorris()).queue();
    }
}
