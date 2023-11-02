package Library.MODELS.LISTENER_MODELS;

import Library.CLASSES.Listener;
import Library.MODELS.Models;
import Library.Presenter;

import java.util.ArrayList;

public class EditListener {
    public static String editListener(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,1);
        if (p.getLibrary().checkIdListener(sel)){
            Listener listener = p.getLibrary().getListenerFromId(sel);
            String strOldGend = "Женский";
            String strNewGend = "Женский";
            boolean gender = false;
            boolean newGender = false;
            if (listener.isGender()){strOldGend = "Мужской";gender = true;}
            message = "Отредактирован "+listener.toString()+" :\n";
            boolean test  =false;
            p.printMessage("Редактируется читатель с ID : "+listener.getId()+
                    "\n  Фамилия        : "+listener.getLastName()+
                    "\n  Имя            : "+listener.getFirstName()+
                    "\n  Пол            : "+strOldGend+
                    "\n  Домашний адрес : "+listener.getHomeAdress()+
                    "\n  Телефон        : "+listener.getPhone()+
                    "\nВведите новые параметры (ENTER - оставить прежнее значение): ");
            ArrayList<String> list = p.startListenerEditForm();
            if (list.get(2).equals("1")){strNewGend = "Мужской";newGender = true;}
            if (list.get(0)!=""&&!list.get(0).equals(listener.getLastName())){
                test = true;
                message = message+"  изменена фамилия : "+listener.getLastName()+" --> "+list.get(0)+"\n";
                p.getLibrary().getListenerFromId(sel).setLastName(list.get(0));}
            if (list.get(1)!=""&&!list.get(1).equals(listener.getFirstName())){
                test = true;
                message = message+"  изменено имя : "+listener.getFirstName()+" --> "+list.get(1)+"\n";
                p.getLibrary().getListenerFromId(sel).setFirstName(list.get(1));}
            if (!list.get(2).equals("0")&&!strNewGend.equals(strOldGend)){
                test = true;
                message = message+"  изменен пол : "+strOldGend+" --> "+strNewGend+"\n";
                p.getLibrary().getListenerFromId(sel).setGender(newGender);}
            if (list.get(3)!=""&&!list.get(3).equals(listener.getHomeAdress())){
                test = true;
                message = message+"  изменен домашний адрес : "+listener.getHomeAdress()+" --> "+list.get(3)+"\n";
                p.getLibrary().getListenerFromId(sel).setHomeAdress(list.get(3));}
            if (list.get(4)!=""&&!list.get(4).equals(listener.getPhone())){
                test = true;
                message = message+"  изменен телефон : "+listener.getPhone()+" --> "+list.get(4)+"\n";
                p.getLibrary().getListenerFromId(sel).setPhone(list.get(4));}
            if (test){p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);}
            else {message = listener.toString()+" не изменен.";}
        }
        return message;
    }
}
