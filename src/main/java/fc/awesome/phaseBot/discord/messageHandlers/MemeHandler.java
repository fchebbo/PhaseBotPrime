package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemeHandler extends MessageHandler{
    @Override
    public String getDesc() {
        return "Sends a random meme to the channel";
    }

    @Override
    public String getTrigger() {
        return "meme";
    }

    @Override
    public Mono<Void> handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        return event.getMessage().getChannel().block().createMessage(PhaseBotUtils.getMeme()).then();
    }
}
