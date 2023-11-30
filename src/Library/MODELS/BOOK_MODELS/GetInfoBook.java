package Library.MODELS.BOOK_MODELS;

import Library.CLASSES.Book;
import Library.CLASSES.Listener;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

import java.util.ArrayList;

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
                message = message+getHistoryBook(p,sel);}
        return message;}

    public static ArrayList<String[]>[] getInfoBookSwing(Presenter p, int selBook) {
        ArrayList<String[]>[] arr = new ArrayList[2];
        arr[0] = new ArrayList<>();
        arr[1] = new ArrayList<>();
        String[] s;
        Book book = p.getLibrary().getBookFromId(selBook);
        s=new String[7];
        s[0] = String.valueOf(book.getId());
        s[1] = book.getName();
        s[2] = book.getAuthor();
        s[3] = book.getManufacture();
        s[4] = String.valueOf(book.getPages());
        s[5] = String.valueOf(book.getQuantity());
        s[6] = String.valueOf(book.getExist());
        arr[0].add(s);
        for (int item:book.getActiveOrdersId()) {
            Order order = p.getLibrary().getActiveOrderFromId(item);
            Listener listener = p.getLibrary().getListenerFromOrder(order);
            s=new String[4];
            s[0] = String.valueOf(item);
            s[1] = order.getOpenComment();
            s[2] = String.valueOf(listener.getId());
            s[3] = listener.getLastName()+" "+listener.getFirstName();
            arr[1].add(s);}
    return arr;}

    public static String getHistoryBook(Presenter p, int sel){
        String message = "\nРанее выдавалась : \n";
        for (Order item:p.getLibrary().getClosedOrders()){
            if (item.getBookId()==sel){
                Listener listener = p.getLibrary().getListenerFromOrder(item);
                message = message+"\n"+listener.toString()
                        +"\nЗаказ(ID : "+item.getId()+"), комментарий при выдаче : "
                        +item.getOpenComment()+"\nкомментарий при возврате : "
                        +item.getCloseComment()+"\n";}}
        return message;}
}
