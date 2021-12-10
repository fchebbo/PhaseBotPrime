package fc.awesome.phaseBot.discord.messageHandlers;

import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FactHandler extends MessageHandler{
    @Override
    public String getDesc() {
        return "Sends a fun random fact to the channel";
    }

    @Override
    public String getTrigger() {
        return "fact";
    }

    @Override
    public void handleMessage(MessageReceivedEvent event, String s) {
        Map<?,?> responseMap = PhaseBotUtils.getJsonRestResponse(event, "https://uselessfacts.jsph.pl/random.json?language=en");
        event.getChannel().sendMessage((String)responseMap.get("text")).queue();
    }
}
