package Library.CLASSES;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private String manufacture;
    private int pages;
    private int quantity;
    private int exist;
    private ArrayList<Integer> activeOrdersId = new ArrayList<>();
    public Book(){}
    public Book(int id, String name, String author, String manufacture, int pages, int quantity){

        this.setId(id);
        this.setName(name);
        this.setAuthor(author);
        this.setManufacture(manufacture);
        this.setPages(pages);
        this.setQuantity(quantity);
        this.setExist(quantity);}

    @Override
    public String toString(){
        return "книга(ID : "+getId() +" ). назв.\""+ getName() +"\""+" авт."+ getAuthor()
                +" изд."+ getManufacture()+" "+activeOrdersId.toString();}

    public boolean equals(Book b1) {
        return getName().equals(b1.getName())&& getAuthor().equals(b1.getAuthor())
                && getManufacture().equals(b1.getManufacture())&& getPages() == b1.getPages();
    }
    public int getIndFromIdOrder(int id){
        int ind=-1;
        for (int i = 0; i < activeOrdersId.size(); i++) {if (activeOrdersId.get(i)==id){ind = i;}}
        return ind;}

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public ArrayList<Integer> getActiveOrdersId() {
        return activeOrdersId;
    }

    public void setActiveOrdersId(ArrayList<Integer> activeOrders) {
        this.activeOrdersId = activeOrders;
    }
}
