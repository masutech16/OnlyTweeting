package masutech16;


import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Twitter4jWrapper {

    Twitter twitter;

    Twitter4jWrapper() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(Settings.consumerKey);
        cb.setOAuthConsumerSecret(Settings.consumerSecret);
        cb.setOAuthAccessToken(null);
        cb.setOAuthAccessTokenSecret(null);

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        RequestToken requestToken = null;
        try {
            requestToken = twitter.getOAuthRequestToken();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        AccessToken accessToken = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
            System.out.println(requestToken.getAuthenticationURL());
            String pin = null;
            try {
                pin = br.readLine();
            } catch (Exception e) {
                System.exit(1);
            }
            try {
                if (pin.length() > 0) {
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                } else {
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    System.out.println("Unable to get the access token");
                } else {
                    te.printStackTrace();
                }
            }
        }

        //accessTokenの永続化


    }

    private static void storeAccessToken(int useId, AccessToken accessToken) {

    }

    public void Tweet(String content) {
        try {
            twitter.updateStatus(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


}
