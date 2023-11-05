package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Library;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.Presenter;

import java.util.ArrayList;

public class MergeLibrary {
    public static String mergeLibrary(Presenter p, int sel, int sel1){
        String message = "";
        boolean check = true;
        if (!p.getListLibrary().checkId(sel))
        {message = message+ Models.wrongIdmessage(sel,3); check = false;}
        if (!p.getListLibrary().checkId(sel1))
        {message = message+Models.wrongIdmessage(sel1,3); check = false;}
        if (sel==sel1)
        {message = message+"Необходимо ввести Id двух разных библиотек.";check = false;}
        if (check) {
            message = "Готовы к слиянию.";
            Library library = p.getListLibrary().getLibraryFromId(sel);
            Library library1 = p.getListLibrary().getLibraryFromId(sel1);
            ArrayList<int[]> listIdBooks = new ArrayList<>();
            ArrayList<int[]> listIdListeners = new ArrayList<>();
            ArrayList<int[]> listIdClosedOrders = new ArrayList<>();
            ArrayList<int[]> listIdActiveOrders = new ArrayList<>();
            ArrayList<Order> tempOrders = new ArrayList<>();
            Order tempOrder;
            ArrayList<int[]> listTroubleOrders = new ArrayList<>();
            boolean exist;

            int count = library.getBooksMaxId();
            for (int i = 0; i < library1.getBooks().size(); i++) {
                int[] temp = new int[2];
                exist = false;
                temp[0] = library1.getBooks().get(i).getId();
                for (int j = 0; j < library.getBooks().size(); j++){
                    if (library1.getBooks().get(i).equals(library.getBooks().get(j))){
                        exist = true;
                        temp[1] = library.getBooks().get(j).getId();}}
                if (!exist)
                    {temp[1] = ++count;}
                listIdBooks.add(temp);}

            count = library.getListenerMaxId();
            for (int i = 0; i < library1.getListeners().size(); i++) {
                int[] temp1 = new int[2];
                exist = false;
                temp1[0] = library1.getListeners().get(i).getId();
                for (int j = 0; j < library.getListeners().size(); j++) {
                    if(library1.getListeners().get(i).equals(library.getListeners().get(j))){
                        exist = true;
                        temp1[1] = library.getListeners().get(j).getId();}}
                if (!exist)
                    {temp1[1] = ++count;}
                listIdListeners.add(temp1);}

            count = library1.getOrdersMaxId();
            for (int i = 0; i < library1.getClosedOrders().size(); i++) {
                int[] temp2 = new int[2];
                temp2[0] = library1.getClosedOrders().get(i).getId();
                temp2[1] = ++count;
                listIdClosedOrders.add(temp2);}
            for (int i = 0; i < library1.getActiveOrders().size(); i++) {
                int[] temp2 = new int[2];
                temp2[0] = library1.getActiveOrders().get(i).getId();
                temp2[1] = ++count;
                listIdActiveOrders.add(temp2);

                tempOrder = new Order();
                tempOrder.setId(temp2[0]);
                tempOrder.setBookId(getNewIdFromOld(listIdBooks,library1.getActiveOrders().get(i).getBookId()));
                tempOrder.setListenerId(getNewIdFromOld(listIdListeners,library1.getActiveOrders().get(i).getListenerId()));
                tempOrders.add(tempOrder);
            }

            /*проверка*/
            System.out.println("ИД книг в присоединяемой бибблиотеке : ");
            for (int j = 0; j < listIdBooks.size(); j++) {
                System.out.println(listIdBooks.get(j)[0]+"-->"+listIdBooks.get(j)[1]);}
            System.out.println("ИД читателей в присоединяемой бибблиотеке : ");
            for (int j = 0; j < listIdListeners.size(); j++) {
                System.out.println(listIdListeners.get(j)[0]+"-->"+listIdListeners.get(j)[1]);}
            /*проверка*/

            exist = false;
            for (Order ordLyb1:tempOrders) {
                for (Order ordLyb:library.getActiveOrders()) {
                    if (ordLyb.equals(ordLyb1)){
                        int[] troubleOrder = new int[2];
                        troubleOrder[0] = ordLyb.getId();
                        troubleOrder[1] = ordLyb1.getId();
                        listTroubleOrders.add(troubleOrder);
                        exist = true;}}}
            if (exist){
                message = "Проблемы :\n";
                count = 0;
                for (int[] item:listTroubleOrders) {
                    message = message+"--------------------"+(++count)+"-----------------------\n";
                    message = message
                            +"("+library.getListenerFromOrder(library.getActiveOrderFromId(item[0])).toString()
                            +") держит на руках\n"
                            +"("+library.getBookFromOrder(library.getActiveOrderFromId(item[0])).toString()
                            +") полученную в :\n"
                            +library.getName()+" по заказу ( ID : "+item[0]+" )\n"
                            +library1.getName()+" по заказу ( ID : "+item[1]+" )\n"
                            +"Чтобы объединить эти библиотеки, данный читатель должен вернуть хотя-бы\n"
                            +"один экзкмпляр в любую из них...\n";}
                message = message+"\nОБЪЕДИНЕНИЕ БИБЛИОТЕК НЕ ПРОИЗОШЛО!\n";}
            else {
                /*do merge*/
            }
        }
        return message;}

    public static int getNewIdFromOld(ArrayList<int[]> list,int old){
        for (int[] item:list) {if (item[0]==old){return item[1];}}
        return -5;}
}
