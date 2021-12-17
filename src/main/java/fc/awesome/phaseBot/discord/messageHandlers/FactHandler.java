package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
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
    public void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException {
        event.getChannel().sendMessage(PhaseBotUtils.getFact()).queue();
    }
}
