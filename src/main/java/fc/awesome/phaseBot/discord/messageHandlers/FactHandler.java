package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FactHandler extends MessageHandler {
    @Override
    public String getDesc() {
        return "Sends a fun random fact to the channel";
    }

    @Override
    public String getTrigger() {
        return "fact";
    }

    @Override
    public Mono<Void> handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        return event.getMessage().getChannel().block().createMessage(PhaseBotUtils.getFact()).then();
    }
}
