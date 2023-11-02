package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Book;
import Library.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GetCatalog {
    public static String getCatalog(Presenter p){
        String str = "";
        ArrayList<Book> books = new ArrayList<>();
        for (Book item:p.getLibrary().getBooks()) {books.add(item);}
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {return o1.getName().compareTo(o2.getName());}});
        for (Book item:books) {str = str+"\n"+item.toString();}
        return str;}
}
