package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RollHandler extends MessageHandler {

    @Override
    public String getArgDesc() {
        return "**<Number (optional)>**";
    }

    /**
     * Defines the description of the handler to be used in the help function
     *
     * @return the description
     */
    @Override
    public String getDesc() {
        return " Rolls the dice of fates";
    }

    /**
     * Defines the trigger to invoke this handler, e.g. "joke"
     *
     * @return
     */
    @Override
    public String getTrigger() {
        return "roll";
    }

    @Override
    public void handleMessageEvent(MessageCreateEvent event, String numberStr) throws JsonProcessingException {
        int highEnd;
        try {
            highEnd = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            highEnd = 100;
        }
        event.getMessage().getChannel().block().createMessage(event.getMessage().getAuthor().get().getMention() + " rolls " +
                ThreadLocalRandom.current().nextInt(1, highEnd + 1) + " (1-" + highEnd + ")").block();
    }
}
