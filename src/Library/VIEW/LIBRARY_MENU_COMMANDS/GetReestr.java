package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class GetReestr implements Command {
    View view;

    public GetReestr(View v){
        this.view=v;
    }
    @Override
    public String label() {
        return "Вывести рестр читателей";
    }

    @Override
    public void execute() {
        view.getReestr();

    }
}
