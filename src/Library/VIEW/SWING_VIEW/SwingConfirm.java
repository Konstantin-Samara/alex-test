package Library.VIEW.SWING_VIEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingConfirm extends JFrame {
    static public final int WINDOW_HEIGHT = 200;
    static public final int WINDOW_WIDTH = 600;
    static public final int WINDOW_POS_Y = 100;
    static public final int WINDOW_POS_X = 100;
    static public final String WINDOW_NAME = "ПОДТВЕРЖДЕНИЕ";
    private String item;
    boolean test = false;
    public SwingConfirm(String item){
        this.item = item;
    }
    public boolean start(){
        setBounds(WINDOW_POS_X,WINDOW_POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle(WINDOW_NAME);
        setResizable(false);
        JButton butYes= new JButton("Подтвердить");
        JButton butNo = new JButton("Отменить");
        JPanel butPanel = new JPanel();
        butPanel.add(butYes);
        butPanel.add(butNo);

        JTextArea label = new JTextArea();
        label.setText(item);
        label.setEditable(false);
        JPanel labPanel = new JPanel(new BorderLayout());
        JScrollPane sp = new JScrollPane(label);
        labPanel.add(sp,BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(labPanel);
        mainPanel.add(butPanel,BorderLayout.SOUTH);
        add(mainPanel);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setVisible(true);

        butYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test = true;
                setVisible(false);
                }});
        butNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                test = false;
                setVisible(false);
            }
        });

        return test;}
}
