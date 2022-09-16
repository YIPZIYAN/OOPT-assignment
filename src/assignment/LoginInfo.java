/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.util.HashMap;

/**
 *
 * @author End User
 */
public class LoginInfo {
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    
    LoginInfo(){
        loginInfo.put("A1001", "Admin1111");
        loginInfo.put("A1002", "Admin2222");
        loginInfo.put("A1003", "Staff3333");
        loginInfo.put("A1004", "Staff4444");
        loginInfo.put("A1005", "Staff5555");

       
    }
    
    LoginInfo(String empId, String password){
        loginInfo.put(empId, password);
    }
    
    protected HashMap getIdPassword(){
        return loginInfo;
    }
}
   
