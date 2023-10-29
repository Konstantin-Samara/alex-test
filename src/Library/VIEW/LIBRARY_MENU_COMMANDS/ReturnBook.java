package Library.VIEW.LIBRARY_MENU_COMMANDS;
import Library.VIEW.Command;
import Library.View;


public class ReturnBook implements Command {
    View view;
    public ReturnBook(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Вернуть книгу";
    }

    @Override
    public void execute() {
        view.ReturnBook();

    }
}
