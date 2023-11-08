package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Library;
import Library.CLASSES.Order;
import Library.MODELS.Models;
import Library.MODELS.WriteRead;
import Library.Presenter;

import java.util.ArrayList;

public class MergeLibrary {
    public static String mergeLibrary(Presenter p, int sel, int sel1){
        Library library = p.getListLibrary().getLibraryFromId(sel);
        Library library1 = p.getListLibrary().getLibraryFromId(sel1);
        ArrayList<int[]> listIdBooks = new ArrayList<>();
        ArrayList<int[]> listIdListeners = new ArrayList<>();
        ArrayList<int[]> listIdClosedOrders = new ArrayList<>();
        ArrayList<int[]> listIdActiveOrders = new ArrayList<>();
        ArrayList<Order> tempOrders = new ArrayList<>();
        Order tempOrder;
        ArrayList<int[]> listTroubleOrders = new ArrayList<>();
        String message = "";
        boolean check = true;
        if (!p.getListLibrary().checkId(sel))
        {message = message+ Models.wrongIdmessage(sel,3); check = false;}
        if (!p.getListLibrary().checkId(sel1))
        {message = message+Models.wrongIdmessage(sel1,3); check = false;}
        if (sel==sel1)
        {message = message+"Необходимо ввести Id двух разных библиотек.\n";check = false;}
        if (check) {
            message = "Книги, читатели, текущие заказы и история завершенных заказов из \n" +
                    "( "+library1.getName()+" ) переведены в\n( "+library.getName()+" ).\n";
            boolean exist;
            int count = library.getBooksMaxId();
            for (int i = 0; i < library1.getBooks().size(); i++) {
                int[] temp = new int[3];
                exist = false;
                temp[0] = library1.getBooks().get(i).getId();
                for (int j = 0; j < library.getBooks().size(); j++){
                    if (library1.getBooks().get(i).equals(library.getBooks().get(j))){
                        exist = true;
                        temp[2] = 1;
                        temp[1] = library.getBooks().get(j).getId();}}
                if (!exist){
                    temp[1] = ++count;
                    temp[2] = 0;}
                listIdBooks.add(temp);}

            count = library.getListenerMaxId();
            for (int i = 0; i < library1.getListeners().size(); i++) {
                int[] temp1 = new int[3];
                exist = false;
                temp1[0] = library1.getListeners().get(i).getId();
                for (int j = 0; j < library.getListeners().size(); j++) {
                    if(library1.getListeners().get(i).equals(library.getListeners().get(j))){
                        exist = true;
                        temp1[2] = 1;
                        temp1[1] = library.getListeners().get(j).getId();}}
                if (!exist){
                    temp1[2] = 0;
                    temp1[1] = ++count;}
                listIdListeners.add(temp1);}

            count = library.getOrdersMaxId();
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
                tempOrders.add(tempOrder);}

            for (int j = 0; j < listIdBooks.size(); j++) {
                System.out.println("BOOK "+listIdBooks.get(j)[0]+"-->"+listIdBooks.get(j)[1]);}
            for (int j = 0; j < listIdListeners.size(); j++) {
                System.out.println("LISTENER "+listIdListeners.get(j)[0]+"-->"+listIdListeners.get(j)[1]);}
            for (int j = 0; j < listIdClosedOrders.size(); j++) {
                System.out.println("CLOSED "+listIdClosedOrders.get(j)[0]+"-->"+listIdClosedOrders.get(j)[1]);}
            for (int j = 0; j < listIdActiveOrders.size(); j++) {
                System.out.println("ACTIVE "+listIdActiveOrders.get(j)[0]
                        +"-->"+listIdActiveOrders.get(j)[1]);}

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
                for (int[] item:listIdBooks){library1.getBookFromId(item[0]).setId(item[1]);}

                for (int[] item:listIdBooks) {
                    ArrayList<Integer> newId = new ArrayList<>();
                    for (int i = 0; i < library1.getBookFromId(item[1]).getActiveOrdersId().size(); i++) {
                        newId.add(getNewIdFromOld(listIdActiveOrders,
                                library1.getBookFromId(item[1]).getActiveOrdersId().get(i)));}
                    library1.getBookFromId(item[1]).setActiveOrdersId(newId);

                    if (item[2]==1){
                        for (int item1:library1.getBookFromId(item[1]).getActiveOrdersId()) {
                            System.out.println(library1.getBookFromId(item[1]).getActiveOrdersId().toString());
                            library.getBookFromId(item[1]).getActiveOrdersId().add(item1);}
                        library.getBookFromId(item[1]).setQuantity(
                                library.getBookFromId(item[1]).getQuantity()
                                        +library1.getBookFromId(item[1]).getQuantity());
                        library.getBookFromId(item[1]).setExist(
                                library.getBookFromId(item[1]).getExist()
                                    +library1.getBookFromId(item[1]).getExist());}
                    else {library.getBooks().add(library1.getBookFromId(item[1]));}}
                library.updateMaxIdBook();

                for (int[] item:listIdListeners){library1.getListenerFromId(item[0]).setId(item[1]);}

                for (int[] item:listIdListeners) {
                    ArrayList<Integer> newId = new ArrayList<>();
                    for (int i = 0; i < library1.getListenerFromId(item[1]).getActiveOrdersId().size(); i++) {
                        newId.add(getNewIdFromOld(listIdActiveOrders,
                                library1.getListenerFromId(item[1]).getActiveOrdersId().get(i)));}
                    library1.getListenerFromId(item[1]).setActiveOrdersId(newId);
                    if (item[2]==1){
                        for (int item1:library1.getListenerFromId(item[1]).getActiveOrdersId()) {
                            library.getListenerFromId(item[1]).getActiveOrdersId().add(item1);}}
                    else {library.getListeners().add(library1.getListenerFromId(item[1]));}}
                library.updateMaxIdListener();
                for (int[] item:listIdClosedOrders){library1.getClosedOrdersFromId(item[0]).setId(item[1]);}
                for (int[] item:listIdActiveOrders){library1.getActiveOrderFromId(item[0]).setId(item[1]);}

                for (int[] item:listIdClosedOrders) {
                    library1.getClosedOrdersFromId(item[1]).setListenerId(getNewIdFromOld(
                            listIdListeners,library1.getClosedOrdersFromId(item[1]).getListenerId()));
                    library1.getClosedOrdersFromId(item[1]).setBookId(getNewIdFromOld(
                            listIdBooks,library1.getClosedOrdersFromId(item[1]).getBookId()));
//                    library1.getClosedOrdersFromId(item[0]).setId(item[1]);
                    library.getClosedOrders().add(library1.getClosedOrdersFromId(item[1]));}
//
                for (int[] item:listIdActiveOrders) {
                    library1.getActiveOrderFromId(item[1]).setListenerId(getNewIdFromOld(
                            listIdListeners,library1.getActiveOrderFromId(item[1]).getListenerId()));
                    library1.getActiveOrderFromId(item[1]).setBookId(getNewIdFromOld(
                            listIdBooks,library1.getActiveOrderFromId(item[1]).getBookId()));
//                    library1.getActiveOrderFromId(item[0]).setId(item[1]);
                    library.getActiveOrders().add(library1.getActiveOrderFromId(item[1]));}
                library.updateMaxIdOrder();

                message = message+p.removeLibrary(library1.getId(),false);
//                WriteRead.save(library1,library1.getFileName());
                WriteRead.save(library,library.getFileName());
            }
        }
        return message;}

    public static int getNewIdFromOld(ArrayList<int[]> list,int old){
        for (int[] item:list) {if (item[0]==old){return item[1];}}
        return -5;}
}
