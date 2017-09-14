package OnlyTweeting;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {

    private JTextArea content;
    private Twitter4jWrapper twitter;

    Window(Twitter4jWrapper twitter4jWrapper) {
        twitter = twitter4jWrapper;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 300, 200);
        setTitle(Settings.APP_NAME);

        JButton tweetButton = new JButton("Tweet");
        tweetButton.addActionListener(this);

        JPanel p = new JPanel();
        p.add(tweetButton);

        content = new JTextArea();
        content.setColumns(20);
        content.setRows(8);


        add(p, BorderLayout.CENTER);
        add(content, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        twitter.Tweet(content.getText());
    }
}
