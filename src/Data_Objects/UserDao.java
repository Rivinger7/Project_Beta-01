/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class UserDao implements IUserDao {

    private List<User> userList = null;
    Scanner sc = new Scanner(System.in);

    public UserDao(String path2) throws Exception {
        userList = loadUsers(path2);
    }
    
    private List<User> loadUsers(String path2) throws Exception {
        try {
            File userInfo = new File(path2);
            String fullPath = userInfo.getAbsolutePath();
            FileInputStream userInfoByte = new FileInputStream(fullPath);
            BufferedReader myInputUsersInfo = new BufferedReader(new InputStreamReader(userInfoByte));
            String thisLine;
            HashMap<String, String> userSystem = null;
            while ((thisLine = myInputUsersInfo.readLine()) != null) {
                User user = null;
                if (!thisLine.trim().isEmpty()) {
                    String split[] = thisLine.split(",");
                    if (userSystem == null) {
                        userSystem = new HashMap<>();
                    }
                    String userName = split[1].trim();
                    String password = split[2].trim();

                    user = new User(userName, password);

                    userSystem.put(userName, password);
                }
                if (userList == null) {
                    userList = new ArrayList<>();
                }
                userList.add(user);
            }
            myInputUsersInfo.close();
        } catch (Exception ex) {
            throw ex;
        }
//        for (User obj1 : userList) {
//            System.out.println(obj1);
//            System.out.println("");
//        }
        return userList;
    }
    
    @Override
    public boolean checkUser() {
        try {
            System.out.println("\nLogin System");
            System.out.print("User Name: ");
            String userName = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            for (User obj1 : userList) {
                if (userName.equals(obj1.getUserName()) && password.equals(obj1.getPassword())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        System.out.println("Username or Password is invalid");
        return false;
    }
    
}
