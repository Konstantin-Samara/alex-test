package Library.MODELS.LISTENER_MODELS;

import Library.CLASSES.Listener;
import Library.MODELS.Models;
import Library.Presenter;

public class RemoveListener {
    public static String removeListener(Presenter p, int sel){
        String message = Models.wrongIdmessage(sel,1);
        if (p.getLibrary().checkIdListener(sel)){
            Listener listener = new Listener();
            for (int i = 0; i < p.getLibrary().getListeners().size(); i++) {
                if (p.getLibrary().getListeners().get(i).getId() == sel) {
                    listener = p.getLibrary().getListeners().get(i);}}
            if (listener.getActiveOrdersId().size()==0){
                if (p.confirm("из реестра будет удален " + listener.toString())){
                    p.getLibrary().getListeners().remove(listener);
                    message = "из реестра удален " + listener.toString()+"\n";
                    p.getLibrary().setChangesLog(p.getLibrary().getChangesLog() + message);
                    if(sel==p.getLibrary().getListenerMaxId()){p.getLibrary().updateMaxIdListener();}}
                else {message = "Удаление отменено.";}}
            else{
                message = "Невзможно удалить из реестра читателя ( имя : "+listener.getFirstName()+" фамилия : "
                        +listener.getLastName()+" )\n"+
                        "  пока у него на руках у есть книги :\n";
                for (int item:listener.getActiveOrdersId()) {
                    message = message + "Выдача(ID : "+item+" ) "
                            +p.getLibrary().getBookFromOrder(
                            p.getLibrary().getActiveOrderFromId(item)).toString()+"\n";}
                message = message+"Сначала необходимо все вернуть...\n";
            }
        }
    return  message;}
}
