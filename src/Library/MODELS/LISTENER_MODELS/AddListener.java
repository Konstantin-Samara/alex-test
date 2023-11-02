package Library.MODELS.LISTENER_MODELS;

import Library.CLASSES.Listener;
import Library.Presenter;

import java.util.ArrayList;

public class AddListener {
    public static String addListener(Presenter p, ArrayList<String> list){
        String message = "";
        boolean gender = false;
        boolean flag = false;
        int id = Integer.valueOf(list.get(0));
        String firstName = list.get(1);
        String lastName = list.get(2);
        if (list.get(3).equals("1")) {gender = true;}
        String homeAdres = list.get(4);
        String phone = list.get(5);
        Listener listener = new Listener(id, firstName, lastName, gender, homeAdres, phone);
        for (Listener item:p.getLibrary().getListeners()){
            if (item.equals(listener)){
                message = "Такой читатель уже есть в реестре данной библиотеки с ID : "
                        +item.getId()+"\nДобавление прервано."; flag = true;}}
        if (!flag){
            p.getLibrary().setListenerMaxId(p.getLibrary().getListenerMaxId() + 1);
            p.getLibrary().getListeners().add(listener);
            message = "в реестр добавлен " + listener.toString() + "\n";
            p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
        return message;}
}
