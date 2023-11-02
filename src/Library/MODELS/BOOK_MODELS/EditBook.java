package Library.MODELS.BOOK_MODELS;

import Library.CLASSES.Book;
import Library.MODELS.Models;
import Library.Presenter;

import java.util.ArrayList;

public class EditBook {
    public static String editBook(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,0);
        if (p.getLibrary().checkIdBook(sel)){
            Book book = p.getLibrary().getBookFromId(sel);
            message = "Отредактирована "+book.toString()+" :\n";
            boolean test  =false;
            p.printMessage("Редактируется книга с ID : "+book.getId()+
                    "\n  Название       : "+book.getName()+
                    "\n  Автор          : "+book.getAuthor()+
                    "\n  Издательство   : "+book.getManufacture()+
                    "\n  Кол-во страниц : "+book.getPages()+
                    "\nВведите новые параметры (ENTER - оставить прежнее значение): ");
            ArrayList<String> list = p.startBookEditForm();
            if (list.get(0)!=""&&!list.get(0).equals(book.getName())){
                test = true;
                message = message+"  изменено имя : "+book.getName()+" --> "+list.get(0)+"\n";
                p.getLibrary().getBookFromId(sel).setName(list.get(0));}
            if (list.get(1)!=""&&!list.get(1).equals(book.getAuthor())){
                test = true;
                message = message+"  изменен автор : "+book.getAuthor()+" --> "+list.get(1)+"\n";
                p.getLibrary().getBookFromId(sel).setAuthor(list.get(1));}
            if (list.get(2)!=""&&!list.get(2).equals(book.getManufacture())){
                test = true;
                message = message+"  изменено издательство : "+book.getManufacture()+" --> "+list.get(2)+"\n";
                p.getLibrary().getBookFromId(sel).setManufacture(list.get(2));}
            if (!list.get(3).equals("0")&&!list.get(3).equals(String.valueOf(book.getPages()))){
                test = true;
                message = message+"  изменено кол-во страниц : "+book.getPages()+" --> "+list.get(3)+"\n";
                p.getLibrary().getBookFromId(sel).setPages(Integer.valueOf(list.get(3)));}
            if (test){p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
            else {message = book.toString()+" не изменена.";}
        }
        return message;
    }
}
