package Library.MODELS.LISTENER_MODELS;

import Library.CLASSES.Book;
import Library.CLASSES.Listener;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

public class GetInfoListener {
    public static String getInfoListener(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,1);
        if (p.getLibrary().checkIdListener(sel)){
            Listener listener = p.getLibrary().getListenerFromId(sel);
            message = "\nИНФОРМАЦИЯ О ЧИТАТЕЛЕ (ID : "+sel+") :\n";
            message = message+listener.toString()+"\nДом адр.: "+listener.getHomeAdress()
                    +"\nВ настоящее время на руках : \n";
            for (int item:listener.getActiveOrdersId()){
                Book book = p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(item));
                message = message+book.toString()+"\nКомментарий при выдаче :\n"
                        +p.getLibrary().getActiveOrderFromId(item).getOpenComment()+"\n";}
            message = message + "\nРанее брал : \n";
            for (Order item:p.getLibrary().getClosedOrders()){
                if (item.getListenerId()==sel){
                    Book book = p.getLibrary().getBookFromOrder(item);
                    message = message+"Книга ("+book.getName()+") Автор : "+book.getAuthor()+" Заказ(ID : "
                            +item.getId()+")\nКомментарий при выдаче : "+item.getOpenComment()
                            +"\nКомментарий при возврате : "+item.getCloseComment()+"\n";}
            }
        }
        return  message;
    }
}
