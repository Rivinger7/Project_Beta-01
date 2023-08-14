/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.*;

public class Book {
    
    private static int oID = 1000;
    private String idBook;
    private double price;
    private String bookName;
    private String bookType;
    private int quantity;
    

    public Book() {
    }

    public Book(double price, String bookName, String bookType, int quantity) {
        this.idBook = Integer.toString(oID++);
        this.price = price;
        this.bookName = bookName;
        this.bookType = bookType;
        this.quantity = quantity;
    }

    public String getIdBook() {
        return idBook;
    }
    
    public double getPrice() {
        return price;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookType() {
        return bookType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
//        return "Book [" + "Name = " + bookName
//                + ", Type = " + bookType
//                + ", Price = " + price + "$"
//                + ", Quantity = " + quantity + "]";
        return "ISBN 978" + "-" + idBook + "," + bookName + "," + bookType
                + "," + (int)price + "," + quantity;
    }
}
