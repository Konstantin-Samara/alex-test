package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class GetList implements Command {
    View view;
    public GetList(View view){this.view=view;}

    @Override
    public String label() {
        return "Список активных заказов.";
    }

    @Override
    public void execute() {
        view.getList();

    }
}
