package Library.CLASSES;

import java.io.Serializable;

public class NoteLibrary implements Serializable {
    private int id;
    private String name;
    private String fileName;
    public NoteLibrary(int id, String name){
        this.id = id;
        this.name = name;
        this.fileName = "./src/Library/DATA/library"+id+".out";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public String toString(){
        return "ID : "+this.id+" name : "+this.name/*+" fileName : "+this.fileName*/;
    }
}
