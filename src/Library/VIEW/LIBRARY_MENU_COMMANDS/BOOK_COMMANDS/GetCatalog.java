package Library.VIEW.LIBRARY_MENU_COMMANDS.BOOK_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class GetCatalog implements Command {
    View view;

    public GetCatalog(View v){
        this.view=v;
    }
    @Override
    public String label() {
        return "Вывести каталог книг";
    }

    @Override
    public void execute() {
        view.getCatalog();
    }
}
