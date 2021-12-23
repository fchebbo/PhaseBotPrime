package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import org.springframework.stereotype.Service;

@Service
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
    public void handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        String norris = PhaseBotUtils.getNorris();
        event.getMessage().getChannel().block().createMessage(norris).block();
    }
}
