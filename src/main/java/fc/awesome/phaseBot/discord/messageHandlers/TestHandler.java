package fc.awesome.phaseBot.discord.messageHandlers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
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
    public void handleMessage(MessageReceivedEvent event, String s) {
        event.getChannel().sendMessage("This actually works, springing into action MOFO").queue();
    }
}
