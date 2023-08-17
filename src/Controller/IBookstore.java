/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.util.*;

public interface IBookstore {

    public void loginSystem() throws Exception;

    public void addBook() throws Exception;

    public void removeBook() throws Exception;

    public List<Book> sortByPrice();

    public List<Book> sortByType();

    public List<Book> sortByQuantity();

    public <E> boolean writeFileBook(String path, List<E> list) throws Exception;
    
    public <E> boolean writeFileUser(String path, List<E> list) throws Exception;
}
