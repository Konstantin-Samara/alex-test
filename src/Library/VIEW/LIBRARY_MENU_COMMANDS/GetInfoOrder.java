package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class GetInfoOrder implements Command {
    View view;
    public GetInfoOrder(View view) {this.view = view;}
    @Override
    public String label() {
        return "Подробнее о выдаче.";
    }

    @Override
    public void execute() {view.getInfoOrder();}
}
