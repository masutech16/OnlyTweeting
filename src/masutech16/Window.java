package masutech16;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {

    private JTextArea content;

    Window() {
        JButton tweetButton = new JButton("投稿");
        tweetButton.addActionListener(this);

        JPanel p = new JPanel();
        p.add(tweetButton);

        content = new JTextArea();
        content.setColumns(20);
        content.setRows(8);


        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(content, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(content.getText());
    }
}
