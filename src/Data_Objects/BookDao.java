/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Model.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class BookDao implements IBookDao {

    private List<Book> bookList = null;
    Scanner sc = new Scanner(System.in);
    
    public BookDao(String path1) throws Exception {
        bookList = loadBooks(path1);
    }

    private List<Book> loadBooks(String path1) throws Exception {
        try {
            File bookInfo = new File(path1);
            String fullPath = bookInfo.getAbsolutePath();
            FileInputStream bookInfoByte = new FileInputStream(fullPath);
            BufferedReader myInputBooksInfo = new BufferedReader(new InputStreamReader(bookInfoByte));
            String thisLine;
            while ((thisLine = myInputBooksInfo.readLine()) != null) {
                Book book = null;
                if (!thisLine.trim().isEmpty()) {
                    String split[] = thisLine.split(",");
//                    String id = split[1].trim();
                    String name = split[2].trim();
                    String type = split[3].trim();
                    double price = Double.parseDouble(split[4].trim());
                    int quantity = Integer.parseInt(split[5].trim());

                    book = new Book(price, name, type, quantity);
                }
                if (bookList == null) {
                    bookList = new ArrayList<>();
                }
                bookList.add(book);
            }
            myInputBooksInfo.close();
        } catch (Exception ex) {
            throw ex;
        }
//        for (Book obj1 : bookList) {
//            System.out.println(obj1);
//            System.out.println("");
//        }
        return bookList;
    }
    
    @Override
    public List<Book> addBook() {
        // Nên thêm chức năng nhập vào mỗi function
        System.out.print("Enter the book name: ");
        String name = sc.nextLine();
        System.out.print("Enter the type book: ");
        String type = sc.nextLine();
        System.out.print("Enter the price: ");
        double price = sc.nextDouble();
        System.out.print("Enter the quantity: ");
        int quantity = sc.nextInt();
        sc.skip("\n");

        Book book = new Book(price, name, type, quantity);
        bookList.add(book);
        return bookList;
    }

    @Override
    public List<Book> removeBook() {
        System.out.print("Enter the ID book: ");
        String id = sc.nextLine();
        int i = 0;
        // Nên tạo clone cho object Book hoặc new Book(bookList)
        for (Book obj : bookList) {
            if (obj.getIdBook().equals(id)) {
                bookList.remove(i);
                return bookList;
            }
            ++i;
        }
        return bookList;
    }

    @Override
    public List<Book> sortByPrice() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        return sortingList;
    }
    
    @Override
    public List<Book> sortByName() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getBookName().compareTo(o2.getBookName());
            }

        });
        return sortingList;
    }

    @Override
    public List<Book> sortByType() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getBookType().compareTo(o2.getBookType());
            }

        });
        return sortingList;
    }

    @Override
    public List<Book> sortByQuantity() {
        List<Book> sortingList = new ArrayList<>(bookList);
        Collections.sort(sortingList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Integer.compareUnsigned(o1.getQuantity(), o2.getQuantity());
            }

        });
        return sortingList;
    }
}
