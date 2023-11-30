package Library.MODELS.LISTENER_MODELS;

import Library.CLASSES.Book;
import Library.CLASSES.Listener;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

import java.util.ArrayList;

public class GetInfoListener {
    public static String getHistoryListener(Presenter p, int sel){
        String message = "\nРанее брал : \n";
        for (Order item : p.getLibrary().getClosedOrders()) {
            if (item.getListenerId() == sel) {
                Book book = p.getLibrary().getBookFromOrder(item);
                message = message + "Книга (" + book.getName() + ") Автор : " + book.getAuthor() + " Заказ(ID : "
                        + item.getId() + ")\nКомментарий при выдаче : " + item.getOpenComment()
                        + "\nКомментарий при возврате : " + item.getCloseComment() + "\n";}}
        return  message;}

    public static ArrayList<String[]>[] getInfoListenerSwing(Presenter p, int sel) {
        ArrayList<String[]>[] arr = new ArrayList[2];
        arr[0] = new ArrayList<String[]>();
        arr[1] = new ArrayList<String[]>();
        String[] s;
        Listener listener = p.getLibrary().getListenerFromId(sel);
        s = new String[7];
        s[0] = String.valueOf(listener.getId());
        s[1] = listener.getLastName();
        s[2] = listener.getFirstName();
        if (listener.isGender()) {s[3] = "Мужской";}
        else {s[3] = "Женский";}
        s[4] = listener.getHomeAdress();
        s[5] = listener.getPhone();
        s[6] = getHistoryListener(p, sel);
        arr[0].add(s);
        for (int item : listener.getActiveOrdersId()) {
            Order order = p.getLibrary().getActiveOrderFromId(item);
            Book book = p.getLibrary().getBookFromOrder(order);
            s = new String[4];
            s[0] = String.valueOf(item);
            s[1] = order.getOpenComment();
            s[2] = String.valueOf(book.getId());
            s[3] = book.getName() + " " + book.getAuthor();
            arr[1].add(s);}
        return arr;}

    public static String getInfoListener(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,1);
        if (p.getLibrary().checkIdListener(sel)){
            Listener listener = p.getLibrary().getListenerFromId(sel);
            message = "\nИНФОРМАЦИЯ О ЧИТАТЕЛЕ (ID : " + sel + ") :\n";
            message = message + listener.toString() + "\nДом адр.: " + listener.getHomeAdress()
                    + "\nВ настоящее время на руках : \n";
            for (int item : listener.getActiveOrdersId()) {
                Book book = p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(item));
                message = message + book.toString() + "\nКомментарий при выдаче :\n"
                        + p.getLibrary().getActiveOrderFromId(item).getOpenComment() + "\n";}
            message = message + getHistoryListener(p,sel);}
        return  message;}
}
