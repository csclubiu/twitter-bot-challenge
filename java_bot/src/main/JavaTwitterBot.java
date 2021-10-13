package main;

// Java boilerplate code
// See https://github.com/csclubiu/twitter-bot-challenge/blob/master/java_bot/README.md
// -----------------------

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import twitter4j.*;
import twitter4j.conf.*;

public class JavaTwitterBot {

    private static final String CONSUMER_KEY        = System.getenv("TWITTERBOT_CONSUMER_KEY");
    private static final String CONSUMER_SECRET     = System.getenv("TWITTERBOT_CONSUMER_SECRET");
    private static final String ACCESS_TOKEN        = System.getenv("TWITTERBOT_ACCESS_TOKEN");
    private static final String ACCESS_TOKEN_SECRET = System.getenv("TWITTERBOT_ACCESS_SECRET");

    public static void main(String[] args) throws TwitterException {

        // disable internal logging
        System.setProperty("twitter4j.loggerFactory", "twitter4j.NullLoggerFactory");

        // build auth config
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(CONSUMER_KEY)
            .setOAuthConsumerSecret(CONSUMER_SECRET)
            .setOAuthAccessToken(ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        Configuration conf = cb.build();
        
        // log into the API
        TwitterFactory tf = new TwitterFactory(conf);
        Twitter twitter = tf.getInstance();
        User bot = twitter.verifyCredentials();
        logMessage("Logged in as @" + bot.getScreenName() + "\n----------------");

        // array of terms that will trigger the onStatus function
        ArrayList<String> triggerWords = new ArrayList<String>();
        triggerWords.add("@" + bot.getScreenName());

        // create tweet listener
        TwitterStream twitterStream = new TwitterStreamFactory(conf).getInstance().addListener(new StatusListener() {

            @Override
            public void onStatus(Status status) {

                String message = status.getText();
                String authorHandle = status.getUser().getScreenName();

                // log incoming tweet
                logMessage("Received: \"" + message + "\" from @" + authorHandle);

                // check if "hi" or "hello" is in the tweet (case insensitive)
                if (message.toLowerCase().contains("hi") || message.toLowerCase().contains("hello")) {

                    // respond with "Hello!"
                    String reply = "Hello!";

                    try {

                        // this needs to include the person's username at the beginning
                        twitter.updateStatus(new StatusUpdate("@" + authorHandle + " " + reply)
                                                    .inReplyToStatusId(status.getId()));

                        logMessage("Responded to @" + authorHandle);
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                    

                }
                
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onScrubGeo(long userId, long upToStatusId) {}
            public void onStallWarning(StallWarning warning) {}

        });
        twitterStream.filter(new FilterQuery(0, new long[0], triggerWords.toArray(new String[triggerWords.size()])));
                
    }

    private static void logMessage(String text) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d K:mm:ss a");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println("[" + dtf.format(now) + "] " + text); 
    }
    
}