package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class EditListener implements Command {
    View view;
    public EditListener(View view){this.view = view;}
    @Override
    public String label() {
        return "Редактировать читателя";
    }

    @Override
    public void execute() {
        view.editListener();

    }
}
