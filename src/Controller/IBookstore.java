/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.util.*;

public interface IBookstore {
    public boolean checkUser();
    public List<Book> addBook();
    public List<Book> removeBook();
    public List<Book> sortByPrice();
    public List<Book> sortByType();
    public List<Book> sortByQuantity();
    public <E> boolean writeFile(String path, List<E> list) throws Exception;
}
