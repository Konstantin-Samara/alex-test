package Library.VIEW.SWING_VIEW;

import Library.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwingMainMenu extends JFrame {
    static public final int WINDOW_HEIGHT = 500;
    static public final int WINDOW_WIDTH = 500;
    static public final int WINDOW_POS_Y = 100;
    static public final int WINDOW_POS_X = 100;
    static public final String WINDOW_NAME = "ГЛАВНОЕ МЕНЮ БИБЛИОТЕК";
    View v;
    public SwingMainMenu(View v,ArrayList<String[]> comboItem){
        this.v = v;
        setBounds(WINDOW_POS_X,WINDOW_POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle(WINDOW_NAME);

        JLabel label = new JLabel("ДОБРО ПОЖАЛОВАТЬ!",SwingConstants.CENTER);
        JPanel labPanel = new JPanel();
        labPanel.add(label);

        JButton butAdd = new JButton("Добавить");
        JButton butExit = new JButton("Выход");
        JButton butSelect = new JButton("Выбрать");
        JButton butRemove = new JButton("Удалить");
        JButton butMerge = new JButton("Объеденить");
        JPanel butPanel = new JPanel(new GridLayout(5,1));
        butPanel.add(butAdd);
        butPanel.add(butExit);
        butPanel.add(butSelect);
        butPanel.add(butRemove);
        butPanel.add(butMerge);

        JComboBox<String> comboBox = new JComboBox<>();
        for (String[] item:comboItem) {comboBox.addItem("( ID:"+item[0]+" ) "+item[1]);}
        comboBox.setSelectedItem(null);
        JPanel comboPanel = new JPanel(new BorderLayout());
        comboPanel.add(comboBox,BorderLayout.NORTH);

        JPanel itemPanel = new JPanel(new GridLayout(1,2));
        itemPanel.add(comboPanel);
        itemPanel.add(butPanel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(labPanel,BorderLayout.NORTH);
        mainPanel.add(itemPanel,BorderLayout.CENTER);

        add(mainPanel);

        if (comboItem.size()<2){
            butMerge.setEnabled(false);
            if (comboItem.size()<1){
                butRemove.setEnabled(false);
                butSelect.setEnabled(false);}}


        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setVisible(true);
        butExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }});
        butSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem()!=null){
                String[] arrSel = comboItem.get(comboBox.getSelectedIndex());
                int sel = Integer.valueOf(arrSel[0]);
//                setVisible(false);
                v.selectLibrary(sel);}
            }
        });

    }
}
