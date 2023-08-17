/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Input.Inputter;
import Model.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
        return bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }
    
    @Override
    public List<Book> addBook() {
        boolean checkStatus = false;
        while (!checkStatus) {
            String name = Inputter.inputNonBlankStr("Enter the book name: ");
            String type = Inputter.inputNonBlankStr("Enter the book type: ");
            double price = Inputter.inputDouble("Enter the price: ");
            int quantity = Inputter.inputInt("Enter the quantity: ");

            if (isBookNameExist(name)) {
                System.out.println("The book name already exists.");
            } else {
                Book book = new Book(price, name, type, quantity);
                bookList.add(book);
                System.out.println("The book has been added successfully.");
                checkStatus = true;
            }
        }
        return bookList;
    }

    private boolean isBookNameExist(String name) {
        for (Book obj : bookList) {
            if (obj.getBookName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> removeBook() {
        String id = Inputter.inputNonBlankStr("Enter the ID book: ");
        int i = 0;
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

    @Override
    public <E> boolean writeFileBook(String path, List<E> list) throws Exception {
        int fID = 1;
        try {
            FileWriter writer = new FileWriter(path);
            for (E bookObj : list) {
                writer.write(fID++ + "," + bookObj.toString());
                writer.write("\n");
            }
            writer.close();
//            System.out.println("File " + path + " has been written successfully.");
//            System.out.println("The Book has been added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("An error occurred while writing to the file: " + path);
            ex.printStackTrace();
            return false;
        }
        // Add thêm sách vào file.txt thì cần kết hợp thêm kỹ thuật viết file (WirteFile)
    }
}
