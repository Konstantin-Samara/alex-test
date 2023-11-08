package Library.MODELS;

import Library.CLASSES.*;

import java.util.ArrayList;


public class HandCreate {
    public static ListLibrary listLibrary;
    public static ArrayList<Book> books;
    public static ArrayList<Listener> listeners;

    public static ListLibrary createListLibrary(){
        listLibrary = new ListLibrary();
        listLibrary.setMaxID(2);
        listLibrary.add(1,"Библиотека им. М.Ю.Ломоносова");
        listLibrary.add(2,"Библиотека им. А.С.Пушкина");
        WriteRead.save(listLibrary,"./src/Library/DATA/listlibrary.out");
        return listLibrary;
    }
    public static ArrayList<Book> createListBooks(){
        books = new ArrayList<>();
        books.add(new Book(1,"О Физике","Н.В. Ломоносов","Архангельск 1980",800,5));
        books.add(new Book(2,"О Химии","Н.В. Ломоносов","Архангельск 1981",700,3));
        books.add(new Book(3,"Три мушкетера","А. Дюма","Москва 1982",300,6));
        books.add(new Book(4,"Война миров","Г. Уэлс","Москва 1983",850,6));
        books.add(new Book(5,"Мастер и Маргарита","М.А. Булгаков","Москва 1984",750,6));
        books.add(new Book(6,"Фауст","И. Гетте","Москва 1985",650,6));
        books.add(new Book(1,"Три мушкетера","А. Дюма","Москва 1982",300,4));
        books.add(new Book(2,"Война миров","Г. Уэлс","Москва 1983",850,4));
        books.add(new Book(3,"Мастер и Маргарита","М.А. Булгаков","Москва 1984",750,4));
        books.add(new Book(4,"Фауст","И. Гетте","Москва 1985",650,4));
        books.add(new Book(5,"Про осень","А.С. Пушкин","СПБ 2019",1090,2));
        books.add(new Book(6,"Про зиму","А.С. Пушкин","СПБ 2020",1190,1));
        return books;}
    public static ArrayList<Listener> createListListeners(){
        listeners = new ArrayList<>();
        listeners.add(new Listener(1,"Жорес","Алферов",true,"Москва","+79272876543"));
        listeners.add(new Listener(2,"Сергей","Королев",true,"Москва","+79272123456"));
        listeners.add(new Listener(3,"Иван","Иванов",true,"Новосибирск","+79272454545"));
        listeners.add(new Listener(4,"Петр","Петров",true,"Казань","+79121231231"));
        listeners.add(new Listener(5,"Николай","Николаев",true,"Саратов","+79127654321"));
        listeners.add(new Listener(6,"Елена","Катина",false,"Самара","+79127777777"));
        listeners.add(new Listener(1,"Иван","Иванов",true,"Новосибирск","+79272454545"));
        listeners.add(new Listener(2,"Петр","Петров",true,"Казань","+79121231231"));
        listeners.add(new Listener(3,"Николай","Николаев",true,"Саратов","+79127654321"));
        listeners.add(new Listener(4,"Елена","Катина",false,"Самара","+79127777777"));
        listeners.add(new Listener(5,"Татьяна","Ларина",false,"Питер","+79126666666"));
        listeners.add(new Listener(6,"Людмила","Русланова",false,"Питер","+79120000000"));
        return listeners;}

    public static ListLibrary createLibrarys(){
        listLibrary = createListLibrary();
        books = createListBooks();
        listeners = createListListeners();

        Library[] librarys = new Library[2];
        for (int i = 0; i < 2; i++) {
            librarys[i] = new Library(listLibrary.getNotes().get(i).getId(),
            listLibrary.getNotes().get(i).getName());
            librarys[i].setBooksMaxId(6);
            librarys[i].setListenerMaxId(6);
            librarys[i].setOrdersMaxId(0);

            for (int j = 0; j < 6; j++) {
                librarys[i].getBooks().add(books.get(i*6+j));
                librarys[i].getListeners().add(listeners.get(i*6+j));}}
            for (int i = 0; i < 2; i++) {
            WriteRead.save(librarys[i],librarys[i].getFileName());}
        return listLibrary;
    }

}
