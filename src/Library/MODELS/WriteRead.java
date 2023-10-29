package Library.MODELS;

import java.io.*;

public class WriteRead {
    public static void save(Object str, String s) {
        String fileName = s;
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(str);
            objectOutputStream.close();}
        catch(FileNotFoundException e) {System.out.println("Файл не найден.");}
        catch(IOException e) {System.out.println("Ошибка ввода-вывода данных.");}
    }
    public static Object read(String s) {
        Object o = null;
        String fileName = s;

        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream(fileName));
            o = objectInputStream.readObject();
            objectInputStream.close();
            return o;}
        catch(FileNotFoundException e) {System.out.println("Файл не найден.");}
        catch(IOException e) {System.out.println("Ошибка ввода-вывода данных.");}
        catch(ClassNotFoundException e) {System.out.println("Не найден класс - ObjectInputStream.");}

        return  o;
    }
}
