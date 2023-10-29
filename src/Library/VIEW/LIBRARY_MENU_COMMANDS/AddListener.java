package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class AddListener implements Command {
    View view;
    public AddListener(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Добавить читателя";
    }

    @Override
    public void execute() {
        view.addListener();

    }
}
