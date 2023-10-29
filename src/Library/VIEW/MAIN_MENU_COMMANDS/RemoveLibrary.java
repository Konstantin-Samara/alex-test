package Library.VIEW.MAIN_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class RemoveLibrary implements Command {
    View view;
    public RemoveLibrary(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Удалить библиотеку";
    }

    @Override
    public void execute() {
        view.removeLibrary();

    }
}
