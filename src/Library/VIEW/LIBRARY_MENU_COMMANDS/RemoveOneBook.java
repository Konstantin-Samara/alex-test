package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class RemoveOneBook implements Command {
    View view;
    public RemoveOneBook(View view){this.view = view;}
    @Override
    public String label() {
        return "Утилизировать экз.книги";
    }

    @Override
    public void execute() {
        view.removeOneBook();
    }
}
