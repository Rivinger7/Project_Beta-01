/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.*;
import Controller.Bookstore;
import Input.Inputter;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String options[] = {
            "Add a new book to the storage",
            "Remove a book in the storage",
            "Create an invoice",
            "Sort the invoice list by the customer name",
            "Search the invoice list",
            "Exit"
        };

        final String fileBook = "src\\Input\\ListOfBooks.txt";
        final String fileUser = "src\\Input\\ListOfUsers.txt";
        final String fileAddingBook = "src\\Output\\ListOfBooks2.txt";
        final String fileAddingUser = "src\\Output\\ListOfUsers2.txt";
        int choice = 0;
        Bookstore bookstore = null;
        bookstore = new Bookstore(fileBook, fileUser);
        
        // Login và Register function nên để ngắn gọn thay vì thiết kế trực tiếp ở đây
        // Nên thiết kế bên bookstore class

        String selection = Inputter.inputNonBlankStr("\nLogin (1) or Register (2): ");
        if (selection.equals("1")) {
            boolean checkLogin = bookstore.login(fileUser);
        } else if (selection.equals("2")) {
            boolean checkRegister = bookstore.register(fileUser);
            if (checkRegister) {
                bookstore.login(fileUser);
            }
        }

        do {
            System.out.println("\nBook Management Program");
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    bookstore.writeFileBook(fileBook, bookstore.addBook());
                    break;
                case 2:
                    bookstore.writeFileBook(fileBook, bookstore.removeBook());
                    break;
                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length);
    }
}
