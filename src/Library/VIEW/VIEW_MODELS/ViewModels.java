package Library.VIEW.VIEW_MODELS;

import Library.VIEW.SWING_VIEW.SwingMessage;
import Library.View;

import java.util.ArrayList;

public class ViewModels {
    static View view;
    public ViewModels(View v) {
        this.view = v;
    }

    public static String addLibrary(int maxID)
        {return Inputs.my_input_str(true,"ID : "+(maxID+1)+". Название библиотеки : ");}

    public static void printMessage(String str)
        {System.out.println(str);}

    public static ArrayList<String> addBook(int maxBookId){
        ArrayList<String> book = new ArrayList<>();
        System.out.println("В каталог будет добавлена книга с ID : "+(maxBookId+1));
        book.add(String.valueOf((maxBookId+1)));
        book.add(Inputs.my_input_str(true,"Введите название книги : "));
        book.add(Inputs.my_input_str(true,"Введите автора : "));
        book.add(Inputs.my_input_str(true,"Введите издательство : "));
        book.add(String.valueOf(Inputs.my_input(true,"Введите кол-во страниц : ",1,5000)));
        book.add(String.valueOf(Inputs.my_input(true,"Введите кол-во экземпляров : ",1,100)));
        return book;}

    public static ArrayList<String> addListener(int maxListenerId){
        ArrayList<String> listener = new ArrayList<>();
        System.out.println("В реестр будет добавлен читатель с ID : "+(maxListenerId+1));
        listener.add(String.valueOf((maxListenerId+1)));
        listener.add(Inputs.my_input_str(true,"Введите имя читателя : "));
        listener.add(Inputs.my_input_str(true,"Введите фамилию читателя : "));
        listener.add(String.valueOf(Inputs.my_input(true,"Введите пол читателя (0-жен./1-муж.) : ",0,1)));
        listener.add(Inputs.my_input_str(true,"Введите адрес читателя : "));
        listener.add(Inputs.my_input_str(true,"Введите телефон читателя : "));
        return  listener;}

    public static ArrayList<String> giveBook(int maxActiveOrdersId,int minB_ID,int maxB_ID,int minL_ID,int maxL_ID){
        ArrayList<String> giveBook = new ArrayList<>();
        System.out.println("Оформляем заказ с ID : "+(maxActiveOrdersId+1));
        giveBook.add(String.valueOf((maxActiveOrdersId+1)));
        giveBook.add(String.valueOf(Inputs.my_input(true,"Введите ID книги для выдачи : ",minB_ID,maxB_ID)));
        giveBook.add(String.valueOf(Inputs.my_input(true,"Введите ID читателя для выдачи : ",minL_ID,maxL_ID)));
        giveBook.add(Inputs.my_input_str(false,"Комментарий при выдаче : "));
        return giveBook;}

    public static String[] returnBook(int minActiveOrdersId,int maxActiveOrdersId){
        String[] returnBook = new String[2];
        returnBook[0] = String.valueOf(Inputs.my_input(true,"Введите ID заказа (выдачи) для осуществлния возврата : ",
                minActiveOrdersId,maxActiveOrdersId));
        returnBook[1] = Inputs.my_input_str(false,"Комметарий при возврате : ");
        return returnBook;}
}
