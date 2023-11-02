package Library.MODELS.BOOK_MODELS;

import Library.CLASSES.Book;
import Library.CLASSES.Listener;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

public class GetInfoBook {
    public static String getInfoBook(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,0);
        if (p.getLibrary().checkIdBook(sel)){
            Book book = p.getLibrary().getBookFromId(sel);
            message = "\nИНФОРМАЦИЯ О КНИГЕ (ID : "+sel+") :\n";
            message = message+"\n"+book.toString()+"\nКол-во страниц : "+book.getPages()+
                    "Кол-во экземпляров в библиотеке : "+book.getQuantity()+" Кол-во в наличии : "+book.getExist()+
                    "\nВ настоящее время выдана : \n";
            for (int item:book.getActiveOrdersId()){
                Listener listener = p.getLibrary().getListenerFromOrder(p.getLibrary().getActiveOrderFromId(item));
                message = message+"\n"+listener.toString()
                        +" Заказ(ID : "+item+") :\nкомментарий при выдаче : "
                        +p.getLibrary().getActiveOrderFromId(item).getOpenComment()+"\n";}
            message = message + "\nРанее выдавалась : \n";
            for (Order item:p.getLibrary().getClosedOrders()){
                if (item.getBookId()==sel){
                    Listener listener = p.getLibrary().getListenerFromOrder(item);
                    message = message+"\n"+listener.toString()
                            +"\nЗаказ(ID : "+item.getId()+"), комментарий при выдаче : "
                            +item.getOpenComment()+"\nкомментарий при возврате : "
                            +item.getCloseComment()+"\n";
                }
            }
        }
        return message;}
}
