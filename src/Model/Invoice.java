/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.*;

public class Invoice {
    
    private String idInvoice;
    private static int sID = 10000;
    private Date date;
    private User user;
    private Book books;
    private double totalPrice;
    private int totalBooks;

    public Invoice() {
    }

    public Invoice(Date date, User user, Book books, double totalPrice, int totalBooks) {
        this.idInvoice = Integer.toString(sID++);
        this.date = date;
        this.user = user;
        this.books = books;
        this.totalPrice = totalPrice;
        this.totalBooks = totalBooks;
    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Book getBooks() {
        return books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBooks(Book books) {
        this.books = books;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }
    
    @Override
    public String toString() {
//        return "Invoice [" + "ID = " + idInvoice
//                + ", Date = " + date
//                + ", User = " + user
//                + ", Total Price = " + totalPrice
//                + ", Total Books = " + totalBooks + "]";
        return "INV 2023" + "-" + idInvoice
                + "\nDate: " + date
                + "\nUser: " + user
                + "\nBook(s): " + books.toString()
                + "\nTotal Price: " + totalPrice
                + "\nTotal Books: " + totalBooks;
    }
}
