package OnlyTweeting;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener, DocumentListener {

    private JTextArea textArea;
    private Twitter4jWrapper twitter;
    private JLabel label;

    Window(Twitter4jWrapper twitter4jWrapper) {
        twitter = twitter4jWrapper;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 10, 300, 200);
        setTitle(Settings.APP_NAME);

        JPanel p = new JPanel();

        JButton tweetButton = new JButton("Tweet");
        tweetButton.addActionListener(this);
        p.add(tweetButton);

        label = new JLabel("140");
        p.add(label);


        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setColumns(20);
        textArea.setRows(8);
        textArea.getDocument().addDocumentListener(this);


        add(p, BorderLayout.CENTER);
        add(textArea, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        twitter.Tweet(textArea.getText());
        textArea.setText("");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setCharCount();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setCharCount();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        setCharCount();
    }

    private void setCharCount() {
        label.setText(String.valueOf(140 - textArea.getText().length()));
    }
}
