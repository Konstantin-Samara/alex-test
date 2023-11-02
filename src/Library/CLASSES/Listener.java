package Library.CLASSES;

import java.io.Serializable;
import java.util.ArrayList;

public class Listener implements Serializable, Comparable<Listener> {
    private int id;
    private String firstName;
    private String lastName;
    private boolean gender;
    private String homeAdress;
    private String phone;
    private ArrayList<Integer> activeOrdersId = new ArrayList<>();
    public Listener(){}
    public Listener(int id, String firstName, String lastName, boolean gender, String homeAdress, String phone){
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setGender(gender);
        this.setHomeAdress(homeAdress);
        this.setPhone(phone);

    }
    @Override
    public String toString(){
        String str = "жен.";
        if (gender) {str = "муж.";}
        return "читатель(ID : "+getId() +" ) фамилия : "+ getLastName() +" имя : "+ getFirstName() +" пол : "+
                str +" тел : "+ getPhone();}
    public boolean equals(Listener l1){
        return getFirstName().equals(l1.getFirstName())&& getLastName().equals(l1.getLastName())
                && gender == l1.gender && getHomeAdress().equals(l1.getHomeAdress())&& getPhone().equals(l1.getPhone());
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Integer> getActiveOrdersId() {
        return activeOrdersId;
    }

    public void setActiveOrdersId(ArrayList<Integer> activeOrdersId) {
        this.activeOrdersId = activeOrdersId;
    }

    @Override
    public int compareTo(Listener o) {
        return (this.getLastName()+this.firstName).compareTo(o.lastName+o.getFirstName());
    }
}
