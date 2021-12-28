package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DogHandler extends MessageHandler {

    /**
     * Defines the description of the handler to be used in the help function
     *
     * @return the description
     */
    @Override
    public String getDesc() {
        return ": Sends a random dog fact to the channel";
    }

    /**
     * Defines the trigger to invoke this handler, e.g. "joke"
     *
     * @return
     */
    @Override
    public String getTrigger() {
        return "dog";
    }

    @Override
    public Mono<Void> handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        return event.getMessage().getChannel().block().createMessage(PhaseBotUtils.getDogFact()).then();
    }
}
