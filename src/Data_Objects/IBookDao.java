/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Model.Book;
import java.util.*;

public interface IBookDao {

    public boolean addBook(String path) throws Exception;
    
    public boolean removeBook(String path) throws Exception;

    public List<Book> sortByPrice();

    public List<Book> sortByName();

    public List<Book> sortByType();

    public List<Book> sortByQuantity();

    public <E> boolean writeFileBook(String path, List<E> list) throws Exception;
    
    public Map<String, Integer> addToCart(); // my part
    
    public boolean writeBackUp(String path, String userName) throws Exception;
    
    public boolean changeProduct();
    
    public List<Book> updateQuantity()throws Exception;
}
