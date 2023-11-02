package Library.MODELS.BOOK_MODELS;

import Library.CLASSES.Book;
import Library.MODELS.Models;
import Library.Presenter;

public class RemoveOneBook {
    public static String removeOneBook(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,0);
        if (p.getLibrary().checkIdBook(sel)){
            Book book = new Book();
            for (int i = 0; i < p.getLibrary().getBooks().size(); i++) {
                if (p.getLibrary().getBooks().get(i).getId() == sel) {
                    book = p.getLibrary().getBooks().get(i);}}
            if (book.getExist()>0){
                if (p.confirm("Будет утилизирован 1 экземпляр "+book.toString())){
                    if (book.getQuantity()>1){
                        p.getLibrary().getBookFromId(sel).setExist(book.getExist()-1);
                        p.getLibrary().getBookFromId(sel).setQuantity(book.getQuantity()-1);
                        message = "Утилизирован 1 экземпляр "+book.toString()+ ".\n";
                        p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
                    else{
                        if (p.confirm("Это единственный экземпляр в библиотеке. \n" +
                                "  "+book.toString()+" будет удалена из каталога целиком")){
                            if(p.getLibrary().getBooks().remove(book)) {
                                message = "Удален единственный экземпляр и из каталога целиком удалена :\n"
                                        +book.toString();
                                p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);
                                if(sel==p.getLibrary().getBooksMaxId()){p.getLibrary().updateMaxIdBook();}}
                            else {message = "Удаление почему-то не прошло...";}}
                        else{message = "Удаление отменено.";}}}
                else {message = "Удаление отменено.";}}
            else {
                message = "В наличии нет экз.( "+book.getName()+" "+book.getAuthor()+" ),\n" +
                        "  доступных для удаления. Все экземпляры на руках у читателей :\n";
                for (int item:book.getActiveOrdersId()) {
                    message = message + "Выдача(ID : "+item+" ) "
                            +p.getLibrary().getListenerFromOrder(
                            p.getLibrary().getActiveOrderFromId(item)).toString()+"\n";}
                message = message+"Сначала необходимо что-нибудь вернуть...\n";}}
        return message;
    }
}
