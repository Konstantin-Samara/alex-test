package Library.VIEW.SWING_VIEW;

import Library.Presenter;

import javax.swing.*;

public class SwingMainMenu extends JFrame {
    static public final int WINDOW_HEIGHT = 500;
    static public final int WINDOW_WIDTH = 500;
    static public final int WINDOW_POS_Y = 100;
    static public final int WINDOW_POS_X = 100;
    static public final String WINDOW_NAME = "ГЛАВНОЕ МЕНЮ БИБЛИОТЕК";
    private Presenter p;
    public SwingMainMenu(Presenter p){
        this.p = p;
        setBounds(WINDOW_POS_X,WINDOW_POS_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(WINDOW_NAME);
        setResizable(false);

    }
}
