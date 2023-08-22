/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.util.*;
import Controller.Bookstore;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String options[] = {
            "Add a new book to the storage",
            "Remove a book in the storage",
            "Add to cart",
            "Create an invoice",
            "Sort the invoice list by the customer name",
            "Search the invoice list",
            "Exit"
        };

        int choice = 0;
        Bookstore bookstore = null;
        bookstore = new Bookstore();
        
        // Hàm main nên là hàm giao diện cho người dùng
        // Không nên thiết kế hàm xử lý bên hàm main
        
        bookstore.loginSystem();
        do {
            System.out.println("\nBook Management Program");
            choice = Menu.getChoice(options);
            switch (choice) {
                case 1:
                    bookstore.addBook();
                    break;
                case 2:
                    bookstore.removeBook();
                    break;
                case 3:
                    /* Có nên gộp hết cả 3 vào method addToCart bên bookstore ko? <DONE>
                       Thêm hiển thị danh sách console ra màn hình cho người dùng nhập ID <NOT YET>
                       Nên trừ quantity sau khi in hóa đơn <NOT YET>
                       Mỗi user có một cart riêng <Processing>
                    */
                    bookstore.addToCart();
                    break;
                case 4:
                    
                    
                    break;
                default:
                    System.out.println("Good bye!");
            }
        } while (choice > 0 && choice < options.length);
    }
}
