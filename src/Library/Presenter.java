package Library;

import Library.CLASSES.Library;
import Library.CLASSES.ListLibrary;
import Library.MODELS.HandCreate;
import Library.MODELS.Models;

import Library.MODELS.WriteRead;
import Library.VIEW.VIEW_MODELS.Inputs;
import Library.VIEW.VIEW_MODELS.ViewModels;

import java.util.ArrayList;


public class Presenter {
View view = new View(this);
ListLibrary listLibrary;
Library library;

    public void pressButton() {
        if (Inputs.my_input("Загрузить из файла - 1/сформировать шаблон - 0 : ",0,1)==1) {
            listLibrary = (ListLibrary) WriteRead.read("./src/Library/DATA/listlibrary.out");}
        else {listLibrary = HandCreate.createLibrarys();}
        view.getMainMenu(Models.getListLibrary(listLibrary));
    }

    public String giveBook(ArrayList<String> giveBook) {
        return Models.giveBook(library,giveBook);}
    public String ReturnBook(String[] returnBook)
        {return Models.returnBook(library,returnBook);}
    public String getCatalog()
        {return Models.getCatalog(library);}
    public String getReestr()
        {return Models.getReestr(library);}
    public void close() {
        if (library.getChangesLog().length()>0) {
            if (ViewModels.confirmSaveChangesLibrary(library.getChangesLog())){
                library.setChangesLog("");
                WriteRead.save(library,library.getFileName());}
            library.setChangesLog("");}
    }
    public String addBook(ArrayList<String> book)
        {return Models.addBook(library,book);}

    public String addListener(ArrayList<String> listener)
        {return Models.addListener(library,listener);}

    public String removeBook(int sel)
        {return Models.removeBook(library,sel);}

    public String removeListener(int sel)
        {return Models.removeListener(library,sel);}

    public void selectLibrary(int sel) {
        if (listLibrary.checkId(sel)){
            library = listLibrary.getLibraryFromId(sel);
            view.getLibraryMenu(library.getName());}
        else {ViewModels.wrongId(sel);}
        pressButton();}

    public void removeLibrary(int sel) {
        if (listLibrary.checkId(sel))
            if(ViewModels.confirmRemoveLibrary(listLibrary.getLibraryFromId(sel).getName()))
                {Models.removeLibrary(listLibrary,sel);}
        else {ViewModels.wrongId(sel);}}

    public void mergeLibrary() {System.out.println("mergeLibrary pressed");}

    public void addLibrary(String s) {
        Models.addLibrary(listLibrary,s);
        selectLibrary(listLibrary.getMaxID()); }

    public void exit() {System.out.println("exit pressed");}

    public String getList()
        {return Models.getList(library);}
    public String getInfoBook(int sel) {
        return Models.getInfoBook(library,sel);
    }
    public String getInfoListener(int sel) {
        return Models.getInfoListener(library,sel);
    }
    public String getInfoOrder(int sel) {
        return Models.getInfoOrder(library,sel);
    }
}

