package Library.MODELS;

import Library.CLASSES.*;
import Library.*;
import Library.MODELS.BOOK_MODELS.*;
import Library.MODELS.LIBRARY_MODELS.*;
import Library.MODELS.LISTENER_MODELS.AddListener;
import Library.MODELS.LISTENER_MODELS.EditListener;
import Library.MODELS.LISTENER_MODELS.GetInfoListener;

import java.io.File;
import java.util.ArrayList;


public class Models {
    Presenter p;
    public Models(Presenter presenter){this.p = presenter;}

    public static String wrongIdmessage(int sel, int spec){
        ArrayList<String>str = new ArrayList<>();
        str.add(
            "Книга с указанным ID( "+sel+" ) отсутствует в каталоге данной библиотеки.\n" +
                    "Введите корректный ID.\n");
        str.add(
            "Читатель с указанным ID( "+sel+" ) отсутствует в реестре данной библиотеки.\n" +
                    "Введите корректный ID.\n");
        str.add(
                "Выдача с указанным ID( "+sel+" ) отсутствует в списке активных выдач текущей библиотеки.\n" +
                        "Введите корректный ID.\n");
        str.add(
                "Библиотека с указанным ID( "+sel+" ) отсутствует в списке доступных библиотек.\n" +
                        "Введите корректный ID.\n");
            return str.get(spec);}

    public String getListLibrary() {
        String str = "";
        for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
            str = str + p.getListLibrary().getNotes().get(i).toString() + "\n";}
        return str;}

    public String removeLibrary(int sel) {
        String message = "";
        if (p.getListLibrary().checkId(sel)){
            if(p.confirm(p.getListLibrary().getLibraryFromId(sel).getName()+" будет удалена"))
                {for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
                    if (p.getListLibrary().getNotes().get(i).getId() == sel) {
                        File file = new File(p.getListLibrary().getNotes().get(i).getFileName());
                        if (file.delete()) {message =
                                p.getListLibrary().getNotes().get(i).getName()+"\" успешно удалена.";}
                        p.getListLibrary().getNotes().remove(p.getListLibrary().getNotes().get(i));
                        if(sel == p.getListLibrary().getMaxID()){updateMaxIdLibrarys();}
                        WriteRead.save(p.getListLibrary(), "./src/Library/DATA/listlibrary.out");}
                }
            }
            else {message = "Удаление отменено.";}
        }
        else {message = wrongIdmessage(sel,3);}
    return message;}

    public void addLibrary(String s) {
        p.getListLibrary().add(p.getListLibrary().getMaxID() + 1, s);
        p.getListLibrary().setMaxID(p.getListLibrary().getMaxID() + 1);
        Library library1 = new Library(p.getListLibrary().getMaxID(), s);
        WriteRead.save(p.getListLibrary(), "./src/Library/DATA/listlibrary.out");
        WriteRead.save(library1, library1.getFileName());
        p.selectLibrary(p.getListLibrary().getMaxID());}

    public String getCatalog() {
        return GetCatalog.getCatalog(p);}

    public String getReestr() {
        return GetReestr.getReestr(p);}

    public String addBook(ArrayList<String> list) {
        return AddBook.addBook(p,list);}

    public String removeBook(int sel) {
        return RemoveBook.removeBook(p,sel);}

    public String removeListener(int sel) {
        return RemoveBook.removeBook(p,sel);}

    public String addListener(ArrayList<String> list) {
        return AddListener.addListener(p,list);}

    public String giveBook(ArrayList<String> giveBook) {
        return GiveBook.giveBook(p,giveBook);}

    public String returnBook(String[] returnBook) {
        return ReturnBook.returnBook(p,returnBook);}

    public String getList() {
        String message = "\nСПИСОК АКТИВНЫХ ЗАКАЗОВ : \n";
        for (Order item:p.getLibrary().getActiveOrders()) {message = message+item.mytString(p.getLibrary());}
        return message;}

    public String getInfoBook(int sel) {
        return GetInfoBook.getInfoBook(p,sel);}

    public String getInfoListener(int sel){
        return GetInfoListener.getInfoListener(p,sel);}

    public String getInfoOrder(int sel){
       return GetInfoOrder.getInfoOrder(p,sel);}

    public void close(){
        if (p.getLibrary().getChangesLog().length()>0){
            if (p.confirm("Были внесены изменения :\n"+p.getLibrary().getChangesLog()+"\nСохранить")){
                p.getLibrary().setChangesLog("");
                WriteRead.save(p.getLibrary(), p.getLibrary().getFileName());}
            p.getLibrary().setChangesLog("");}}

    public void selectLibrary(int sel) {
        if (p.getListLibrary().checkId(sel)){
            p.setLibrary(p.getListLibrary().getLibraryFromId(sel));
            p.startLibraryMenu(p.getLibrary().getName());}
        else {p.printMessage(wrongIdmessage(sel,3));}
        p.pressButton();}

    public void updateMaxIdLibrarys(){
        int max = 0;
        for (NoteLibrary item:p.getListLibrary().getNotes()) {
            if (max<item.getId()) {max = item.getId();}}
        p.getListLibrary().setMaxID(max);}

    public String removeOneBook(int sel) {
        return RemoveOneBook.removeOneBook(p,sel);}

    public String editBook(int sel) {
        return EditBook.editBook(p,sel);}

    public String editListener(int sel){
        return EditListener.editListener(p,sel);}

    public String mergeLibrary(int sel, int sel1) {
        return MergeLibrary.mergeLibrary(p,sel,sel1);}

}