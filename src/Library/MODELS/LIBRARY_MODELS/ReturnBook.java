package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Book;
import Library.CLASSES.Listener;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

public class ReturnBook {
    public static String returnBook(Presenter p,String[] returnBook){
        int sel = Integer.valueOf(returnBook[0]);
        String message = "";
        if (p.getLibrary().checkIdActiveOrders(sel)){
            Order order = p.getLibrary().getActiveOrderFromId(sel);
            order.setCloseComment(returnBook[1]);
            Listener listener = p.getLibrary().getListenerFromOrder(p.getLibrary().getActiveOrderFromId(sel));
            Book book = p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(sel));
            p.getLibrary().getListenerFromOrder(p.getLibrary().getActiveOrderFromId(sel)).getActiveOrdersId().remove(listener.getIndFromIdOrder(sel));
            p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(sel)).getActiveOrdersId().remove(book.getIndFromIdOrder(sel));
            p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(sel)).setExist(book.getExist()+1);
            p.getLibrary().getActiveOrders().remove(p.getLibrary().getActiveOrderFromId(sel));
            p.getLibrary().getClosedOrders().add(order);
            message = "Возврат книги "+book.getName()+" "+book.getAuthor()+" читателем "
                    +listener.getFirstName()+" "+listener.getLastName()+" осуществлен.\n";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog()+message);}
        else {message = Models.wrongIdmessage(sel,2);}
        return message;}
}
