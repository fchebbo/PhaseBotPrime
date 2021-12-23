package fc.awesome.phaseBot.twitter;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;

@Component
@ConditionalOnProperty
        (value = "twitter.enabled",
                havingValue = "true",
                matchIfMissing = false)
public class TwitterClient {

    @Value("${twitter.consumerKey}")
    String consumerKey;

    @Value("${twitter.consumerSecret}")
    String consumerSecret;

    @Value("${twitter.accessToken}")
    String accessToken;

    @Value("${twitter.accessTokenSecret}")
    String accessTokenSecret;

    private Twitter twitter;

    static org.slf4j.Logger logger = LoggerFactory.getLogger(TwitterClient.class);

    // This now gets run after setup, pretty neat I guess
    @PostConstruct
    private void startup() throws LoginException, TwitterException {

        logger.info("Starting Twitter Client");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(false);
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());

        logger.info("Twitter Client Started!");

        twitter = tf.getInstance();
    }

    public Status tweet(String text) throws TwitterException {
        return twitter.updateStatus(text);
    }

    public static  String getUrlOfStatus(Status status) {
        return "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId();
    }
}
