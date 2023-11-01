package Library.VIEW.LIBRARY_MENU_COMMANDS.BOOK_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class RemoveBook implements Command {
    View view;
    public RemoveBook(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Удалить книгу";
    }

    @Override
    public void execute() {
        view.removeBook();

    }
}
