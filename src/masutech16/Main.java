package masutech16;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Main {


    public static void main(String[] args) {
        Window frame = new Window();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 300, 200);
        frame.setTitle("OnlyTweeting");
        frame.setVisible(true);
    }


}
