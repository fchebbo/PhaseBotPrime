package fc.awesome.phaseBot.discord.messageHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import fc.awesome.phaseBot.twitter.TwitterClient;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;

@Component
@ConditionalOnBean(TwitterClient.class)
public class TwitterHandler extends MessageHandler {
    Logger logger = LoggerFactory.getLogger(TwitterHandler.class);

    @Autowired
    TwitterClient twitterClient;

    @Override
    public String getArgDesc() {
        return "**Tweet**";
    }

    @Override
    public String getDesc() {
        return "Post something to twitter using the twitter bot";
    }

    @Override
    public String getTrigger() {
        return "tweet";
    }

    @Override
    public void handleMessage(MessageReceivedEvent event, String s) throws JsonProcessingException {
        if (s.isEmpty()) {
            PhaseBotUtils.sendDmToAuthor(event, "Invalid use of tweet command, must be in the form of tweet.  \n" +
                    "Example: '!pb tweet Phase is the best");
            return;
        }

        try {
            Status status = twitterClient.tweet(s);
            event.getChannel().sendMessage("Tweeted! : " + TwitterClient.getUrlOfStatus(status)).queue();
        } catch (TwitterException e) {
            logger.error(e.getMessage());
            PhaseBotUtils.sendDmToAuthor(event, "Something broke, please message phase the following: " + e.getMessage());
        }
    }
}
