/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.*;
import Model.Book;
import Data_Objects.*;

public class Bookstore implements IBookstore {

    IBookDao book;
    IUserDao user;
    Scanner sc = new Scanner(System.in);

    public Bookstore(String path1, String path2) throws Exception {
        book = new BookDao(path1);
        user = new UserDao(path2);
    }

    @Override
    public <E> boolean writeFileBook(String path, List<E> list) throws Exception {
        return book.writeFileBook(path, list);
    }
    
    @Override
    public <E> boolean writeFileUser(String path, List<E> list) throws Exception {
        return user.writeFileUser(path, list);
    }

    @Override
    public boolean login(String path) throws Exception {
        return user.login(path);
    }
    
    @Override
    public boolean register(String path) throws Exception {
        return user.register(path);
    }

    @Override
    public List<Book> addBook() {
        // Nếu tên sách bị trùng thì phải throw Exception (Đã xử lý)
        return book.addBook();
    }

    @Override
    public List<Book> removeBook() {
        // Cần xử lý ID tăng lên liên tục thay vì giảm dần khi xóa ở giữa (Cần xử lý)
        return book.removeBook();
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
}
