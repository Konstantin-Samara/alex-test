package Library.CLASSES;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int bookId;
    private int listenerId;
    private String openComment="";
    private String closeComment="";
    public Order(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getListenerId() {
        return listenerId;
    }

    public void setListenerId(int listenerId) {
        this.listenerId = listenerId;
    }

    public String getOpenComment() {
        return openComment;
    }

    public void setOpenComment(String openComment) {
        this.openComment = openComment;
    }

    public String getCloseComment() {
        return closeComment;
    }

    public void setCloseComment(String closeComment) {
        this.closeComment = closeComment;
    }
    @Override
    public String toString(){
        return "Выдача(ID : "+id+" ) Книга(ID : "+bookId+" ) Читатель(ID : "+listenerId+" )";}
    public String mytString(Library library){
        Book book = library.getBookFromOrder(this);
        Listener listener = library.getListenerFromOrder(this);
        String str = "\nИНФОРМАЦИЯ О ЗАКАЗЕ(ID : "+id+" ) : \n"+book.toString()+"\n"+listener.toString()+"\n";
        if (openComment!=""){str = str+"Комментарий при выдаче : "+openComment+"\n";}
        if (closeComment!=""){str = str+"Комментарий при возврате : "+closeComment+"\n";}
        return str;
    }

    public boolean equals(Order o) {
        return this.getListenerId()==o.getListenerId()&&this.getBookId()==o.getBookId();
    }
}
