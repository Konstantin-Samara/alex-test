package Library;

import Library.VIEW.VIEW_MODELS.Menu;
import Library.VIEW.VIEW_MODELS.ViewModels;
import Library.VIEW.VIEW_MODELS.Inputs;



public class View {
    Presenter presenter;
    View(Presenter p){
        this.presenter = p;
    }

    public void getInfoBook()
        {   int minId = presenter.library.getBooks().get(0).getId();
            int maxId = presenter.library.getBooksMaxId();
            int sel = Inputs.my_input("Введите ID книги : ",minId,maxId);
            ViewModels.printMessage(presenter.getInfoBook(sel));}

    public void getInfoListener()
        {   int minId = presenter.library.getListeners().get(0).getId();
            int maxId = presenter.library.getListenerMaxId();
            int sel = Inputs.my_input("Введите ID читателя : ",minId,maxId);
            ViewModels.printMessage(presenter.getInfoListener(sel));}
    public void getInfoOrder()
        {   int minId = presenter.library.getActiveOrderFromId(0).getId();
            int maxId = presenter.library.getOrdersMaxId();
            int sel = Inputs.my_input("Введите ID выдачи : ",minId,maxId);
            ViewModels.printMessage(presenter.getInfoOrder(sel));}

    public void selectLibrary() {
        int sel = Inputs.my_input("Введите ID библиотеки : ",1,presenter.listLibrary.getMaxID());
        presenter.selectLibrary(sel);}

    public void getLibraryMenu(String s){
        Menu menu = new Menu(this);
        menu.getLibraryMenu(s);}
    public void getMainMenu(String str){
        Menu menu = new Menu(this);
        menu.getMainMenu(str);}
    public void giveBook(){
        ViewModels.printMessage(
        presenter.giveBook(ViewModels.giveBook(presenter.library.getOrdersMaxId(),
                presenter.library.getBooks().get(0).getId(),presenter.library.getBooksMaxId(),
                presenter.library.getListeners().get(0).getId(),presenter.library.getListenerMaxId())));}

    public void ReturnBook() {
        ViewModels.printMessage(presenter.ReturnBook(ViewModels.returnBook(
                presenter.library.getActiveOrders().get(0).getId(),presenter.library.getOrdersMaxId())));}

    public void close()
        {presenter.close();}

    public void getCatalog()
        {ViewModels.printMessage(presenter.getCatalog());}
    public void getReestr()
        {ViewModels.printMessage(presenter.getReestr());}

    public void addBook()
        {ViewModels.printMessage(
                presenter.addBook(
                        ViewModels.addBook(presenter.library.getBooksMaxId())));}

    public void addListener()
        {presenter.addListener(ViewModels.addListener(presenter.library.getListenerMaxId()));}
    public void removeBook() {
        int minBookId = presenter.library.getBooks().get(0).getId();
        int maxBookId = presenter.library.getBooksMaxId();
        int sel = Inputs.my_input("Введите ID книги для удаления : ",minBookId,maxBookId);
        ViewModels.printMessage(presenter.removeBook(sel));}

    public void removeListener() {
        int minListenerId = presenter.library.getListeners().get(0).getId();
        int maxListenerId = presenter.library.getListenerMaxId();
        int sel = Inputs.my_input("Введите ID читателя для удаления : ",minListenerId,maxListenerId);
        ViewModels.printMessage(presenter.removeListener(sel));}

    public void removeLibrary() {
        int minLibraryId = presenter.listLibrary.getNotes().get(0).getId();
        int maxLibraryId = presenter.listLibrary.getMaxID();
        int sel = Inputs.my_input("Введите ID библиотеки для удаления : ",minLibraryId,maxLibraryId);
        presenter.removeLibrary(sel);
        presenter.pressButton();
    }

    public void mergeLibrary() {presenter.mergeLibrary();}

    public void addLibrary() {
        String s = ViewModels.addLibrary(presenter.listLibrary.getMaxID());
        presenter.addLibrary(s);
        presenter.pressButton();
    }

    public void exit() {presenter.exit();}

    public void getList()
        {ViewModels.printMessage(presenter.getList());}
}
