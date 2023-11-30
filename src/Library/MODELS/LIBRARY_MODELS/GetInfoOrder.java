package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Book;
import Library.CLASSES.Listener;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

public class GetInfoOrder {
    public static String getInfoOrder(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,2);
        boolean flag = false;
        Order order = new Order();
        if (p.getLibrary().checkIdActiveOrders(sel)){
            order = p.getLibrary().getActiveOrderFromId(sel);
            flag = true;}
        else if (p.getLibrary().checkIdClosedOrders(sel)) {
            order = p.getLibrary().getClosedOrdersFromId(sel);
            flag = true;}
        if (flag){
            message = order.mytString(p.getLibrary());}
        return message;
    }

    public static String[] getInfoActiveOrderSwing(Presenter p, int sel) {
        String[] arr = new String[6];
        Order order = p.getLibrary().getActiveOrderFromId(sel);
        Book book = p.getLibrary().getBookFromOrder(order);
        Listener listener = p.getLibrary().getListenerFromOrder(order);
        arr[0] = String.valueOf(order.getId());
        arr[1] = order.getOpenComment();
        arr[2] = String.valueOf(listener.getId());
        arr[3] = listener.getLastName()+" "+listener.getFirstName();
        arr[4] = String.valueOf(book.getId());
        arr[5] = book.getName()+" "+book.getAuthor();
        return arr;}
}
