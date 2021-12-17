package fc.awesome.phaseBot.discord.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class PhaseBotUtils {
    private static RestTemplate restTemplate = new RestTemplate();

    private static HttpHeaders headers = new HttpHeaders();
    private static HttpEntity<String> entity;

    static {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // though this header is only used for jokebot fuck it - lets keep it here
        headers.add("User-Agent", "phaseBot (firahs.chebbo@utoronto.ca)");
        entity = new HttpEntity<>(headers);
    }

    // prevents instantiation, TF you wanna instantiate a util class for?
    private PhaseBotUtils() {
    }


    /**
     * Returns a map containing the JSON response of a request
     *
     * @param url
     * @return
     */
    public static Map<?, ?> getJsonRestResponse(String url) throws JsonProcessingException {
        ResponseEntity<String> k = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue((String) k.getBody(), Map.class);
    }

    /**
     * Tests to see if the meme is successfully returned
     *
     * @return True if the url returns 200...fucking random meme generator needs to get on its game
     */
    public static boolean isUrlOk(String url) {
        //TODO: maybe make a static restTemplate so we dont need to keep making new objects..idfk
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> k = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return k.getStatusCode() == HttpStatus.OK;
    }

    public static void sendDmToAuthor(MessageReceivedEvent event, String msg) {
        // this weird thing is what enables us to whisper a user...//TODO (PERHAPS) make this a helper method
        event.getAuthor().openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(msg).queue();
        });
    }

    public static String getDogFact() throws JsonProcessingException {
        Map<?, ?> responseMap = PhaseBotUtils.getJsonRestResponse("https://dog-api.kinduff.com/api/facts");
        return ((ArrayList<String>) responseMap.get("facts")).get(0);
    }

    public static String getCatFact() throws JsonProcessingException {
        Map<?, ?> responseMap = PhaseBotUtils.getJsonRestResponse("https://catfact.ninja/fact");
        return (String) responseMap.get("fact");
    }

    public static String getFact() throws JsonProcessingException {
        Map<?, ?> responseMap = PhaseBotUtils.getJsonRestResponse("https://uselessfacts.jsph.pl/random.json?language=en");
        return (String) responseMap.get("text");
    }

    public static String getJoke() throws JsonProcessingException {
        int choice = ThreadLocalRandom.current().nextInt(1, 3);
        Map<?, ?> responseMap;
        responseMap = PhaseBotUtils.getJsonRestResponse("https://icanhazdadjoke.com/");
        return (String) responseMap.get("joke");
    }

    public static String getMeme() throws JsonProcessingException {
        // I might have to Axe this if too many images are 404...
        int tries = 0;
        boolean found = false;
        String url;
        Map<?, ?> responseMap = null;
        while (tries < 5 && !found) {
            responseMap = PhaseBotUtils.getJsonRestResponse("https://meme-api.herokuapp.com/gimme");
            url = (String) responseMap.get("url");
            found = PhaseBotUtils.isUrlOk(url);
        }
        if (found) {
            return ((String) responseMap.get("url"));
        } else {
            return "Your call could not be completed as dialed, please try again";
        }
    }

    public static String getNorris() throws JsonProcessingException {
        Map<?, ?> responseMap = PhaseBotUtils.getJsonRestResponse("http://api.icndb.com/jokes/random");

        return (String) ((Map) responseMap.get("value")).get("joke");
    }

    public static String getQuote() throws JsonProcessingException {
        Map<?, ?> responseMap = PhaseBotUtils.getJsonRestResponse("https://api.quotable.io/random");
        return "`" + (String) responseMap.get("content") + "`\n" +
                "*---" + (String) responseMap.get("author") + "*";
    }
}
