package Library.VIEW.VIEW_MODELS;

import java.util.Scanner;

public class Inputs {
    public static int my_input(boolean need,String str, int n1, int n2) {
        int inp = 0;
        boolean test = true;
        Scanner scan = new Scanner(System.in);

        while (test) {
            System.out.print(str);
            String s = scan.nextLine();
            if (s!=""){
                try { inp = Integer.valueOf(s);test = false;}
                catch (Exception e) {
                    System.out.println("Вы ввели не число, попробуйте еще раз.");
                    scan = new Scanner(System.in);
                }
                if (!test)   {
                    if (inp>=n1&&inp<=n2)
                        test = false;
                    else {
                        test = true;
                        System.out.println("Число от "+n1+" до "+n2+". попробуйте еще раз.");}}}
            else {if(!need) {test = false;}
                  else {System.out.println("Поле обязательно для заполнения...");}}
        }
        return inp;}

    public static String my_input_str(boolean need,String s){
        String str = "";
        if (!need){
            Scanner scan = new Scanner(System.in);
            System.out.print(s);
            str = scan.nextLine();}
        else {
            boolean test = true;
            while (test) {
                Scanner scan = new Scanner(System.in);
                System.out.print(s);
                str = scan.nextLine();
                if (!str.equals("")){test = false;}
                else {System.out.println("Поле обязательно для заполнения...");}
            }
        }

        return str;
    }
}
