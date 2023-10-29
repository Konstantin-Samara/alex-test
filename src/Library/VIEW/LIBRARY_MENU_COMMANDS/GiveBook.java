package Library.VIEW.LIBRARY_MENU_COMMANDS;


import Library.VIEW.Command;
import Library.View;

public class GiveBook implements Command {
    View view;

    public GiveBook(View v){
        this.view=v;
    }



    @Override
    public String label() {
        return "Выдать книгу";
    }

    @Override
    public void execute() {view.giveBook();}
}
