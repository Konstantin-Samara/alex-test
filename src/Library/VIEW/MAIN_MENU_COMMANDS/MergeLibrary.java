package Library.VIEW.MAIN_MENU_COMMANDS;

import Library.VIEW.Command;
import Library.View;

public class MergeLibrary implements Command {
    View view;
    public MergeLibrary(View v){
        this.view = v;
    }
    @Override
    public String label() {
        return "Объединить библиотеки";
    }

    @Override
    public void execute() {
        view.mergeLibrary();

    }
}
