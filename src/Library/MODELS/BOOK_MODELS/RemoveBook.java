package Library.MODELS.BOOK_MODELS;

import Library.CLASSES.Book;
import Library.MODELS.Models;
import Library.Presenter;

public class RemoveBook {
    public static String removeBook(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,0);
        if (p.getLibrary().checkIdBook(sel)){
            Book book = new Book();
            for (int i = 0; i < p.getLibrary().getBooks().size(); i++) {
                if (p.getLibrary().getBooks().get(i).getId() == sel) {
                    book = p.getLibrary().getBooks().get(i);}}
            if (book.getActiveOrdersId().size()==0){
                if (p.confirm("из каталога будет удалена "+book.toString()
                        + " в кол-ве : " + book.getQuantity() + " экз.")){
                    p.getLibrary().getBooks().remove(book);
                    message = "из каталога удалена "+book.toString() + " в кол-ве : "
                            + book.getQuantity() + " экз.\n";
                    p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);
                    if(sel==p.getLibrary().getBooksMaxId()){p.getLibrary().updateMaxIdBook();}}
                else {message = "Удаление отменено.";}}
            else {
                message = "Невзможно удалить из каталога книгу ( назв.: "+book.getName()+" авт.: "+book.getAuthor()+" )\n"+
                        "  пока есть экземпляры на руках у читателей :\n";
                for (int item:book.getActiveOrdersId()) {
                    message = message + "Выдача(ID : "+item+" ) "
                            +p.getLibrary().getListenerFromOrder(
                            p.getLibrary().getActiveOrderFromId(item)).toString()+"\n";}
                message = message+"Сначала необходимо все вернуть...\n";
            }
        }
        return message;
    }
}
