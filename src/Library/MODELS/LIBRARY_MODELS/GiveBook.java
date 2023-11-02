package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

import java.util.ArrayList;

public class GiveBook {
    public static String giveBook(Presenter p, ArrayList<String> giveBook){
        String message = "Проблемы при создании заказа : \n";
        boolean flag = true;
        Order order = new Order();
        order.setId(Integer.valueOf(giveBook.get(0)));
        order.setBookId(Integer.valueOf(giveBook.get(1)));
        if (!p.getLibrary().checkIdBook(order.getBookId())) {
            flag = false;
            message = message + Models.wrongIdmessage(order.getBookId(),0);}
        order.setListenerId(Integer.valueOf(giveBook.get(2)));
        if (!p.getLibrary().checkIdListener(order.getListenerId())) {
            flag = false;
            message = message + Models.wrongIdmessage(order.getListenerId(),1);}
        order.setOpenComment(giveBook.get(3));
        if (p.getLibrary().getBookFromId(order.getBookId()).getExist() == 0) {
            flag = false;
            message = message + "Книга " + p.getLibrary().getBookFromId(order.getBookId()).getName() +
                    " закончилась.\nПридется подождать возвратов других читателей.\n";}
        for (int i = 0; i < p.getLibrary().getListenerFromOrder(order).getActiveOrdersId().size(); i++) {
            int tempIdOrder = p.getLibrary().getListenerFromOrder(order).getActiveOrdersId().get(i);
            int tempIdBook = p.getLibrary().getActiveOrderFromId(tempIdOrder).getBookId();
            if (tempIdBook==order.getBookId()){
                flag = false;
                message = message + "\nКнига " + p.getLibrary().getBookFromOrder(order).getName() +
                        " уже на руках у данного читателя (" +
                        p.getLibrary().getListenerFromOrder(order).getFirstName() + " " +
                        p.getLibrary().getListenerFromOrder(order).getLastName() + ").";}}
        if (flag) {
            p.getLibrary().getActiveOrders().add(order);
            p.getLibrary().setOrdersMaxId(order.getId());
            p.getLibrary().getBookFromId(order.getBookId()).getActiveOrdersId().add(order.getId());
            p.getLibrary().getBookFromId(order.getBookId()).setExist(p.getLibrary().getBookFromId(order.getBookId()).getExist()-1);
            p.getLibrary().getListenerFromId(order.getListenerId()).getActiveOrdersId().add(order.getId());
            message = "Книга ("+p.getLibrary().getBookFromId(order.getBookId()).getName()
                    +") выдана читателю ("+p.getLibrary().getListenerFromId(order.getListenerId()).getFirstName() +
                    " "+p.getLibrary().getListenerFromId(order.getListenerId()).getLastName()+").\n";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog()+message);}
        else {message = message+"\nЗаказ не оформлен. Книга не выдана.";}
        return message;
    }
}
