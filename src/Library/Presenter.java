package Library;

import Library.CLASSES.Library;
import Library.CLASSES.ListLibrary;
import Library.MODELS.HandCreate;
import Library.MODELS.Models;
import Library.MODELS.WriteRead;
import Library.VIEW.VIEW_MODELS.Inputs;

import java.util.ArrayList;


public class Presenter {
    View v = new View(this);
    Models m = new Models(this);
    private ListLibrary listLibrary;
    private Library library;
    private boolean swing;

    public void pressButton() {
        if (Inputs.my_input(false, "Загрузить из файла - 1/сформировать шаблон - 0(ENTER) : ", 0, 1) == 1) {
            setListLibrary((ListLibrary) WriteRead.read("./src/Library/DATA/listlibrary.out"));
        } else {
            setListLibrary(HandCreate.createLibrarys());
        }
        if (Inputs.my_input(false, "Консоль - 1/SWING - 0(ENTER) : ", 0, 1) == 1) {
            swing = false;
        } else {
            swing = true;
        }
        getMainMenu();
    }

    public void getMainMenu() {
        v.getMainMenu(m.getArrListLibrary());
    }

    public void printMessage(String s) {
        v.printMessage(s);
    }

    public boolean confirm(String s) {
        return v.confirm(s);
    }

    public String giveBook(ArrayList<String> giveBook) {
        return m.giveBook(giveBook);
    }

    public String ReturnBook(String[] returnBook) {
        return m.returnBook(returnBook);
    }

    public ArrayList<String[]> getCatalog() {
        return m.getCatalog();
    }

    public ArrayList<String[]> getReestr() {
        return m.getReestr();
    }

    public void close() {
        m.close();
    }

    public String addBook(ArrayList<String> book) {
        return m.addBook(book);
    }

    public String addListener(ArrayList<String> listener) {
        return m.addListener(listener);
    }

    public String removeBook(int sel) {
        return m.removeBook(sel);
    }

    public String removeListener(int sel) {
        return m.removeListener(sel);
    }

    public void selectLibrary(int sel) {
        m.selectLibrary(sel);
    }

    public void startLibraryMenu(String s) {
        v.getLibraryMenu(s);
    }

    public String removeLibrary(int sel, boolean needCheckConfirm) {
        return m.removeLibrary(sel, needCheckConfirm);
    }

    public String mergeLibrary(int sel, int sel1) {
        return m.mergeLibrary(sel, sel1);
    }

    public void addLibrary(String s) {
        m.addLibrary(s);
    }

    public void exit() {
        v.printMessage("До встречи!");
    }

    public ArrayList<String[]> getList() {
        return m.getList();
    }

    public String getInfoBook(int sel) {
        return m.getInfoBook(sel);
    }

    public ArrayList<String[]>[] getInfoBookSwing(int selBook) {
        return m.getInfoBookSwing(selBook);
    }

    public String getInfoListener(int sel) {
        return m.getInfoListener(sel);
    }

    public ArrayList<String[]>[] getInfoListenerSwing(int sel) {
        return m.getInfoListenerSwing(sel);
    }

    public String getInfoOrder(int sel) {
        return m.getInfoOrder(sel);
    }

    public String[] getInfoActiveOrderSwing(int sel) {
        return m.getInfoActiveOrderSwing(sel);
    }

    public ListLibrary getListLibrary() {
        return listLibrary;
    }

    public void setListLibrary(ListLibrary listLibrary) {
        this.listLibrary = listLibrary;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String removeOneBook(int sel) {
        return m.removeOneBook(sel);
    }

    public String editBook(int sel) {
        return m.editBook(sel);
    }

    public String editListener(int sel) {
        return m.editListener(sel);
    }

    public ArrayList<String> startBookEditForm() {
        return v.startBookEditForm();
    }

    public ArrayList<String> startListenerEditForm() {
        return v.startListenerEditForm();
    }

    public boolean isSwing() {
        return swing;
    }

    public void setSwing(boolean swing) {
        this.swing = swing;
    }


}

