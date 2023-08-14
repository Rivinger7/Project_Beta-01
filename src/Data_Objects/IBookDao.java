/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Model.Book;
import java.util.*;

public interface IBookDao {
    public List<Book> addBook();
    public List<Book> removeBook();
    public List<Book> sortByPrice();
    public List<Book> sortByName();
    public List<Book> sortByType();
    public List<Book> sortByQuantity();
}
