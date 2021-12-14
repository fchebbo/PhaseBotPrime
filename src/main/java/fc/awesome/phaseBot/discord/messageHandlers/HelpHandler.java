package fc.awesome.phaseBot.discord.messageHandlers;

import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

@Component
public class HelpHandler extends MessageHandler {
    @Override
    public String getDesc() {
        return "Literally returns this menu";
    }

    @Override
    public String getTrigger() {
        return "help";
    }

    @Override
    public void handleMessage(MessageReceivedEvent event, String s) {

            String botTrigger = phaseBotListenerAdapter.getBotTrigger();
            String helpStr = "```\n";
            ArrayList<MessageHandler> newList = new ArrayList<>(phaseBotListenerAdapter.handlerMap.values());
            //bro we sorting like a champ
            Collections.sort(newList, Comparator.comparing(MessageHandler::getTrigger));
            for (Iterator<MessageHandler> it = newList.iterator(); it.hasNext(); ) {

                MessageHandler handler = it.next();
                helpStr += botTrigger +
                        " " +
                        handler.getTrigger() +
                        handler.getArgDesc() +
                        ": " +
                        handler.getDesc() +"\n";
            }
            helpStr += "```";
            PhaseBotUtils.sendDmToAuthor(event,helpStr);
    }
}
