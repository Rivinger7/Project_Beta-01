/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import Input.Inputter;
import Model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
        return userList;
    }

    @Override
    public boolean login(String path) throws Exception {
        try {
            int i = 0;
            boolean check = false;
            while (!check) {
                System.out.println("\nLogin System");
                String userName = Inputter.inputNonBlankStr("User Name: ");
                String password = Inputter.inputNonBlankStr("Password: ");
                for (User obj1 : userList) {
                    if (userName.equals(obj1.getUserName()) && password.equals(obj1.getPassword())) {
                        return true;
                    }
                }
                if (i >= 0 && !check) {
                    System.out.println("Username or Password is invalid");
                    // Register
                    // False == register failed or not register yet
                    String registerAccount = Inputter.inputNonBlankStr("Do you want to"
                            + " register an Account? "
                            + "(Yes / No): ");
                    if (registerAccount.equalsIgnoreCase("Yes")) {
                        boolean checkStatus = false;
                        while (!checkStatus) {
                            checkStatus = register(path);
                            if (!checkStatus) {
                                String temp = Inputter.inputNonBlankStr("Do you want to register again?"
                                        + " (Yes / No): ");
                                if (temp.equalsIgnoreCase("No")) {
                                    checkStatus = true;
                                }
                            }
                        }
                    }
                }
                ++i;
            }
        } catch (Exception ex) {
            throw ex;
        }
        System.out.println("Username or Password is invalid");
        return false;
    }

    @Override
    public boolean register(String path) throws Exception {
        System.out.println("\nRegister System");
        String userName = Inputter.inputNonBlankStr("Username: ");
        String password = Inputter.inputNonBlankStr("Password: ");
        for (User obj : userList) {
            if (obj.getUserName().equals(userName) && obj.getPassword().equals(password)) {
                System.out.println("Registeration failed");
                return false;
            }
        }
        User user = new User(userName, password);
        userList.add(user);
        return writeFileUser(path, userList);
    }

    @Override
    public <E> boolean writeFileUser(String path, List<E> list) throws Exception {
        int fID = 1;
        try {
            FileWriter writer = new FileWriter(path);
            boolean isFirstLine = true;
            for (E userObj : list) {
                if(!isFirstLine) {
                    writer.write("\n");
                } else {
                    isFirstLine = false;
                }
                writer.write(fID++ + "," + userObj.toString());
            }
            writer.close();
//            System.out.println("File " + path + " has been written successfully.");
            System.out.println("Username has been registered successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("An error occurred while writing to the file: " + path);
            ex.printStackTrace();
            return false;
        }
    }
}
