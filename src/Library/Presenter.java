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

    public void pressButton() {
        if (Inputs.my_input("Загрузить из файла - 1/сформировать шаблон - 0 : ",0,1)==1) {
            setListLibrary((ListLibrary) WriteRead.read("./src/Library/DATA/listlibrary.out"));}
        else {
            setListLibrary(HandCreate.createLibrarys());}
        v.getMainMenu(m.getListLibrary());}
    public void printMessage(String s)
        {v.printMessage(s);}
    public boolean confirm(String s)
        {return v.confirm(s);}
//    public String getInputStr(String s)
//        {return v.getInputStr(s);}
    public String giveBook(ArrayList<String> giveBook)
        {return m.giveBook(giveBook);}
    public String ReturnBook(String[] returnBook)
        {return m.returnBook(returnBook);}
    public String getCatalog()
        {return m.getCatalog();}
    public String getReestr()
        {return m.getReestr();}
    public void close()
        {m.close();}
    public String addBook(ArrayList<String> book)
        {return m.addBook(book);}
    public String addListener(ArrayList<String> listener)
        {return m.addListener(listener);}
    public String removeBook(int sel)
        {return m.removeBook(sel);}
    public String removeListener(int sel)
        {return m.removeListener(sel);}

    public void selectLibrary(int sel)
        {m.selectLibrary(sel);}
    public void startLibraryMenu(String s)
        {v.getLibraryMenu(s);}
    public String removeLibrary(int sel)
        {return m.removeLibrary(sel);}

    public void mergeLibrary() {System.out.println("mergeLibrary pressed");}

    public void addLibrary(String s)
        {m.addLibrary(s);}
    public void exit()
        {System.out.println("exit pressed");}
    public String getList()
        {return m.getList();}
    public String getInfoBook(int sel)
        {return m.getInfoBook(sel);}
    public String getInfoListener(int sel)
        {return m.getInfoListener(sel);}
    public String getInfoOrder(int sel)
        {return m.getInfoOrder(sel);}

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

    public String removeOneBook(int sel)
        {return m.removeOneBook(sel);}

    public String getBookInStrList(int sel)
        {return m.getBookInStrList(sel);}
    public String getListenerInStrList(int sel)
    {return m.getListenerInStrList(sel);}

    public ArrayList<String> startBookEditForm()
        {return v.startBookEditForm();}

    public ArrayList<String> startListenerEditForm()
        {return v.startListenerEditForm();}
}

