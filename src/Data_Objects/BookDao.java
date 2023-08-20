/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Input.Inputter;
import Model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BookDao implements IBookDao {

    private List<Book> bookList = null;
    Scanner sc = new Scanner(System.in);
    private Map<String, Integer> map = null;

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
    public boolean addBook(String path) throws Exception {
        System.out.println("");
        String name = Inputter.inputNonBlankStr("Enter the book name: ");
        String type = Inputter.inputNonBlankStr("Enter the book type: ");
        double price = Inputter.inputDouble("Enter the price: ");
        int quantity = Inputter.inputInt("Enter the quantity: ");

        if (isBookNameExist(name)) {
//                System.out.println("The book name already exists.");
            return false;
        }
        Book book = new Book(price, name, type, quantity);
        bookList.add(book);
//            System.out.println("The book has been added successfully.");
        // Nếu không lưu được vào database cũng đồng nghĩa với việc addBook() -> return False
        return writeFileBook(path, bookList);
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
    public boolean removeBook(String path) throws Exception {
        String id = Inputter.inputNonBlankStr("\nEnter the ID book: ");
        int i = 0;
        for (Book obj : bookList) {
            if (obj.getIdBook().equals(id)) {
                bookList.remove(i);
                // Solution 1: remove(i) thì sẽ reset lại từ đó (index) (Không hiệu quả)
                // Solution 2: writeFile() lại bằng cách reset lại static oID
                // Solution 3: Tìm ID có khoảng cách idOne - idTwo > 1 sau đó reset từ đó
                // Đã thêm solution 3
                setID();
                return writeFileBook(path, bookList);
            }
            ++i;
        }
        return false;
    }

    private void setID() {
        for (int i = 0; i < bookList.size() - 1; ++i) {
            if ((Integer.parseInt(bookList.get(i + 1).getIdBook())
                    - Integer.parseInt(bookList.get(i).getIdBook())) > 1) {
                int temp = Integer.parseInt(bookList.get(i).getIdBook());
                for (int j = i; j < bookList.size(); ++j) {
                    bookList.get(j).setIdBook(String.valueOf(temp++));
                }
                Book.setoID(temp);
                return;
            }
        }
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
        boolean isFirstLine = true;
        try {
            FileWriter writer = new FileWriter(path);
            for (E bookObj : list) {
                if (!isFirstLine) {
                    writer.write("\n");
                } else {
                    isFirstLine = false;
                }
                writer.write(fID++ + "," + bookObj.toString());
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

    public Map<String, Integer> addToCart() {
        map = new HashMap<>();
        map.clear();
        List<Book> list = new ArrayList<>(bookList);
        System.out.println("Press \"exit\" if you want to do next option!");
        while (true) {
            String ID = Inputter.inputStr("Enter ID book: ");
            if (ID.equalsIgnoreCase("exit")) {
                break;
            }
            int quantity = Inputter.inputInt("Enter quantity: ");
            String fullID = "ISBN 978-" + ID;
            for (int i = 0; i < list.size();) {
                String[] subB = list.get(i).toString().split(",");
                int quantityOfList = Integer.parseInt(subB[4]);
                if (fullID.equalsIgnoreCase(subB[0]) && (quantity <= quantityOfList && quantityOfList > 0)) {
                    if (quantity <= 0) {
                        System.out.println("Not valid quantity!");
                    } else {
                        System.out.println("Add successful!");
                        map.put(ID, quantity);
                    }
                    break;
                } else if (!fullID.equalsIgnoreCase(subB[0])) {
                    i++;
                    if (i == list.size() - 1) {
                        System.out.println("Not found!");
                        break;
                    }
                } else if (fullID.equalsIgnoreCase(subB[0]) && quantityOfList == 0) {
                    System.out.println("Sold out!");
                    break;
                } else if (fullID.equalsIgnoreCase(subB[0]) && (quantity > quantityOfList && quantityOfList > 0)) {
                    System.out.println("Not enough, try later!");
                    break;
                }
            }
        }
        return map;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    @Override
    public boolean writeBackUp(String path) throws Exception { //Ghi data của map vô file
        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(map.toString());
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return false;
    }

    @Override
    public boolean changeProduct() {
        while (true) {  //function replace va delete riêng (tạm thời)... sẽ có update function này sau!
            int option = Inputter.inputInt("Do you want to replace (and add) or delete items in your cart? (Choose 1 or 2, if not press any number): ");
            if (option == 3) {
                break;
            }
            switch (option) {
                case 1:
                    String newID = Inputter.inputStr("Enter ID of book need to replace: ");
                    if (newID.equalsIgnoreCase("exit")) {
                        break;
                    }
                    int newQuantity = Inputter.inputInt("Enter quantity need to replace: ");
                    if (newQuantity <= 0) {//neu vuot qua quantity trong book list cho phep va <= 0
                        System.out.println("New quantity is not valid, try later");
                    } else {
                        map.put(newID, newQuantity);
                        System.out.println("Replace successfully!");
                    }
                    break;
                case 2: // Thêm trường hợp map đag null và không có id đó trong map !!
                    String ID = Inputter.inputStr("Enter ID need to delete: ");
                    if (ID.equalsIgnoreCase("exit")) {
                        break;
                    }
                    map.remove(ID);
                    System.out.println("Delete successfully!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        return false;
    }

    @Override
    public List<Book> updateQuantity() throws Exception {
        for (Book obj : bookList) {// nen cong don quantity
            String bookId = obj.getIdBook();
            if (map.containsKey(bookId)) {
                int quantityGetFromCustomer = map.get(bookId);
                int newQuantity = obj.getQuantity() - quantityGetFromCustomer;
                obj.setQuantity(newQuantity);
            }
        }
        return bookList;
    }
}
