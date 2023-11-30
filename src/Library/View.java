package Library;

import Library.VIEW.SWING_VIEW.*;
import Library.VIEW.VIEW_MODELS.Menu;
import Library.VIEW.VIEW_MODELS.ViewModels;
import Library.VIEW.VIEW_MODELS.Inputs;

import java.util.ArrayList;


public class View {
    public Presenter presenter;
    public ArrayList<String[]> catalog;
    public ArrayList<String[]> reestr;
    public String libraryName;
    private ArrayList<String[]> activeOrders;



    View(Presenter p){
        this.presenter = p;
    }
    public boolean confirm(String s){
        boolean flag = false;
            if (Inputs.my_input(true,s+" (Да-1/Нет-0) : ",0,1)==1)
                {flag = true;}
        return flag;}

    public void printMessage(String s){
        if (presenter.isSwing()){new SwingMessage(s);}
        else {ViewModels.printMessage(s);}}

    public void getInfoBook(){
        int minId = presenter.getLibrary().getBooks().get(0).getId();
        int maxId = presenter.getLibrary().getBooksMaxId();
        int sel = Inputs.my_input(true,"Введите ID книги : ",minId,maxId);
        printMessage(presenter.getInfoBook(sel));}
    public ArrayList<String[]>[] getInfoBookSwing(int selBook)
        {return presenter.getInfoBookSwing(selBook);}

    public void getInfoListener(){
        int minId = presenter.getLibrary().getListeners().get(0).getId();
        int maxId = presenter.getLibrary().getListenerMaxId();
        int sel = Inputs.my_input(true,"Введите ID читателя : ",minId,maxId);
        printMessage(presenter.getInfoListener(sel));}
    public ArrayList<String[]>[] getInfoListenerSwing(int sel)
        {return presenter.getInfoListenerSwing(sel);}


    public void getInfoOrder(){
        int minId = presenter.getLibrary().getActiveOrderFromId(0).getId();
        int maxId = presenter.getLibrary().getOrdersMaxId();
        int sel = Inputs.my_input(true,"Введите ID выдачи : ",minId,maxId);
        printMessage(presenter.getInfoOrder(sel));}
    public String[] getInfoActiveOrderSwing(int sel) {
        return presenter.getInfoActiveOrderSwing(sel);}

    public void selectLibrary(int sel) {
       if (!presenter.isSwing()) {
            sel = Inputs.my_input(true,"Введите ID библиотеки : ",
            presenter.getListLibrary().getNotes().get(0).getId(),
            presenter.getListLibrary().getMaxID());}
        presenter.selectLibrary(sel);}

    public void getLibraryMenu(String s){
        if (presenter.isSwing()){
            getCatalog();getReestr();getList();
            libraryName = s;
            new SwingLibraryMenu(this,libraryName,catalog,reestr,activeOrders);}
        else {
            Menu menu = new Menu(this);
            menu.getLibraryMenu(s);
            presenter.getMainMenu();}
    }
    public void getMainMenu(ArrayList<String[]> arr){
        if (presenter.isSwing())
            {new SwingMainMenu(this,arr);}
        else {
            Menu menu = new Menu(this);
            menu.getMainMenu(arr);}}

    public void giveBook() {
        printMessage(presenter.giveBook(ViewModels.giveBook(
        presenter.getLibrary().getOrdersMaxId(), presenter.getLibrary().getBooks().get(0).getId(),
        presenter.getLibrary().getBooksMaxId(), presenter.getLibrary().getListeners().get(0).getId(),
        presenter.getLibrary().getListenerMaxId())));}
    public void ReturnBook() {
        printMessage(presenter.ReturnBook(ViewModels.returnBook(
        presenter.getLibrary().getActiveOrders().get(0).getId(),
        presenter.getLibrary().getOrdersMaxId())));}

    public void close()
        {presenter.close();}

    public void getCatalog(){
        catalog = new ArrayList<>();
        catalog = presenter.getCatalog();
        if (!presenter.isSwing()) {
            String s ="";
            for (String[] item:catalog)
                {s=s+"( ID:"+item[0]+" ) "+item[1]+" "+item[2]+"\n";}
            printMessage(s);}
    }
    public void getReestr(){
        reestr = new ArrayList<>();
        reestr = presenter.getReestr();
        if (!presenter.isSwing()) {
            String s ="";
            for (String[] item:reestr)
            {s=s+"( ID:"+item[0]+" ) "+item[1]+" "+item[2]+"\n";}
            printMessage(s);}
    }

    public void addBook()
        {printMessage(
                presenter.addBook(
                        ViewModels.addBook(presenter.getLibrary().getBooksMaxId())));}

    public void addListener()
        {printMessage(presenter.addListener(ViewModels.addListener(
                presenter.getLibrary().getListenerMaxId())));}
    public void removeBook() {
        int minBookId = presenter.getLibrary().getBooks().get(0).getId();
        int maxBookId = presenter.getLibrary().getBooksMaxId();
        int sel = Inputs.my_input(true,"Введите ID книги для удаления : ",minBookId,maxBookId);
        printMessage(presenter.removeBook(sel));}

    public void removeListener() {
        int minListenerId = presenter.getLibrary().getListeners().get(0).getId();
        int maxListenerId = presenter.getLibrary().getListenerMaxId();
        int sel = Inputs.my_input(true,"Введите ID читателя для удаления : ",minListenerId,maxListenerId);
        printMessage(presenter.removeListener(sel));}

    public void removeLibrary() {
        int minLibraryId = presenter.getListLibrary().getNotes().get(0).getId();
        int maxLibraryId = presenter.getListLibrary().getMaxID();
        int sel = Inputs.my_input(true,"Введите ID библиотеки для удаления : ",minLibraryId,maxLibraryId);
        printMessage(presenter.removeLibrary(sel,true));
        presenter.getMainMenu();}

    public void mergeLibrary() {
        int minLibraryId = presenter.getListLibrary().getNotes().get(0).getId();
        int maxLibraryId = presenter.getListLibrary().getMaxID();
        int sel = Inputs.my_input(true,"Введите ID базовой библиотеки для слияния : ",minLibraryId,maxLibraryId);
        int sel1 = Inputs.my_input(true,"Введите ID присоединяемой библиотеки : ",minLibraryId,maxLibraryId);
        printMessage(presenter.mergeLibrary(sel,sel1));
        presenter.getMainMenu();}

    public void addLibrary() {
        String s = ViewModels.addLibrary(presenter.getListLibrary().getMaxID());
        presenter.addLibrary(s);
    }

    public void exit()
        {presenter.exit();}

    public void getList(){
        activeOrders = new ArrayList<>();
        activeOrders = presenter.getList();
        if (!presenter.isSwing()){
            String s ="";
            for (String[] item:activeOrders)
            {s=s+"( ID:"+item[0]+" ) "+item[1]+" "+item[2]+" --- "+item[3]+" "+item[4]+"\n";}
            printMessage(s);}
        }

    public void removeOneBook() {
        int minBookId = presenter.getLibrary().getBooks().get(0).getId();
        int maxBookId = presenter.getLibrary().getBooksMaxId();
        int sel = Inputs.my_input(true,"Введите ID книги для удаления экземпляра : ",minBookId,maxBookId);
        printMessage(presenter.removeOneBook(sel));}

    public void editBook() {
        int minBookId = presenter.getLibrary().getBooks().get(0).getId();
        int maxBookId = presenter.getLibrary().getBooksMaxId();
        int sel = Inputs.my_input(true,"Введите ID книги для редактирования : ",minBookId,maxBookId);
        printMessage(presenter.editBook(sel));}
    public void editListener() {
        int minListenerId = presenter.getLibrary().getListeners().get(0).getId();
        int maxListenerId = presenter.getLibrary().getListenerMaxId();
        int sel = Inputs.my_input(true,"Введите ID читателя для редактирования : ",minListenerId,maxListenerId);
        printMessage(presenter.editListener(sel));}

    public ArrayList<String> startBookEditForm() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Inputs.my_input_str(false,"  Название       : "));
        list.add(Inputs.my_input_str(false,"  Автор          : "));
        list.add(Inputs.my_input_str(false,"  Издательство   : "));
        list.add(String.valueOf(Inputs.my_input(false,"  Кол-во страниц : ",1,5000)));
        return list;}

    public ArrayList<String> startListenerEditForm() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Inputs.my_input_str(false,"  Фамилия             : "));
        list.add(Inputs.my_input_str(false,"  Имя                 : "));
        list.add(String.valueOf(Inputs.my_input(false,"  Пол (1-муж./2-жен.) : ",1,2)));
        list.add(Inputs.my_input_str(false,"  Домашний адрес      : "));
        list.add(Inputs.my_input_str(false,"  Телефон             : "));
        return list;}

    public void BookMenu() {
        Menu menu = new Menu(this);
        menu.getBookMenu(presenter.getLibrary().getName());}

    public void ListenerMenu() {
        Menu menu = new Menu(this);
        menu.getListenerMenu(presenter.getLibrary().getName());}

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }



}
