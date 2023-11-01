package Library;

import Library.VIEW.VIEW_MODELS.Menu;
import Library.VIEW.VIEW_MODELS.ViewModels;
import Library.VIEW.VIEW_MODELS.Inputs;

import java.util.ArrayList;


public class View {
    public Presenter presenter;
    View(Presenter p){
        this.presenter = p;
    }
    public boolean confirm(String s){
        boolean flag = false;
            if (Inputs.my_input(s+" (Да-1/Нет-0) : ",0,1)==1){flag = true;};
        return flag;}
    public void printMessage(String s){ViewModels.printMessage(s);}

    public void getInfoBook()
        {if(presenter.getLibrary().getBooks().size()>0){
            int minId = presenter.getLibrary().getBooks().get(0).getId();
            int maxId = presenter.getLibrary().getBooksMaxId();
            int sel = Inputs.my_input("Введите ID книги : ",minId,maxId);
            ViewModels.printMessage(presenter.getInfoBook(sel));}
        else {ViewModels.printMessage("Добавьте книги в каталог. Пока в нем пусто.");}

        }

    public void getInfoListener(){
        if (presenter.getLibrary().getListeners().size() > 0){
            int minId = presenter.getLibrary().getListeners().get(0).getId();
            int maxId = presenter.getLibrary().getListenerMaxId();
            int sel = Inputs.my_input("Введите ID читателя : ",minId,maxId);
            ViewModels.printMessage(presenter.getInfoListener(sel));}
        else {ViewModels.printMessage("Добавьте читателей в реестр. Пока в нем пусто.");}

    }
    public void getInfoOrder(){
        if(presenter.getLibrary().getActiveOrders().size() > 0){
            int minId = presenter.getLibrary().getActiveOrderFromId(0).getId();
            int maxId = presenter.getLibrary().getOrdersMaxId();
            int sel = Inputs.my_input("Введите ID выдачи : ",minId,maxId);
            ViewModels.printMessage(presenter.getInfoOrder(sel));}
        else {ViewModels.printMessage("В списке выдач нет активных (незавершенных) выдач.");}

    }

    public void selectLibrary() {
        if (presenter.getListLibrary().getNotes().size() > 0) {
            int sel = Inputs.my_input("Введите ID библиотеки : ",
                    presenter.getListLibrary().getNotes().get(0).getId(),
                    presenter.getListLibrary().getMaxID());
            presenter.selectLibrary(sel);}
        else {ViewModels.printMessage("У вас нет доступных библиотек. Необходимо добавить.");
            presenter.pressButton();}}

    public void getLibraryMenu(String s){
        Menu menu = new Menu(this);
        menu.getLibraryMenu(s);}
    public void getMainMenu(String str){
        Menu menu = new Menu(this);
        menu.getMainMenu(str);}
    public void giveBook() {
        if (presenter.getLibrary().getBooks().size() > 0) {
            if (presenter.getLibrary().getListeners().size() > 0) {
                ViewModels.printMessage(presenter.giveBook(ViewModels.giveBook(
                presenter.getLibrary().getOrdersMaxId(), presenter.getLibrary().getBooks().get(0).getId(),
                presenter.getLibrary().getBooksMaxId(), presenter.getLibrary().getListeners().get(0).getId(),
                presenter.getLibrary().getListenerMaxId())));}
            else {ViewModels.printMessage("Добавьте читателей в реестр. Пока в нем пусто (выдавать некому).");}}
        else {ViewModels.printMessage("Добавьте книги в каталог. Пока в нем пусто (выдавать нечего).");}}


    public void ReturnBook() {
        if (presenter.getLibrary().getActiveOrders().size() > 0) {
            ViewModels.printMessage(presenter.ReturnBook(ViewModels.returnBook(
            presenter.getLibrary().getActiveOrders().get(0).getId(),
            presenter.getLibrary().getOrdersMaxId())));}
        else{ViewModels.printMessage("В списке выдач нет активных (незавершенных) выдач.");}

    }

    public void close()
        {presenter.close();}

    public void getCatalog()
        {ViewModels.printMessage(presenter.getCatalog());}
    public void getReestr()
        {ViewModels.printMessage(presenter.getReestr());}

    public void addBook()
        {ViewModels.printMessage(
                presenter.addBook(
                        ViewModels.addBook(presenter.getLibrary().getBooksMaxId())));}

    public void addListener()
        {ViewModels.printMessage(presenter.addListener(ViewModels.addListener(
                presenter.getLibrary().getListenerMaxId())));}
    public void removeBook() {
        if(presenter.getLibrary().getBooks().size()>0){
            int minBookId = presenter.getLibrary().getBooks().get(0).getId();
            int maxBookId = presenter.getLibrary().getBooksMaxId();
            int sel = Inputs.my_input("Введите ID книги для удаления : ",minBookId,maxBookId);
            ViewModels.printMessage(presenter.removeBook(sel));}
        else {ViewModels.printMessage("Добавьте книги в каталог. Пока в нем пусто. Удалять нечего.");}
    }

    public void removeListener() {
        if (presenter.getLibrary().getListeners().size() > 0){
            int minListenerId = presenter.getLibrary().getListeners().get(0).getId();
            int maxListenerId = presenter.getLibrary().getListenerMaxId();
            int sel = Inputs.my_input("Введите ID читателя для удаления : ",minListenerId,maxListenerId);
            ViewModels.printMessage(presenter.removeListener(sel));}
        else {ViewModels.printMessage("Добавьте читателей в реестр. Пока в нем пусто. Удалять некого.");}}

    public void removeLibrary() {
        if (presenter.getListLibrary().getNotes().size() > 0){
            int minLibraryId = presenter.getListLibrary().getNotes().get(0).getId();
            int maxLibraryId = presenter.getListLibrary().getMaxID();
            int sel = Inputs.my_input("Введите ID библиотеки для удаления : ",minLibraryId,maxLibraryId);
            ViewModels.printMessage(presenter.removeLibrary(sel));}
        else {ViewModels.printMessage("У вас нет доступных библиотек. Нечего удалять.");}
        presenter.pressButton();}

    public void mergeLibrary() {presenter.mergeLibrary();}

    public void addLibrary() {
        String s = ViewModels.addLibrary(presenter.getListLibrary().getMaxID());
        presenter.addLibrary(s);
    }

    public void exit() {presenter.exit();}

    public void getList()
        {ViewModels.printMessage(presenter.getList());}

    public void removeOneBook() {
        if(presenter.getLibrary().getBooks().size()>0){
            int minBookId = presenter.getLibrary().getBooks().get(0).getId();
            int maxBookId = presenter.getLibrary().getBooksMaxId();
            int sel = Inputs.my_input("Введите ID книги для удаления экземпляра : ",minBookId,maxBookId);
            ViewModels.printMessage(presenter.removeOneBook(sel));}
        else {ViewModels.printMessage("Добавьте книги в каталог. Пока в нем пусто. Удалять нечего.");}}

    public void editBook() {
        if(presenter.getLibrary().getBooks().size()>0){
            int minBookId = presenter.getLibrary().getBooks().get(0).getId();
            int maxBookId = presenter.getLibrary().getBooksMaxId();
            int sel = Inputs.my_input("Введите ID книги для редактирования : ",minBookId,maxBookId);
            ViewModels.printMessage(presenter.getBookInStrList(sel));}
        else {ViewModels.printMessage("Добавьте книги в каталог. Пока в нем пусто. Редактировать нечего.");}}
    public void editListener() {
        if (presenter.getLibrary().getListeners().size() > 0){
            int minListenerId = presenter.getLibrary().getListeners().get(0).getId();
            int maxListenerId = presenter.getLibrary().getListenerMaxId();
            int sel = Inputs.my_input("Введите ID читателя для редактирования : ",minListenerId,maxListenerId);
            ViewModels.printMessage(presenter.getListenerInStrList(sel));}
        else {ViewModels.printMessage("Добавьте читателей в реестр. Пока в нем пусто. Редактировать некого.");}}

    public ArrayList<String> startBookEditForm() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Inputs.my_input_str("  Название       : "));
        list.add(Inputs.my_input_str("  Автор          : "));
        list.add(Inputs.my_input_str("  Издательство   : "));
        list.add(String.valueOf(Inputs.my_input("  Кол-во страниц : ",1,5000)));
        return list;}

    public ArrayList<String> startListenerEditForm() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Inputs.my_input_str("  Фамилия             : "));
        list.add(Inputs.my_input_str("  Имя                 : "));
        list.add(String.valueOf(Inputs.my_input("  Пол (1-муж./2-жен.) : ",1,2)));
        list.add(Inputs.my_input_str("  Домашний адрес      : "));
        list.add(Inputs.my_input_str("  Телефон             : "));
        return list;}
}
