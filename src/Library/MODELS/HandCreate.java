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
        books.add(new Book(1,"О России","сам Ломоносов","Архангельск 1980",800,5));
        books.add(new Book(2,"О Химии","сын Ломоносова","Архангельск 1981",700,3));
        books.add(new Book(3,"О Физике","дочь Ломоносова","Архангельск 1982",600,2));
        books.add(new Book(4,"О Ломоносове","друг Ломоносова","Москва 1983",850,1));
        books.add(new Book(5,"О Стихи и оды","сам Ломоносов","Москва 1984",750,2));
        books.add(new Book(6,"О Архангельске","сосед Ломоносова","Москва 1985",650,3));

        books.add(new Book(1,"О Любви","сам Пушкин","СПБ 1992",770,2));
        books.add(new Book(2,"О Погоде","Александр Пушкин","СПБ 1993",670,1));
        books.add(new Book(3,"О Стихи и оды","сам Ломоносов","Москва 1984",750,4));
        books.add(new Book(4,"О России","сам Ломоносов","Архангельск 1980",800,5));

//        books.add(new Book(4,"О Болдино","сам Пушкин","Сочи 2018",1290,3));
        books.add(new Book(5,"Про осень","сам Пушкин","Сочи 2019",1090,2));
        books.add(new Book(6,"Про зиму","сам Пушкин","Сочи 2020",1190,1));
        return books;
    }
    public static ArrayList<Listener> createListListeners(){
        listeners = new ArrayList<>();
        listeners.add(new Listener(1,"Иван","Иванов",true,"Екатеринбург","+79272876543"));
        listeners.add(new Listener(2,"Петр","Петров",true,"Казань","+79272123456"));
        listeners.add(new Listener(3,"Николай","Николаев",true,"Новосибирск","+79272454545"));
        listeners.add(new Listener(4,"Дмитрий","Дмитриев",true,"Москва","+79121231231"));
        listeners.add(new Listener(5,"Сергей","Сергеев",true,"Питер","+79127654321"));
        listeners.add(new Listener(6,"Елена","Еленина",true,"Питер","+79127777777"));


        listeners.add(new Listener(1,"Татьяна","Ларина",false,"Питер","+70951231231"));
        listeners.add(new Listener(2,"Иван","Иванов",true,"Екатеринбург","+79272876543"));
        //        listeners.add(new Listener(2,"Ольга","Морозова",false,"Питер","+70957654321"));
        listeners.add(new Listener(3,"Наталья","Романова",false,"Питер","+70955656565"));
        listeners.add(new Listener(4,"Елена","Еленина",true,"Питер","+79127777777"));
        listeners.add(new Listener(5,"Анна","Нюрина",true,"Питер","+79126666666"));
        listeners.add(new Listener(6,"Екатерина","Катина",true,"Питер","+79120000000"));
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
                librarys[i].getListeners().add(listeners.get(i*6+j));
            }
        }
        for (int i = 0; i < 2; i++) {
            WriteRead.save(librarys[i],librarys[i].getFileName());}
        return listLibrary;
    }

}
