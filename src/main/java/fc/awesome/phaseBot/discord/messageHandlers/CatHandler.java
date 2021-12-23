package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import org.springframework.stereotype.Service;

@Service
public class CatHandler extends MessageHandler {
    @Override
    public String getDesc() {
        return "Sends a random cat fact to the channel";
    }

    @Override
    public String getTrigger() {
        return "cat";
    }

    @Override
    public void handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        event.getMessage().getChannel().block().createMessage(PhaseBotUtils.getCatFact()).block();
    }
}
