package Library.VIEW.LIBRARY_MENU_COMMANDS.LISTENER_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class RemoveListener implements Command {
    View view;
    public RemoveListener(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Удалить читателя";
    }

    @Override
    public void execute() {
        view.removeListener();

    }
}
