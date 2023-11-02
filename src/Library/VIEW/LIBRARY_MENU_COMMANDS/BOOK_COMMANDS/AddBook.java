package Library.VIEW.LIBRARY_MENU_COMMANDS.BOOK_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class AddBook implements Command {
    View view;
    public AddBook(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Добавить книгу";
    }

    @Override
    public void execute() {
        view.addBook();

    }
}
