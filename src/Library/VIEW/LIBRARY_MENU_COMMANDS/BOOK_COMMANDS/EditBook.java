package Library.VIEW.LIBRARY_MENU_COMMANDS.BOOK_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class EditBook implements Command {
    View view;
    public EditBook(View view){this.view = view;}

    @Override
    public String label() {
        return "Редактировать книгу";
    }

    @Override
    public void execute() {
        view.editBook();

    }
}
