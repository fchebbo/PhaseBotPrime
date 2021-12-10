package fc.awesome.phaseBot.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

@Component
public class TestHandler extends MessageHandler{
    public TestHandler()
    {
        super();
        desc = "Do a live sanity test of the bot";
        trigger = "test";
    }
    @Override
    public void handleMessage(MessageReceivedEvent event, String s) {
        event.getChannel().sendMessage("This actually works, springing into action MOFO").queue();
        System.out.println("Message Should have been sent???");
    }
}
