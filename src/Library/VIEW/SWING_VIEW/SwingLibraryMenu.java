package Library.VIEW.SWING_VIEW;

import Library.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SwingLibraryMenu extends JFrame {
    static public final int WINDOW_HEIGHT = 700;
    static public final int WINDOW_WIDTH = 1100;
    static public final int WINDOW_POS_Y = 100;
    static public final int WINDOW_POS_X = 100;
    private ArrayList<String[]> catalog,reestr,activeOrders;

    private View v;
    private int selListener,selBook,selActiveOrder;
    private ArrayList<String[]>[] selListenerInfo,selBookInfo;
    private String[] selActiveOrderInfo;

    private JLabel
            titleLabel = new JLabel("ДОБРО ПОЖАЛОВАТЬ!",SwingConstants.CENTER);
    private JComboBox<String>
            listenersBox = new JComboBox<>(),
            booksBox = new JComboBox<>(),
            activeOrdersBox = new JComboBox<>(),
            listenerInfoBox = new JComboBox<>(),
            bookInfoBox = new JComboBox<>(),
            activeOrderInfoBox = new JComboBox<>();
    private JButton
            butExit = new JButton("ВЫХОД");
    private JPanel
            mainPanel = new JPanel(new BorderLayout()),
            itemPanel = new JPanel(new GridLayout(1,3)),
            butPanel = new JPanel(),labPanel = new JPanel(),
            listenerPanel= new JPanel(new BorderLayout()),
            bookPanel= new JPanel(new BorderLayout()),
            activeOrdersPanel = new JPanel(new BorderLayout()),
            listenerInfoPanel= new JPanel(new BorderLayout()),
            bookInfoPanel= new JPanel(new BorderLayout()),
            activeOrdersInfoPanel = new JPanel(new BorderLayout());;


    public SwingLibraryMenu(View v,String s,ArrayList<String[]> catalog,ArrayList<String[]> reestr, ArrayList<String[]> activeOrders){
        this.catalog = catalog;
        this.reestr = reestr;
        this.activeOrders = activeOrders;
        this.v = v;
        setBounds(WINDOW_POS_X,WINDOW_POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle(s);

        labPanel.add(titleLabel);

        for (String[] item:reestr) {
            listenersBox.addItem("( ID:"+item[0]+" ) "+item[1]+" "+item[2]);}
        listenersBox.setSelectedItem(null);
        listenersBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {listenerInfoComplete();}});
        listenerInfoPanel.add(listenerInfoBox,BorderLayout.NORTH);
        listenerPanel.add(listenersBox,BorderLayout.NORTH);
        listenerPanel.add(listenerInfoPanel,BorderLayout.CENTER);



        for (String[] item:catalog) {
            booksBox.addItem("( ID:"+item[0]+" ) "+item[1]+" "+item[2]);}
        booksBox.setSelectedItem(null);
        booksBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {bookInfoComplete();}});
        bookInfoPanel.add(bookInfoBox,BorderLayout.NORTH);
        bookPanel.add(booksBox,BorderLayout.NORTH);
        bookPanel.add(bookInfoPanel,BorderLayout.CENTER);

        for (String[] item:activeOrders) {
            activeOrdersBox.addItem("( ID:"+item[0]+" ) "+item[1]+" "+item[2]+" --- "+item[3]+" "+item[4]);}
        activeOrdersBox.setSelectedItem(null);
        activeOrdersBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {ActiveOrderInfoComplete();}});
        activeOrdersInfoPanel.add(activeOrderInfoBox,BorderLayout.NORTH);
        activeOrdersPanel.add(activeOrdersBox,BorderLayout.NORTH);
        activeOrdersPanel.add(activeOrdersInfoPanel,BorderLayout.CENTER);

        itemPanel.add(listenerPanel);itemPanel.add(bookPanel);itemPanel.add(activeOrdersPanel);

        butPanel.add(butExit);

        mainPanel.add(labPanel,BorderLayout.NORTH);
        mainPanel.add(itemPanel,BorderLayout.CENTER);
        mainPanel.add(butPanel,BorderLayout.SOUTH);
        add(mainPanel);

        setResizable(false);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setVisible(true);

        butExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
//                new SwingMainMenu();
            }
        });
    }
    public void listenerInfoComplete(){
        String[] listener = reestr.get(listenersBox.getSelectedIndex());
        selListener = Integer.valueOf(listener[0]);
        selListenerInfo = new ArrayList[2];
        selListenerInfo = v.getInfoListenerSwing(selListener);
        listenerInfoBox.removeAllItems();
        for (String[] item:selListenerInfo[1]) {
            listenerInfoBox.addItem("( ID:"+item[2]+" ) "+item[3]);}
    }
    public void bookInfoComplete(){
        String[] book = catalog.get(booksBox.getSelectedIndex());
        selBook = Integer.valueOf(book[0]);
        selBookInfo = new ArrayList[2];
        selBookInfo = v.getInfoBookSwing(selBook);
        bookInfoBox.removeAllItems();
        for (String[] item:selBookInfo[1]) {
            bookInfoBox.addItem("( ID:"+item[2]+" ) "+item[3]);}
    }
    public void ActiveOrderInfoComplete(){
        String[] activeOrder = activeOrders.get(activeOrdersBox.getSelectedIndex());
        selActiveOrder = Integer.valueOf(activeOrder[0]);
        selActiveOrderInfo = new String[6];
        selActiveOrderInfo = v.getInfoActiveOrderSwing(selActiveOrder);

    }

}
