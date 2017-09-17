package OnlyTweeting;


import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4jWrapper {

    private Twitter twitter;
    private RequestToken requestToken = null;
    private AccessToken accessToken = null;


    Twitter4jWrapper(String consumerKey, String consumerSecret, String accessToken, String accessSecret) {
        //設定の初期化
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    Twitter4jWrapper(String consumerKey, String consumerSecret) {
        this(consumerKey, consumerSecret, null, null);
    }

    void Tweet(String content) {
        try {
            twitter.updateStatus(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    String getRequestTokenUrl() {
        try{
            requestToken = twitter.getOAuthRequestToken();
        } catch(TwitterException te) {
            System.out.println(te.getStackTrace());
            System.exit(1);
        }
        return requestToken.getAuthenticationURL();
    }

    boolean canCreateAccessToken(String pin) {
        try {
            accessToken = twitter.getOAuthAccessToken(requestToken,pin);
            FileManager.writeAccessToken(accessToken);
        } catch(TwitterException te) {
            return false;
        }
        return true;
    }

}
