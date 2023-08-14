/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Input;

import java.util.Scanner;

public class Inputter {

    public static Scanner sc = new Scanner(System.in);

    public static int inputInt(String msg) {
        if (msg != null) {
            System.out.print(msg);
        }
        return Integer.parseInt(sc.nextLine());
    }

    public static double inputDouble(String msg) {
        if (msg != null) {
            System.out.print(msg);
        }
        return Double.parseDouble(sc.nextLine());
    }

    public static String inputStr() {
        return inputStr(null);
    }

    public static String inputStr(String msg) {
        if (msg != null) {
            System.out.print(msg);
        }

        return sc.nextLine().trim();
    }

    public static String inputNonBlankStr(String msg) {
        String str = null;
        do {
            str = inputStr(msg);
        } while (str == null);

        return str;
    }
}
