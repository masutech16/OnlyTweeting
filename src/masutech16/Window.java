package masutech16;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {

    private JTextArea content;
    private Twitter4jWrapper twitter;

    Window() {
        JButton tweetButton = new JButton("POST");
        tweetButton.addActionListener(this);

        JPanel p = new JPanel();
        p.add(tweetButton);

        content = new JTextArea();
        content.setColumns(20);
        content.setRows(8);


        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(content, BorderLayout.PAGE_END);

        twitter = new Twitter4jWrapper();
    }

    public void actionPerformed(ActionEvent e) {
        twitter.Tweet(content.getText());
    }
}
