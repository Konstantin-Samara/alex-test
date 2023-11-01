package Library.VIEW.LIBRARY_MENU_COMMANDS.BOOK_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class GetInfoBook implements Command {
    View view;
    public GetInfoBook(View view) {this.view = view;}
    @Override
    public String label() {
        return "Подробнее о книге.";
    }

    @Override
    public void execute() {view.getInfoBook();}
}
