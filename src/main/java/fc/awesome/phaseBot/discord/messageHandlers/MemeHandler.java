package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
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
    public void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException {
        event.getChannel().sendMessage(PhaseBotUtils.getMeme()).queue();
    }

    @Override
    public void handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {
        String meme = PhaseBotUtils.getMeme();
        event.getMessage().getChannel().block().createMessage(meme).block();
    }
}
