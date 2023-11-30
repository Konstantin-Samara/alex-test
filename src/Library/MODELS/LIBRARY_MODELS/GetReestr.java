package Library.MODELS.LIBRARY_MODELS;

import Library.CLASSES.Listener;
import Library.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GetReestr {
    public static ArrayList<String[]> getReestr(Presenter p){
        String str = "";
        ArrayList<String[]> arr = new ArrayList<>();
        ArrayList<Listener> listeners = new ArrayList<>();
        for (Listener item:p.getLibrary().getListeners()) {listeners.add(item);}
        Collections.sort(listeners, new Comparator<Listener>() {
            @Override
            public int compare(Listener o1, Listener o2) {
                if (!o1.getLastName().equals(o2.getLastName())){
                    return o1.getLastName().compareTo(o2.getLastName());}
                return o1.getFirstName().compareTo(o2.getFirstName());}});
        for (Listener item : listeners) {
            String[] s = new String[3];
            s[0] = String.valueOf(item.getId());
            s[1] = item.getLastName();
            s[2] = item.getFirstName();
            arr.add(s);}
        return arr;}
}
