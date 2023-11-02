package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class ListenerMenu implements Command {
    View view;
    public ListenerMenu(View view){this.view = view;}
    @Override
    public String label() {
        return "РАБОТАТЬ С РЕЕСТРОМ ЧИТАТЕЛЕЙ";
    }

    @Override
    public void execute() {
        view.ListenerMenu();
    }
}
