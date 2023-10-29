package Library.MODELS;

import Library.CLASSES.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Models {
    public static String getListLibrary(ListLibrary listLibrary) {
        String str = "";
        for (int i = 0; i < listLibrary.getNotes().size(); i++) {
            str = str + listLibrary.getNotes().get(i).toString() + "\n";}
        return str;}

    public static void removeLibrary(ListLibrary listLibrary, int sel) {
        for (int i = 0; i < listLibrary.getNotes().size(); i++) {
            if (listLibrary.getNotes().get(i).getId() == sel) {
                File file = new File(listLibrary.getNotes().get(i).getFileName());
                if (file.delete()) {System.out.println("Библиотека \""
                     + listLibrary.getNotes().get(i).getName() + "\" успешно удалена.");}
                listLibrary.getNotes().remove(listLibrary.getNotes().get(i));
                WriteRead.save(listLibrary, "./src/Library/DATA/listlibrary.out");}}
    }

    public static void addLibrary(ListLibrary listLibrary, String s) {
        listLibrary.add(listLibrary.getMaxID() + 1, s);
        listLibrary.setMaxID(listLibrary.getMaxID() + 1);
        Library library1 = new Library(listLibrary.getMaxID(), s);
        WriteRead.save(listLibrary, "./src/Library/DATA/listlibrary.out");
        WriteRead.save(library1, library1.getFileName());}


    public static String getCatalog(Library library) {
        String str = "";
        ArrayList<Book> books = new ArrayList<>();
        for (Book item:library.getBooks()) {books.add(item);}
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {return o1.getName().compareTo(o2.getName());}});
        for (Book item:books) {str = str+item.toString();}
        return str;}

    public static String getReestr(Library library) {
        String str = "";
        ArrayList<Listener> listeners = new ArrayList<>();
        for (Listener item:library.getListeners()) {listeners.add(item);}
        Collections.sort(listeners, new Comparator<Listener>() {
            @Override
            public int compare(Listener o1, Listener o2) {
                if (!o1.getLastName().equals(o2.getLastName())){
                    return o1.getLastName().compareTo(o2.getLastName());}
                return o1.getFirstName().compareTo(o2.getFirstName());}});
        for (Listener item : listeners) {str = str+item.toString();}
        return str;}

    public static String addBook(Library library, ArrayList<String> list) {
        int id = Integer.valueOf(list.get(0));
        String message = "";
        String name = list.get(1);
        String author = list.get(2);
        String manufacture = list.get(3);
        int pages = Integer.valueOf(list.get(4));
        int quantity = Integer.valueOf(list.get(5));
        library.setBooksMaxId(library.getBooksMaxId() + 1);
        library.getBooks().add(new Book(id, name, author, manufacture, pages, quantity));
        message = "\nв каталог добавлена книга "+name+" "+author+" в кол-ве : "+quantity+" экз.\n";
        library.setChangesLog(library.getChangesLog() + message);
        return message;}

    public static String removeBook(Library library, int sel) {
        String message = "Книга с указанным ID("+sel+") отсутствует в каталоге данной библиотеки.\n" +
                "Удаление прервано.\n";
        if (library.checkIdBook(sel)){
                Book book = new Book();
            for (int i = 0; i < library.getBooks().size(); i++) {
                if (library.getBooks().get(i).getId() == sel) {
                    book = library.getBooks().get(i);
                    library.getBooks().remove(book);}}
            message = "из каталога удалена "+book.toString() + " в кол-ве : " + book.getQuantity() + " экз.\n";
            library.setChangesLog(library.getChangesLog() + message);}
        return message;}

    public static String removeListener(Library library, int sel) {
        String message = "Читатель с указанным ID("+sel+") отсутствует в реестре данной библиотеки.\n" +
                "Удаление прервано.\n";
        if (library.checkIdListener(sel)){
        Listener listener = new Listener();
        for (int i = 0; i < library.getListeners().size(); i++) {
            if (library.getListeners().get(i).getId() == sel) {
                listener = library.getListeners().get(i);
                library.getListeners().remove(listener);}}
        message = "\nиз реестра удален " + listener.toString() + "\n";
        library.setChangesLog(library.getChangesLog() + message);}
        return  message;}

    public static String addListener(Library library, ArrayList<String> list) {
        boolean gender = false;
        int id = Integer.valueOf(list.get(0));
        String firstName = list.get(1);
        String lastName = list.get(2);
        if (list.get(3).equals("1")) {gender = true;}
        String homeAdres = list.get(4);
        String phone = list.get(5);
        library.setListenerMaxId(library.getListenerMaxId() + 1);
        Listener listener = new Listener(id, firstName, lastName, gender, homeAdres, phone);
        library.getListeners().add(listener);
        String message = "в реестр добавлен " + listener.toString() + "\n";
        library.setChangesLog(library.getChangesLog() + message);
        return message;}

    public static String giveBook(Library library, ArrayList<String> giveBook) {
        String message = "\nПроблемы при создании заказа : ";
        boolean flag = true;
        Order order = new Order();
        order.setId(Integer.valueOf(giveBook.get(0)));
        order.setBookId(Integer.valueOf(giveBook.get(1)));
        if (!library.checkIdBook(order.getBookId())) {
            flag = false;
            message = message + "\nКнига с указанным ID (" + order.getBookId() +
                    ") отсутсвует в каталоге данной библиотеки.";}
        order.setListenerId(Integer.valueOf(giveBook.get(2)));
        if (!library.checkIdListener(order.getListenerId())) {
            flag = false;
            message = message + "\nЧитатель с указанным ID (" + order.getListenerId() +
                    ") отсутсвует в реестре данной библиотеки.";}
        order.setOpenComment(giveBook.get(3));
        if (library.getBookFromId(order.getBookId()).getExist() == 0) {
            flag = false;
            message = message + "\nКнига " + library.getBookFromId(order.getBookId()).getName() +
                    " закончилась. Придется подождать возвратов других читателей.";}
        for (int i = 0; i < library.getListenerFromOrder(order).getActiveOrdersId().size(); i++) {
            int tempIdOrder = library.getListenerFromOrder(order).getActiveOrdersId().get(i);
            int tempIdBook = library.getActiveOrderFromId(tempIdOrder).getBookId();
            if (tempIdBook==order.getBookId()){
                flag = false;
                message = message + "\nКнига " + library.getBookFromOrder(order).getName() +
                        " уже на руках у данного читателя (" +
                        library.getListenerFromOrder(order).getFirstName() + " " +
                        library.getListenerFromOrder(order).getLastName() + ").";}}
        if (flag) {
            library.getActiveOrders().add(order);
            library.setOrdersMaxId(order.getId());
            library.getBookFromId(order.getBookId()).getActiveOrdersId().add(order.getId());
            library.getBookFromId(order.getBookId()).setExist(library.getBookFromId(order.getBookId()).getExist()-1);
            library.getListenerFromId(order.getListenerId()).getActiveOrdersId().add(order.getId());
            message = "\nКнига ("+library.getBookFromId(order.getBookId()).getName()
                    +") выдана читателю ("+library.getListenerFromId(order.getListenerId()).getFirstName() +
                    " "+library.getListenerFromId(order.getListenerId()).getLastName()+").";
            library.setChangesLog(library.getChangesLog()+message);}
        else {message = message+"\nЗаказ не оформлен. Книга не выдана.";}
        return message; }

    public static String returnBook(Library library, String[] returnBook) {
        int sel = Integer.valueOf(returnBook[0]);
        String message = "";
        if (library.checkIdActiveOrders(sel)){
            Order order = library.getActiveOrderFromId(sel);
            order.setCloseComment(returnBook[1]);
            Listener listener = library.getListenerFromOrder(library.getActiveOrderFromId(sel));
            Book book = library.getBookFromOrder(library.getActiveOrderFromId(sel));
            library.getListenerFromOrder(library.getActiveOrderFromId(sel)).getActiveOrdersId().remove(listener.getIndFromIdOrder(sel));
            library.getBookFromOrder(library.getActiveOrderFromId(sel)).getActiveOrdersId().remove(book.getIndFromIdOrder(sel));
            library.getBookFromOrder(library.getActiveOrderFromId(sel)).setExist(book.getExist()+1);
            library.getActiveOrders().remove(library.getActiveOrderFromId(sel));
            library.getClosedOrders().add(order);
            message = "\nВзврат книги "+book.getName()+" "+book.getAuthor()+" читателем "
                    +listener.getFirstName()+" "+listener.getLastName()+" успешно осуществлен.";
            library.setChangesLog(library.getChangesLog()+message);}
        else {message = "Выдача с указанным ID : ("+sel+") отсутствует в списке активных выдач текущей библиотеки" +
                "\nВозврат не осуществлен";}
        return message;}

    public static String getList(Library library) {
        String message = "\nСПИСОК АКТИВНЫХ ЗАКАЗОВ : ";

        for (Order item:library.getActiveOrders()) {message = message+item.mytString(library);}
        return message;}

    public static String getInfoBook(Library library, int sel) {
        String message = "\nКнига с указанным ID (" + sel +") отсутсвует в каталоге данной библиотеки.";
        if (library.checkIdBook(sel)){
            Book book = library.getBookFromId(sel);
            message = "ИНФОРМАЦИЯ О КНИГЕ (ID : "+sel+") :\n";
            message = message+"\n"+book.toString()+"\nКол-во страниц : "+book.getPages()+
                    "Кол-во экземпляров в библиотеке : "+book.getQuantity()+" Кол-во в наличии : "+book.getExist()+
                    "\nВ настоящее время выдана : \n";
            for (int item:book.getActiveOrdersId()){
                Listener listener = library.getListenerFromOrder(library.getActiveOrderFromId(item));
                message = message+"\n"+listener.toString()
                        +" Заказ(ID : "+item+") :\nкомментарий при выдаче : "
                        +library.getActiveOrderFromId(item).getOpenComment()+"\n";}
            message = message + "\nРанее выдавалась : \n";
            for (Order item:library.getClosedOrders()){
                if (item.getBookId()==sel){
                    Listener listener = library.getListenerFromOrder(item);
                    message = message+"\n"+listener.toString()
                            +"\nЗаказ(ID : "+item.getId()+"), комментарий при выдаче : "
                            +item.getOpenComment()+"\nкомментарий при возврате : "
                            +item.getCloseComment()+"\n";
                }
            }
        }
        return message;}
    public static String getInfoListener(Library library, int sel){
        String message = "\nЧитатель с указанным ID (" + sel +") отсутсвует в реестре данной библиотеки.";
        if (library.checkIdListener(sel)){
            Listener listener = library.getListenerFromId(sel);
            message = "ИНФОРМАЦИЯ О ЧИТАТЕЛЕ (ID : "+sel+") :\n";
            message = message+listener.toString()+"\nДом адр.: "+listener.getHomeAdress()
                    +"\nВ настоящее время на руках : \n";
            for (int item:listener.getActiveOrdersId()){
                Book book = library.getBookFromOrder(library.getActiveOrderFromId(item));
                message = message+book.toString()+"\nКомментарий при выдаче :\n"
                        +library.getActiveOrderFromId(item).getOpenComment()+"\n";}
            message = message + "\nРанее брал : \n";
            for (Order item:library.getClosedOrders()){
                if (item.getListenerId()==sel){
                    Book book = library.getBookFromOrder(item);
                    message = message+"Книга ("+book.getName()+") Автор : "+book.getAuthor()+" Заказ(ID : "
                    +item.getId()+")\nКомментарий при выдаче : "+item.getOpenComment()
                    +"\nКомментарий при возврате : "+item.getCloseComment()+"\n";}
            }
        }
        return  message;}
    public static String getInfoOrder(Library library,int sel){
        String message = "\nВыдача с указанным ID ("+ sel+") отсутсвует в списке выдачи данной библиотеки.";
        boolean flag = false;
        Order order = new Order();
        if (library.checkIdActiveOrders(sel)){
            order = library.getActiveOrderFromId(sel);
            flag = true;}
        else if (library.checkIdClosedOrders(sel)) {
            order = library.getClosedOrdersFromId(sel);
            flag = true;}
        if (flag){
            message = order.mytString(library);}
        return message;
    }
}