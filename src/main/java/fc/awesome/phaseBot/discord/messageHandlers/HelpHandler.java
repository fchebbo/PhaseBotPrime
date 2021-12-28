package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

@Service
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
    public Mono<Void> handleMessageEvent(MessageCreateEvent event, String s) throws JsonProcessingException {

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
                    handler.getDesc() + "\n";
        }
        helpStr += "```";
        return PhaseBotUtils.sendDmToAuthor(event, helpStr);
    }
}
