/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_Objects;

import java.util.List;

/**
 *
 * @author Admins
 */
public interface IUserDao {

    public boolean login(String path) throws Exception;
    
    public boolean register(String path) throws Exception;
    
    public <E> boolean writeFileUser(String path, List<E> list) throws Exception;
    
    public String getUserName();
}
