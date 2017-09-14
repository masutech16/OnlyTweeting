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


    Twitter4jWrapper() {
        //設定の初期化
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(Settings.consumerKey);
        cb.setOAuthConsumerSecret(Settings.consumerSecret);
        cb.setOAuthAccessToken(null);
        cb.setOAuthAccessTokenSecret(null);

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    void Tweet(String content) {
        try {
            twitter.updateStatus(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public String getRequestTokenUrl() {
        try{
            requestToken = twitter.getOAuthRequestToken();
        } catch(TwitterException te) {
            //エラーが発生したことをお知らせするウィンドウを表示する
            System.out.println(te.getStackTrace());
            System.exit(1);
        }
        return requestToken.getAuthenticationURL();
    }

    public boolean canCreateAccessToken(String pin) {
        try {
            accessToken = twitter.getOAuthAccessToken(requestToken,pin);
        } catch(TwitterException te) {
            return false;
        }
        return true;
    }

    private static void storeAccessToken(int useId, AccessToken accessToken) {

    }


}
