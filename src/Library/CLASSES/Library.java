package Library.CLASSES;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {
    private int id;
    private String name;
    private String fileName;
    private int booksMaxId;
    private int listenerMaxId;
    private int ordersMaxId;

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Listener> listeners = new ArrayList<>();
    private ArrayList<Order> activeOrders = new ArrayList<>();
    private ArrayList<Order> closedOrders = new ArrayList<>();
    private String changesLog;
    public Library(int i, String n){
        this.setBooksMaxId(0);
        this.setListenerMaxId(0);
        this.setOrdersMaxId(0);
        this.setChangesLog("");
        this.setId(i);
        this.setName(n);
        this.setFileName("./src/Library/DATA/library"+ this.getId() +".out");
    }
    public Library(){}
    public Listener getListenerFromOrder(Order order)
        {return getListenerFromId(order.getListenerId());}
    public Book getBookFromOrder(Order order)
        {return getBookFromId(order.getBookId());}
    public Book getBookFromId(int id){
        Book book = new Book();
        for (Book item: getBooks()) {if (item.getId() ==id){book = item;}}
        return book;}
    public Listener getListenerFromId(int id){
        Listener listener = new Listener();
        for (Listener item: getListeners()) {if (item.getId() ==id){listener = item;}}
        return listener;}
    public Order getActiveOrderFromId(int id){
        Order order = new Order();
        for (Order item: getActiveOrders()) {if (item.getId() ==id){order = item;}}
        return order;}

    public Order getClosedOrdersFromId(int id){
        Order order = new Order();
        for (Order item: getClosedOrders()) {if (item.getId() ==id){order = item;}}
        return order;}
    public boolean checkIdBook(int sel) {
        for (Book item:books) {if (item.getId()==sel){return true;}}
        return false;}

    public boolean checkIdListener(int sel) {
        for (Listener item:listeners) {if (item.getId()==sel){return true;}}
        return false;}

    public  boolean checkIdActiveOrders(int sel) {
        for (Order item:activeOrders) {if (item.getId()==sel){return true;}}
        return false;}
    public  boolean checkIdClosedOrders(int sel) {
        for (Order item:closedOrders) {if (item.getId()==sel){return true;}}
        return false;}
//    public void updateMinIdBook(){
//        int min = 0;
//        for (Book item:books)
//            {if (min>item.getId()) {min = item.getId();}}
//        bookMinId = min;}
    public void updateMaxIdBook(){
        int max = 0;
        for (Book item:books)
            {if (max<item.getId()) {max = item.getId();}}
        booksMaxId = max;
    }
//    public void updateMinIdListener(){
//        int min = 0;
//        for (Listener item:listeners)
//            {if (min>item.getId()) {min = item.getId();}}
//        listenerMinId = min;
//    }
    public void updateMaxIdListener(){
        int max = 0;
        for (Listener item:listeners)
            {if (max<item.getId()) {max = item.getId();}}
        listenerMaxId = max;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getBooksMaxId() {
        return booksMaxId;
    }

    public void setBooksMaxId(int booksMaxId) {
        this.booksMaxId = booksMaxId;
    }

    public int getListenerMaxId() {
        return listenerMaxId;
    }

    public void setListenerMaxId(int listenerMaxId) {
        this.listenerMaxId = listenerMaxId;
    }

    public int getOrdersMaxId() {
        return ordersMaxId;
    }

    public void setOrdersMaxId(int ordersMaxId) {
        this.ordersMaxId = ordersMaxId;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(ArrayList<Listener> listeners) {
        this.listeners = listeners;
    }

    public ArrayList<Order> getActiveOrders() {
        return activeOrders;
    }

    public void setActiveOrders(ArrayList<Order> orders) {
        this.activeOrders = orders;
    }

    public String getChangesLog() {
        return changesLog;
    }

    public void setChangesLog(String changesLog) {
        this.changesLog = changesLog;
    }
    public ArrayList<Order> getClosedOrders() {
        return closedOrders;
    }

    public void setClosedOrders(ArrayList<Order> closedOrders) {
        this.closedOrders = closedOrders;
    }

//    public int getBookMinId() {
//        return bookMinId;
//    }
//
//    public void setBookMinId(int bookMinId) {
//        this.bookMinId = bookMinId;
//    }
//
//    public int getListenerMinId() {
//        return listenerMinId;
//    }
//
//    public void setListenerMinId(int listenerMinId) {
//        this.listenerMinId = listenerMinId;
//    }

}



