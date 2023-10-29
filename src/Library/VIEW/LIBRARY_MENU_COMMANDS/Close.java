package Library.VIEW.LIBRARY_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class Close implements Command {
    View view;
    public Close(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Завершить работу с библиотекой";
    }

    @Override
    public void execute() {
        view.close();

    }
}
