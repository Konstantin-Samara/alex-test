package Library.VIEW.MAIN_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class SelectLibrary implements Command {
    View view;
    public SelectLibrary(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Выбрать библиотеку";
    }

    @Override
    public void execute() {
        view.selectLibrary(0);

    }
}
