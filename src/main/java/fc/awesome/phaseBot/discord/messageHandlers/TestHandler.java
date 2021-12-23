package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;

@Service
public class TestHandler extends MessageHandler{
    public TestHandler()
    {
        super();
    }
    @Override
    public String getDesc() {
        return "Do a live sanity test of the bot";
    }

    @Override
    public String getTrigger() {
        return "test";
    }

    @Override
    public void handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        event.getMessage().getChannel().block().createMessage("OH GOD HOW DO I REACT").block();
        throw new JsonProcessingException("TEST"){};
    }
}
