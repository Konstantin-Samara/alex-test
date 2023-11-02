package Library.VIEW.VIEW_MODELS;

import Library.VIEW.Command;
import Library.VIEW.LIBRARY_MENU_COMMANDS.*;
import Library.VIEW.LIBRARY_MENU_COMMANDS.BOOK_COMMANDS.*;
import Library.VIEW.LIBRARY_MENU_COMMANDS.LISTENER_COMMANDS.*;
import Library.VIEW.MAIN_MENU_COMMANDS.*;
import Library.View;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    View view;

    public Menu(View view) {
        this.view = view;
    }
    public List<Command> createLibraryMenu(){
        List<Command> commands = new ArrayList<>();
        BookMenu bookMenu = new BookMenu(view);
        commands.add(bookMenu);
        ListenerMenu listenerMenu = new ListenerMenu(view);
        commands.add(listenerMenu);
        if (view.presenter.getLibrary().getListeners().size()>0&&
                view.presenter.getLibrary().getBooks().size()>0) {
            GiveBook giveBook = new GiveBook(view);
            commands.add(giveBook);}
        if (view.presenter.getLibrary().getActiveOrders().size()>0) {
            ReturnBook returnBook = new ReturnBook(view);
            commands.add(returnBook);
            GetList getList = new GetList(view);
            commands.add(getList);
            GetInfoOrder getInfoOrder = new GetInfoOrder(view);
            commands.add(getInfoOrder);}
        Close close = new Close(view);
        commands.add(close);
        return commands;}
    public void getLibraryMenu(String s) {
        List<Command> commands = new ArrayList<>();
        int sel = -1;
        while (sel!=commands.size()) {
            commands = createLibraryMenu();
            System.out.println("Выбранная библиотека : "+s);
            for (int i = 0; i < commands.size(); i++)
                {System.out.println(i+1+". "+commands.get(i).label());}
            sel = Inputs.my_input(true,"Выберите пункт меню : ",1,commands.size());
            if (sel!=0) {commands.get(sel-1).execute();}}}

    public List<Command> createMainMenu(){

        List<Command> commands = new ArrayList<>();

        if(view.presenter.getListLibrary().getNotes().size()>0){
            SelectLibrary selectLibrary = new SelectLibrary(view);
            commands.add(selectLibrary);
            RemoveLibrary removeLibrary = new RemoveLibrary(view);
            commands.add(removeLibrary);}
        AddLibrary addLibrary = new AddLibrary(view);
        commands.add(addLibrary);
        if(view.presenter.getListLibrary().getNotes().size()>1){
            MergeLibrary mergeLibrary = new MergeLibrary(view);
            commands.add(mergeLibrary);}
        Exit exit = new Exit(view);
        commands.add(exit);

        return commands;}

    public void getMainMenu(String str){
        List<Command> commands = new ArrayList<>();
        int sel = -1;
            commands = createMainMenu();
            System.out.println("Список доступных библиотек : \n"+str);
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i+1+". "+commands.get(i).label());}
            sel = Inputs.my_input(true,"Выберите пункт меню : ",1,commands.size());
            if (sel!=0) {commands.get(sel-1).execute();}
    }
    public List<Command> createBookMenu(){
        List<Command> commands = new ArrayList<>();

        AddBook addBook = new AddBook(view);
        commands.add(addBook);
        if (view.presenter.getLibrary().getBooks().size()>0){
            GetCatalog getCatalog = new GetCatalog(view);
            commands.add(getCatalog);
            GetInfoBook getInfoBook = new GetInfoBook(view);
            commands.add(getInfoBook);
            RemoveBook removeBook = new RemoveBook(view);
            commands.add(removeBook);
            RemoveOneBook removeOneBook = new RemoveOneBook(view);
            commands.add(removeOneBook);
            EditBook editBook = new EditBook(view);
            commands.add(editBook);}

        return commands;}

    public void getBookMenu(String s) {
        List<Command> commands = new ArrayList<>();
        int sel = -1;
        while (sel!=commands.size()+1) {
            commands = createBookMenu();
            System.out.println("РАБОТАЕМ С КАТАЛОГОМ КНИГ ( "+s+" )");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i+1+". "+commands.get(i).label());
            }
            System.out.println((commands.size()+1)+". Вернутся в меню библиотеки");
            sel = Inputs.my_input(true,"Выберите пункт меню : ",1,commands.size()+1);
            if ((sel!=commands.size()+1&&(sel!=0))){commands.get(sel-1).execute();}
        }
    }
    public List<Command> createListenerMenu(){
        List<Command> commands = new ArrayList<>();
        AddListener addListener = new AddListener(view);
        commands.add(addListener);
        if (view.presenter.getLibrary().getListeners().size()>0){
            GetReestr getReestr = new GetReestr(view);
            commands.add(getReestr);
            GetInfoListener getInfoListener = new GetInfoListener(view);
            commands.add(getInfoListener);
            RemoveListener removeListener = new RemoveListener(view);
            commands.add(removeListener);
            EditListener editListener = new EditListener(view);
            commands.add(editListener);}
        return commands;}
    public void getListenerMenu(String s) {
        List<Command> commands = new ArrayList<>();


        int sel = -1;
        while (sel!=commands.size()+1) {
            commands = createListenerMenu();
            System.out.println("РАБОТАЕМ С РЕЕСТРОМ ЧИТАТЕЛЕЙ ( "+s+" )");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println(i+1+". "+commands.get(i).label());
            }
            System.out.println((commands.size()+1)+". Вернутся в меню библиотеки");
            sel = Inputs.my_input(true,"Выберите пункт меню : ",1,commands.size()+1);
            if ((sel!=commands.size()+1)&&(sel!=0)){commands.get(sel-1).execute();}
        }
    }

}