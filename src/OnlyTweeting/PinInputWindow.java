package OnlyTweeting;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
;import static javax.swing.JOptionPane.showMessageDialog;

public class PinInputWindow extends JFrame implements ActionListener{

    private Twitter4jWrapper twitter4jWrapper = null;
    private JTextField textField = null;

    PinInputWindow(Twitter4jWrapper twitter4jWrapper) {
        this.twitter4jWrapper = twitter4jWrapper;
        setTitle(Settings.APP_NAME);
        setBounds(100,100,350,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URLLabel label = new URLLabel(twitter4jWrapper.getRequestTokenUrl(),"ここに表示されるPINコードを入力してください");
        add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();

        textField = new JTextField(10);
        panel.add(textField);

        JButton btn = new JButton("submit");
        btn.addActionListener(this);
        panel.add(btn);

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if(twitter4jWrapper.canCreateAccessToken(textField.getText())) {
            dispose();
            Window window = new Window(twitter4jWrapper);
            window.setVisible(true);
        } else {
            showMessageDialog(this, "PINコードの値が間違っています");
            textField.setText("");
        }
    }
}
