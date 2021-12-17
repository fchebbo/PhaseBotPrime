package fc.awesome.phaseBot.twitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import fc.awesome.phaseBot.discord.utils.PhaseBotUtils;
import fc.awesome.phaseBot.discord.utils.ThrowableSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnBean(TwitterClient.class)
@ConditionalOnProperty
        (value="twitter.autoTweet.enabled",
                havingValue = "true",
                matchIfMissing = false)
public class AutoTweeter {
    // Functional programming: an array that contains methods!
    ThrowableSupplier<String, JsonProcessingException>[] autoTweets = new ThrowableSupplier[] {
            PhaseBotUtils::getCatFact,
            PhaseBotUtils::getDogFact,
            PhaseBotUtils::getFact,
            PhaseBotUtils::getJoke,
            PhaseBotUtils::getQuote
    };

    static Logger logger = LoggerFactory.getLogger(AutoTweeter.class);

    @Autowired
    TwitterClient twitterClient;

    // We have to use # before $ here so that we can parse things like 1000 * 60
    @Scheduled(fixedDelayString = "#{${twitter.autoTweet.postRate}}", initialDelay = 1000)
    public void autoTweet() throws JsonProcessingException, TwitterException {
        Status status = twitterClient.tweet(autoTweets[ThreadLocalRandom.current().nextInt(autoTweets.length)].get());
        logger.info("Posted Tweet!: " + TwitterClient.getUrlOfStatus(status));
    }
}

