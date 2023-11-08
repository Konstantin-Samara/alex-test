package Library.CLASSES;

import Library.MODELS.WriteRead;

import java.io.Serializable;
import java.util.ArrayList;

public class ListLibrary implements Serializable {
    private int maxID=0;
    private ArrayList<NoteLibrary> notes = new ArrayList<>();

    public int getMaxID() {
        return maxID;
    }

    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    public ArrayList<NoteLibrary> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<NoteLibrary> notes) {
        this.notes = notes;
    }

    public void add(int i, String n){
        NoteLibrary note = new NoteLibrary(i,n);
        notes.add(note);
    }
    public boolean checkId(int sel){
        boolean flag = false;
        for (NoteLibrary item:notes)
            {if (item.getId()==sel){flag = true;}}
        return flag;}
    public Library getLibraryFromId(int sel){
            Library library = (Library) WriteRead.read("./src/Library/DATA/library"+sel+".out");
        return library;}

}
