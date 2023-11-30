package Library.VIEW.SWING_VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TempFrame extends JFrame {
    public TempFrame(int x, int y, int width, int height){
        setBounds(x,y,width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        JButton button = new JButton("close");
        panel.add(button);
        add(panel);
        setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
