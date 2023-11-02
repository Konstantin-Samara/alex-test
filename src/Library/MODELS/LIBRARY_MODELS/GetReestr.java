package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Listener;
import Library.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GetReestr {
    public static String getReestr(Presenter p){
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
}
