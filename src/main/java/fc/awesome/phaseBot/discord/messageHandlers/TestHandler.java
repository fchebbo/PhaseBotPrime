package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public Mono<Void> handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        return event.getMessage().getChannel().block().createMessage("OH GOD HOW DO I REACT").then();
    }
}
