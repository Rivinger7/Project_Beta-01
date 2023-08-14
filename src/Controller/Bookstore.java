/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.*;
import Model.Book;
import java.io.FileWriter;
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
    public <E> boolean writeFile(String path, List<E> list) throws Exception {
        int fID = 1;
        try {
            FileWriter writer = new FileWriter(path);
            for (E bookObj : list) {
                writer.write(fID++ + "," + bookObj.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("File " + path + " has been written successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("An error occurred while writing to the file: " + path);
            ex.printStackTrace();
            return false;
        }
        // Add thêm sách vào file.txt thì cần kết hợp thêm kỹ thuật viết file (WirteFile)
    }

    @Override
    public boolean checkUser() {
        return user.checkUser();
    }

    @Override
    public List<Book> addBook() {
        // Nếu tên sách bị trùng thì phải throw Exception (Cần xử lý)
        return book.addBook();
    }

    @Override
    public List<Book> removeBook() {
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
