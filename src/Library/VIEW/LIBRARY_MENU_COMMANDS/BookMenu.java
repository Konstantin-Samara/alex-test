package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class BookMenu implements Command {
    View view;
    public BookMenu(View view) {this.view = view;}
    @Override
    public String label() {
        return "РАБОТАТЬ С КАТАЛОГОМ КНИГ";
    }

    @Override
    public void execute() {
        view.BookMenu();
    }
}
