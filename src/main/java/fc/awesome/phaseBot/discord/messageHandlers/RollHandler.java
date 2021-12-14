package fc.awesome.phaseBot.discord.messageHandlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RollHandler extends MessageHandler {

    @Override
    public String getArgDesc()
    {
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
    public void handleMessage(MessageReceivedEvent event, String numberStr) {
        int highEnd;
        try {
            highEnd = Integer.parseInt(numberStr);
        }
        catch (NumberFormatException e) {
            highEnd = 100;
        }

        event.getChannel().sendMessage(event.getAuthor().getAsMention() + " rolls " +
                ThreadLocalRandom.current().nextInt(1, highEnd + 1)+ " (1-" + highEnd +")").queue();
    }
}
