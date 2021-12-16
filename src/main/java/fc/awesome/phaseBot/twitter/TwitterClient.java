package fc.awesome.phaseBot.twitter;

import com.neovisionaries.ws.client.WebSocketFactory;
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
        (value="twitter.enabled",
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

    public TwitterClient()
    {
        System.out.println("SUPBRO");
    }

    // This now gets run after setup, pretty neat I guess
    @PostConstruct
    private void startup() throws LoginException, TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(false);
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();
        Status status = twitter.updateStatus("Hello World");
        System.out.println(status.getText());
    }
}
