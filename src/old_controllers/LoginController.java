package old_controllers;

import models.Staff;

public class LoginController {

    public static boolean checkAccount(String username,String password) {
    	Staff staffAccount = Staff.getByUsername(username);
   
    	if (staffAccount.getPassword().equals(password)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
