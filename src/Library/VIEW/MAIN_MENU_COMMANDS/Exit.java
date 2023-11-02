package Library.VIEW.MAIN_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class Exit implements Command {
    View view;
    public Exit(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Выйти";
    }

    @Override
    public void execute() {
        view.exit();

    }
}
