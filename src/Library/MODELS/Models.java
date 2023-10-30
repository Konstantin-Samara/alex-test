package Library.MODELS;

import Library.CLASSES.*;
import Library.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Models {
    Presenter p;
    public Models(Presenter presenter){this.p = presenter;}

    public String wrongIdmessage(int sel, int spec){
        ArrayList<String>str = new ArrayList<>();
        str.add(
            "Книга с указанным ID( "+sel+" ) отсутствует в каталоге данной библиотеки.\n" +
                    "Введите корректный ID.\n");
        str.add(
            "Читатель с указанным ID( "+sel+" ) отсутствует в реестре данной библиотеки.\n" +
                    "Введите корректный ID.\n");
        str.add(
                "Выдача с указанным ID( "+sel+" ) отсутствует в списке активных выдач текущей библиотеки.\n" +
                        "Введите корректный ID.\n");
        str.add(
                "Библиотека с указанным ID( "+sel+" ) отсутствует в списке доступных библиотек.\n" +
                        "Введите корректный ID.\n");
            return str.get(spec);}

    public String getListLibrary() {
        String str = "";
        for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
            str = str + p.getListLibrary().getNotes().get(i).toString() + "\n";}
        return str;}

    public String removeLibrary(int sel) {
        String message = "";
        if (p.getListLibrary().checkId(sel)){
            if(p.confirm(p.getListLibrary().getLibraryFromId(sel).getName()+" будет удалена"))
                {for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
                    if (p.getListLibrary().getNotes().get(i).getId() == sel) {
                        File file = new File(p.getListLibrary().getNotes().get(i).getFileName());
                        if (file.delete()) {message = "Библиотека \""
                                + p.getListLibrary().getNotes().get(i).getName() + "\" успешно удалена.";}
                        p.getListLibrary().getNotes().remove(p.getListLibrary().getNotes().get(i));
                        WriteRead.save(p.getListLibrary(), "./src/Library/DATA/listlibrary.out");}
                }
            }
            else {message = "Удаление прервано.";}
        }
        else {message = wrongIdmessage(sel,3);}
    return message;
    }

    public void addLibrary(String s) {
        p.getListLibrary().add(p.getListLibrary().getMaxID() + 1, s);
        p.getListLibrary().setMaxID(p.getListLibrary().getMaxID() + 1);
        Library library1 = new Library(p.getListLibrary().getMaxID(), s);
        WriteRead.save(p.getListLibrary(), "./src/Library/DATA/listlibrary.out");
        WriteRead.save(library1, library1.getFileName());
        p.selectLibrary(p.getListLibrary().getMaxID());}


    public String getCatalog() {
        String str = "";
        ArrayList<Book> books = new ArrayList<>();
        for (Book item:p.getLibrary().getBooks()) {books.add(item);}
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {return o1.getName().compareTo(o2.getName());}});
        for (Book item:books) {str = str+"\n"+item.toString();}
        return str;}

    public String getReestr() {
        String str = "";
        ArrayList<Listener> listeners = new ArrayList<>();
        for (Listener item:p.getLibrary().getListeners()) {listeners.add(item);}
        Collections.sort(listeners, new Comparator<Listener>() {
            @Override
            public int compare(Listener o1, Listener o2) {
                if (!o1.getLastName().equals(o2.getLastName())){
                    return o1.getLastName().compareTo(o2.getLastName());}
                return o1.getFirstName().compareTo(o2.getFirstName());}});
        for (Listener item : listeners) {str = str+"\n"+item.toString();}
        return str;}

    public String addBook(ArrayList<String> list) {
        int id = Integer.valueOf(list.get(0));
        String message = "";
        String name = list.get(1);
        String author = list.get(2);
        String manufacture = list.get(3);
        int pages = Integer.valueOf(list.get(4));
        int quantity = Integer.valueOf(list.get(5));
        p.getLibrary().setBooksMaxId(p.getLibrary().getBooksMaxId() + 1);
        p.getLibrary().getBooks().add(new Book(id, name, author, manufacture, pages, quantity));
        message = "в каталог добавлена книга "+name+" "+author+" в кол-ве : "+quantity+" экз.\n";
        p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);
        return message;}

    public String removeBook(int sel) {
        String message = wrongIdmessage(sel,0);
        if (p.getLibrary().checkIdBook(sel)){
                Book book = new Book();
            for (int i = 0; i < p.getLibrary().getBooks().size(); i++) {
                if (p.getLibrary().getBooks().get(i).getId() == sel) {
                    book = p.getLibrary().getBooks().get(i);
                    p.getLibrary().getBooks().remove(book);}}
            message = "из каталога удалена "+book.toString() + " в кол-ве : " + book.getQuantity() + " экз.\n";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
        return message;}

    public String removeListener(int sel) {
        String message = wrongIdmessage(sel,1);
        if (p.getLibrary().checkIdListener(sel)){
        Listener listener = new Listener();
        for (int i = 0; i < p.getLibrary().getListeners().size(); i++) {
            if (p.getLibrary().getListeners().get(i).getId() == sel) {
                listener = p.getLibrary().getListeners().get(i);
                p.getLibrary().getListeners().remove(listener);}}
        message = "из реестра удален " + listener.toString() + "\n";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
        return  message;}

    public String addListener(ArrayList<String> list) {
        boolean gender = false;
        int id = Integer.valueOf(list.get(0));
        String firstName = list.get(1);
        String lastName = list.get(2);
        if (list.get(3).equals("1")) {gender = true;}
        String homeAdres = list.get(4);
        String phone = list.get(5);
        p.getLibrary().setListenerMaxId(p.getLibrary().getListenerMaxId() + 1);
        Listener listener = new Listener(id, firstName, lastName, gender, homeAdres, phone);
        p.getLibrary().getListeners().add(listener);
        String message = "в реестр добавлен " + listener.toString() + "\n";
        p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);
        return message;}

    public String giveBook(ArrayList<String> giveBook) {
        String message = "Проблемы при создании заказа : \n";
        boolean flag = true;
        Order order = new Order();
        order.setId(Integer.valueOf(giveBook.get(0)));
        order.setBookId(Integer.valueOf(giveBook.get(1)));
        if (!p.getLibrary().checkIdBook(order.getBookId())) {
            flag = false;
            message = message + wrongIdmessage(order.getBookId(),0);}
        order.setListenerId(Integer.valueOf(giveBook.get(2)));
        if (!p.getLibrary().checkIdListener(order.getListenerId())) {
            flag = false;
            message = message + wrongIdmessage(order.getListenerId(),1);}
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
            message = "\nКнига ("+p.getLibrary().getBookFromId(order.getBookId()).getName()
                    +") выдана читателю ("+p.getLibrary().getListenerFromId(order.getListenerId()).getFirstName() +
                    " "+p.getLibrary().getListenerFromId(order.getListenerId()).getLastName()+").";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog()+message);}
        else {message = message+"\nЗаказ не оформлен. Книга не выдана.";}
        return message; }

    public String returnBook(String[] returnBook) {
        int sel = Integer.valueOf(returnBook[0]);
        String message = "";
        if (p.getLibrary().checkIdActiveOrders(sel)){
            Order order = p.getLibrary().getActiveOrderFromId(sel);
            order.setCloseComment(returnBook[1]);
            Listener listener = p.getLibrary().getListenerFromOrder(p.getLibrary().getActiveOrderFromId(sel));
            Book book = p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(sel));
            p.getLibrary().getListenerFromOrder(p.getLibrary().getActiveOrderFromId(sel)).getActiveOrdersId().remove(listener.getIndFromIdOrder(sel));
            p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(sel)).getActiveOrdersId().remove(book.getIndFromIdOrder(sel));
            p.getLibrary().getBookFromOrder(p.getLibrary().getActiveOrderFromId(sel)).setExist(book.getExist()+1);
            p.getLibrary().getActiveOrders().remove(p.getLibrary().getActiveOrderFromId(sel));
            p.getLibrary().getClosedOrders().add(order);
            message = "\nВзврат книги "+book.getName()+" "+book.getAuthor()+" читателем "
                    +listener.getFirstName()+" "+listener.getLastName()+" успешно осуществлен.";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog()+message);}
        else {message = wrongIdmessage(sel,2);}
        return message;}

    public String getList() {
        String message = "\nСПИСОК АКТИВНЫХ ЗАКАЗОВ : \n";
        for (Order item:p.getLibrary().getActiveOrders()) {message = message+item.mytString(p.getLibrary());}
        return message;}

    public String getInfoBook(int sel) {
        String message = wrongIdmessage(sel,0);
        if (p.getLibrary().checkIdBook(sel)){
            Book book = p.getLibrary().getBookFromId(sel);
            message = "ИНФОРМАЦИЯ О КНИГЕ (ID : "+sel+") :\n";
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
    public String getInfoListener(int sel){
        String message = wrongIdmessage(sel,1);
        if (p.getLibrary().checkIdListener(sel)){
            Listener listener = p.getLibrary().getListenerFromId(sel);
            message = "ИНФОРМАЦИЯ О ЧИТАТЕЛЕ (ID : "+sel+") :\n";
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
        return  message;}
    public String getInfoOrder(int sel){
        String message = wrongIdmessage(sel,2);
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
    public void close(){
        if (p.getLibrary().getChangesLog().length()>0){
            if (p.confirm("Были внесены изменения :\n"+p.getLibrary().getChangesLog()+"\nСохранить")){
                p.getLibrary().setChangesLog("");
                WriteRead.save(p.getLibrary(), p.getLibrary().getFileName());}
            p.getLibrary().setChangesLog("");}}

    public void selectLibrary(int sel) {
        if (p.getListLibrary().checkId(sel)){
            p.setLibrary(p.getListLibrary().getLibraryFromId(sel));
            p.startLibraryMenu(p.getLibrary().getName());}
        else {p.printMessage(wrongIdmessage(sel,3));}
        p.pressButton();}
}