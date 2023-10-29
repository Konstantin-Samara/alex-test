package Library.VIEW.VIEW_MODELS;

import Library.VIEW.Command;
import Library.VIEW.LIBRARY_MENU_COMMANDS.*;
import Library.VIEW.MAIN_MENU_COMMANDS.*;
import Library.View;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    View view;

    public Menu(View view) {
        this.view = view;
    }

    public void getLibraryMenu(String s) {
        List<Command> commands = new ArrayList<>();

        GiveBook giveBook = new GiveBook(view);
        commands.add(giveBook);
        ReturnBook returnBook = new ReturnBook(view);
        commands.add(returnBook);

        GetCatalog getCatalog = new GetCatalog(view);
        commands.add(getCatalog);
        GetReestr getReestr = new GetReestr(view);
        commands.add(getReestr);
        GetList getList = new GetList(view);
        commands.add(getList);

        GetInfoBook getInfoBook = new GetInfoBook(view);
        commands.add(getInfoBook);
        GetInfoListener getInfoListener = new GetInfoListener(view);
        commands.add(getInfoListener);
        GetInfoOrder getInfoOrder = new GetInfoOrder(view);
        commands.add(getInfoOrder);

        AddBook addBook = new AddBook(view);
        commands.add(addBook);
        RemoveBook removeBook = new RemoveBook(view);
        commands.add(removeBook);
        AddListener addListener = new AddListener(view);
        commands.add(addListener);
        RemoveListener removeListener = new RemoveListener(view);
        commands.add(removeListener);
        Close close = new Close(view);
        commands.add(close);

        int sel = -1;
        while (sel!=commands.size()) {
            System.out.println("Выбранная библиотека : "+s);
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i+1+". "+commands.get(i).label());
            }
            sel = Inputs.my_input("Выберите пункт меню : ",1,commands.size());
            commands.get(sel-1).execute();}

    }
    public void getMainMenu(String str){

        List<Command> commands = new ArrayList<>();
        SelectLibrary selectLibrary = new SelectLibrary(view);
        commands.add(selectLibrary);
        AddLibrary addLibrary = new AddLibrary(view);
        commands.add(addLibrary);
        RemoveLibrary removeLibrary = new RemoveLibrary(view);
        commands.add(removeLibrary);
        MergeLibrary mergeLibrary = new MergeLibrary(view);
        commands.add(mergeLibrary);
        Exit exit = new Exit(view);
        commands.add(exit);

        int sel = -1;

            System.out.println("Список доступных библиотек : \n"+str);
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i+1+". "+commands.get(i).label());
            }
            sel = Inputs.my_input("Выберите пункт меню : ",1,commands.size());
            commands.get(sel-1).execute();


    }

}