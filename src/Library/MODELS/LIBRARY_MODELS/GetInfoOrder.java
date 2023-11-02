package Library.MODELS.LIBRARY_MODELS;

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
}
