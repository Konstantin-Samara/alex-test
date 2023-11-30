package Library.MODELS;

import Library.CLASSES.*;
import Library.*;
import Library.MODELS.BOOK_MODELS.*;
import Library.MODELS.LIBRARY_MODELS.*;
import Library.MODELS.LISTENER_MODELS.AddListener;
import Library.MODELS.LISTENER_MODELS.EditListener;
import Library.MODELS.LISTENER_MODELS.GetInfoListener;
import Library.MODELS.LISTENER_MODELS.RemoveListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Models {
    Presenter p;

    public Models(Presenter presenter) {
        this.p = presenter;
    }

    public static String wrongIdmessage(int sel, int spec) {
        ArrayList<String> str = new ArrayList<>();
        str.add(
                "Книга с указанным ID( " + sel + " ) отсутствует в каталоге данной библиотеки.\n" +
                        "Введите корректный ID.\n");
        str.add(
                "Читатель с указанным ID( " + sel + " ) отсутствует в реестре данной библиотеки.\n" +
                        "Введите корректный ID.\n");
        str.add(
                "Выдача с указанным ID( " + sel + " ) отсутствует в списке активных выдач текущей библиотеки.\n" +
                        "Введите корректный ID.\n");
        str.add(
                "Библиотека с указанным ID( " + sel + " ) отсутствует в списке доступных библиотек.\n" +
                        "Введите корректный ID.\n");
        return str.get(spec);
    }

//    public String getListLibrary() {
//        String str = "";
//        for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
//            str = str + p.getListLibrary().getNotes().get(i).toString() + "\n";}
//        return str;}

    public ArrayList<String[]> getArrListLibrary() {
        ArrayList<String[]> arr1 = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
            String[] s = new String[2];
            s[0] = String.valueOf(p.getListLibrary().getNotes().get(i).getId());
            s[1] = p.getListLibrary().getNotes().get(i).getName();
            arr1.add(s);
        }
        arr1.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });
        return arr1;
    }

    public String removeLibrary(int sel, boolean needCheckConfirm) {
        String message = "";
        if (needCheckConfirm) {
            if (p.getListLibrary().checkId(sel)) {
                if (p.confirm(p.getListLibrary().getLibraryFromId(sel).getName() + " будет удалена")) {
                    needCheckConfirm = false;
                } else {
                    message = "Удаление отменено.";
                }
            } else {
                message = wrongIdmessage(sel, 3);
            }
        }

        if (!needCheckConfirm) {
            for (int i = 0; i < p.getListLibrary().getNotes().size(); i++) {
                if (p.getListLibrary().getNotes().get(i).getId() == sel) {
                    File file = new File(p.getListLibrary().getNotes().get(i).getFileName());
                    if (file.delete()) {
                        message =
                                p.getListLibrary().getNotes().get(i).getName() + "\" успешно удалена.";
                    }
                    p.getListLibrary().getNotes().remove(p.getListLibrary().getNotes().get(i));
                    if (sel == p.getListLibrary().getMaxID()) {
                        updateMaxIdLibrarys();
                    }
                    WriteRead.save(p.getListLibrary(), "./src/Library/DATA/listlibrary.out");
                }
            }
        }
        return message;
    }

    public void addLibrary(String s) {
        p.getListLibrary().add(p.getListLibrary().getMaxID() + 1, s);
        p.getListLibrary().setMaxID(p.getListLibrary().getMaxID() + 1);
        Library library1 = new Library(p.getListLibrary().getMaxID(), s);
        WriteRead.save(p.getListLibrary(), "./src/Library/DATA/listlibrary.out");
        WriteRead.save(library1, library1.getFileName());
        p.selectLibrary(p.getListLibrary().getMaxID());
    }

    public ArrayList<String[]> getCatalog() {
        return GetCatalog.getCatalog(p);
    }

    public ArrayList<String[]> getReestr() {
        return GetReestr.getReestr(p);
    }

    public String addBook(ArrayList<String> list) {
        return AddBook.addBook(p, list);
    }

    public String removeBook(int sel) {
        return RemoveBook.removeBook(p, sel);
    }

    public String removeListener(int sel) {
        return RemoveListener.removeListener(p, sel);
    }

    public String addListener(ArrayList<String> list) {
        return AddListener.addListener(p, list);
    }

    public String giveBook(ArrayList<String> giveBook) {
        return GiveBook.giveBook(p, giveBook);
    }

    public String returnBook(String[] returnBook) {
        return ReturnBook.returnBook(p, returnBook);
    }

    public ArrayList<String[]> getList() {
        ArrayList<String[]> arr = new ArrayList<>();
        for (Order item : p.getLibrary().getActiveOrders()) {
            String[] s = new String[5];
            s[0] = String.valueOf(item.getId());
            s[1] = p.getLibrary().getListenerFromOrder(item).getLastName();
            s[2] = p.getLibrary().getListenerFromOrder(item).getFirstName();
            s[3] = p.getLibrary().getBookFromOrder(item).getName();
            s[4] = p.getLibrary().getBookFromOrder(item).getAuthor();
            arr.add(s);
        }
        return arr;
    }

    public String getInfoBook(int sel) {
        return GetInfoBook.getInfoBook(p, sel);
    }

    public String getInfoListener(int sel) {
        return GetInfoListener.getInfoListener(p, sel);
    }

    public String getInfoOrder(int sel) {
        return GetInfoOrder.getInfoOrder(p, sel);
    }

    public void close() {
        if (p.getLibrary().getChangesLog().length() > 0) {
            if (p.confirm("Были внесены изменения :\n" + p.getLibrary().getChangesLog() + "\nСохранить")) {
                p.getLibrary().setChangesLog("");
                WriteRead.save(p.getLibrary(), p.getLibrary().getFileName());
            }
            p.getLibrary().setChangesLog("");
        }
    }

    public void selectLibrary(int sel) {
        if (p.getListLibrary().checkId(sel)) {
            p.setLibrary(p.getListLibrary().getLibraryFromId(sel));
            p.startLibraryMenu(p.getLibrary().getName());
        } else {
            p.printMessage(wrongIdmessage(sel, 3));
            p.getMainMenu();
        }
    }

    public void updateMaxIdLibrarys() {
        int max = 0;
        for (NoteLibrary item : p.getListLibrary().getNotes()) {
            if (max < item.getId()) {
                max = item.getId();
            }
        }
        p.getListLibrary().setMaxID(max);
    }

    public String removeOneBook(int sel) {
        return RemoveOneBook.removeOneBook(p, sel);
    }

    public String editBook(int sel) {
        return EditBook.editBook(p, sel);
    }

    public String editListener(int sel) {
        return EditListener.editListener(p, sel);
    }

    public String mergeLibrary(int sel, int sel1) {
        return MergeLibrary.mergeLibrary(p, sel, sel1);
    }

    public ArrayList<String[]>[] getInfoListenerSwing(int sel) {
        return GetInfoListener.getInfoListenerSwing(p, sel);
    }

    public ArrayList<String[]>[] getInfoBookSwing(int selBook) {
        return GetInfoBook.getInfoBookSwing(p, selBook);
    }

    public String[] getInfoActiveOrderSwing(int sel) {
        return GetInfoOrder.getInfoActiveOrderSwing(p, sel);
    }
}