package OnlyTweeting;import javax.swing.*;import java.util.List;public class Main {    public static void main(String[] args) {        Twitter4jWrapper twitter4jWrapper;        List<String> list = FileManager.getAccessToken();        if (list.size() == 0 || list == null) {            twitter4jWrapper = new Twitter4jWrapper(Settings.consumerKey, Settings.consumerSecret);            PinInputWindow pinInputWindow = new PinInputWindow(twitter4jWrapper);            pinInputWindow.setVisible(true);        } else {            twitter4jWrapper = new Twitter4jWrapper(Settings.consumerKey, Settings.consumerSecret, list.get(0), list.get(1));            Window window = new Window(twitter4jWrapper);            window.setVisible(true);        }    }}