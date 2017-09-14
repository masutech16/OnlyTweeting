package OnlyTweeting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

/**
 * Created by Masaki on 2017/09/13.
 */
public class URLLabel extends JLabel {
    private String url = null;
    URLLabel(String url, String text) {
        super("<html><a href='" + url + "'>" + text + "</a></html>");
        this.url = url;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!Desktop.isDesktopSupported()) {
                    return;
                }
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch(Exception ex) {
                    System.out.println(ex.getStackTrace());
                    System.exit(1);
                }
            }
        });
    }

    URLLabel(String url) {
        this(url,url);
    }
}
