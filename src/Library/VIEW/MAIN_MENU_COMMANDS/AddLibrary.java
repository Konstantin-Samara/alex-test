package Library.VIEW.MAIN_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class AddLibrary implements Command {
    View view;
    public AddLibrary(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Доавить библиотеку";
    }

    @Override
    public void execute() {
        view.addLibrary();

    }
}
