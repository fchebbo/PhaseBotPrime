package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
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
    public void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException {
        event.getChannel().sendMessage(PhaseBotUtils.getCatFact()).queue();
    }
}
