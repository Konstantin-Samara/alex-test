package Library.MODELS.BOOK_MODELS;

import Library.CLASSES.Book;
import Library.Presenter;

import java.util.ArrayList;

public class AddBook {

    public static String addBook(Presenter p, ArrayList<String> list){
        int id = Integer.valueOf(list.get(0));
        String message = "";
        String name = list.get(1);
        String author = list.get(2);
        String manufacture = list.get(3);
        int pages = Integer.valueOf(list.get(4));
        int quantity = Integer.valueOf(list.get(5));
        Book newBook = new Book(id, name, author, manufacture, pages, quantity);
        Book existBook = null;
        for (Book item:p.getLibrary().getBooks()) {
            if (newBook.equals(item)){existBook = item;}}
        if (existBook!=null){
            if (p.confirm("  Эта книга уже содержится в каталоге данной библиотеки с ID : "+
                    existBook.getId()+" в кол-ве : "+existBook.getQuantity()+" экз.\n"+
                    "Новые экземпляры ( "+newBook.getQuantity()+" экз.) будут добавлены к существующей :" +
                    "\n  "+existBook.toString()+"\n"+"Общее кол-во в каталоге данной библиотеки составит "
                    +(existBook.getQuantity()+newBook.getQuantity())+" экз.")){
                p.getLibrary().getBookFromId(existBook.getId()).setQuantity(
                        existBook.getQuantity()+newBook.getQuantity());
                p.getLibrary().getBookFromId(existBook.getId()).setExist(
                        existBook.getExist()+newBook.getQuantity());
                message = "Новые экземпляры ( "+newBook.getQuantity()+" экз.) добавлены к существующей :\n  "+
                        existBook.toString()+"\n";
                p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
            else {message = "Добавление отменено.";}}
        else {
            p.getLibrary().setBooksMaxId(p.getLibrary().getBooksMaxId() + 1);
            p.getLibrary().getBooks().add(newBook);
            message = "в каталог добавлена книга "+name+" "+author+" в кол-ве : "+quantity+" экз.\n";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
        return message;
    }
}
