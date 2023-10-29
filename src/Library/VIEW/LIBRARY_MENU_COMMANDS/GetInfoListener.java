package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class GetInfoListener implements Command {
    View view;
    public GetInfoListener(View view) {this.view = view;}
    @Override
    public String label() {
        return "Подробнее о читателе.";
    }

    @Override
    public void execute() {view.getInfoListener();}
}
