/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.*;
import Model.Book;
import Data_Objects.*;
import Exception.NumberOutOfRangeException;
import Input.Inputter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bookstore implements IBookstore {

    IBookDao book;
    IUserDao user;
    UserDao userdao = new UserDao() ;
    Scanner sc = new Scanner(System.in);

    final String fileBook = "src\\Input\\ListOfBooks.txt";
    final String fileUser = "src\\Input\\ListOfUsers.txt";
    final String fileBackUp = "src\\Output\\BackUpList.txt";
//    final String userName = user.getUserName();

    // Nên để fileBook và fileUser thay vì để ở hàm main (Đã đổi)
    // Nếu vậy thì phải build lại constructor
    // Nên giữ lại 2 luồng (fileBook và fileUser) để dễ quản lý
    // Nên dùng Exception bên bookstore()
    public Bookstore() throws Exception {
        book = new BookDao(fileBook);
        user = new UserDao(fileUser);
    }

    // Nên thiết kế thêm 1 method getBookList (Đã thiết kế)
    // Thay vì để method removeBook() là một danh sách (boolean hoặc void)
    // Nếu làm vậy thì phải chỉnh cả addBook() method
    // Gộp writeFile vào addBook() và removeBook() method, và return writeFile
    // Đã cập nhật tất cả tasks trên
    @Override
    public <E> boolean writeFileBook(String path, List<E> list) throws Exception {
        return book.writeFileBook(path, list);
    }

    @Override
    public <E> boolean writeFileUser(String path, List<E> list) throws Exception {
        return user.writeFileUser(path, list);
    }

    @Override
    public void loginSystem() throws Exception {
        boolean check = false;
        while (!check) {
            try {
                String selection = Inputter.inputNonBlankStr("\nLogin (1) or Register (2): ");
                switch (selection) {
                    case "1":
                        boolean checkLogin = user.login(fileUser);
                        break;
                    case "2":
                        boolean checkStatus = false;
                        while (!checkStatus) {
                            checkStatus = user.register(fileUser);
                            if (!checkStatus) {
                                String temp = Inputter.inputNonBlankStr("Do you want to register again?"
                                        + " (Yes / No): ");
                                if (temp.equalsIgnoreCase("No")) {
                                    checkStatus = true;
                                }
                            }
                        }
                        user.login(fileUser);
                        break;
                    default:
                        throw new NumberOutOfRangeException("Number is outside the range");
                }
                check = true;
            } catch (Exception ex) {
                if (ex instanceof NumberOutOfRangeException) {
                    System.out.println(ex);
                }
            }
        }
    }

    @Override
    public void addBook() throws Exception {
        // Nếu tên sách bị trùng thì phải throw Exception (Đã xử lý)
        // Nên dùng Exception
        while (true) {
            try {
                if (book.addBook(fileBook)) {
                    System.out.println("The book has been added successfully.");
                    return;
                } else {
                    throw new Exception("The book name already exists.");
                    // Nên hỏi user có muốn nhập lại không
                    // Throwing an Exception then handle the Exception
                }
            } catch (Exception ex) {
                System.out.println(ex);
                String selection = Inputter.inputNonBlankStr("Do you want to enter again?"
                        + " (Yes / No): ");
                if (selection.trim().equalsIgnoreCase("No")) {
                    return;
                }
            }
        }
    }

    @Override
    public void removeBook() throws Exception {
        // Cần xử lý ID tăng lên liên tục thay vì giảm dần khi xóa ở giữa (Đã xử lý)
        // Sau khi xử lý vấn đề trên thì cần phải sort nó 1 lần nữa theo thứ tự ID (Không cần thiết)
        // Đã xử lý các tasks trên
        while (true) {
            try {
                if (book.removeBook(fileBook)) {
                    System.out.println("The book has been deleted from the database");
                    return;
                } else {
                    // Nên hỏi user có muốn nhập lại không
                    // Throwing an Exception then handle the Exception
                    throw new Exception("Not found the book ID");
                }
            } catch (Exception ex) {
                System.out.println(ex);
                String selection = Inputter.inputNonBlankStr("Do you want to enter again?"
                        + " (Yes / No): ");
                if (selection.trim().equalsIgnoreCase("No")) {
                    return;
                }
            }
        }
    }

    @Override
    public List<Book> sortByPrice() {
        return book.sortByPrice();
    }

    public List<Book> sortByName() {
        return book.sortByName();
    }

    @Override
    public List<Book> sortByType() {
        return book.sortByType();
    }

    @Override
    public List<Book> sortByQuantity() {
        return book.sortByQuantity();
    }
    
        @Override
    public void writeBackUp(String userName) throws Exception{
            book.writeBackUp(fileBackUp, userName);
    }
    
    @Override
    public void addToCart(){
        try{
            try{
                book.addToCart();
                book.changeProduct();
            }catch(Exception e){
                System.out.println("Cannot add to cart, try later");
            }
            try {
                writeBackUp(user.getUserName());
            } catch (Exception ex) {
                Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
            }
            updateQuantity();
        }catch(Exception ex){
            Logger.getLogger(Bookstore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void updateQuantity() throws Exception{
            book.writeFileBook(fileBook, book.updateQuantity());
    }
}
