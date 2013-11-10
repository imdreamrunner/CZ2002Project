package controllers;

import java.util.List;

import models.Movie;
import models.Staff;
import utils.Controller;

public class ManagerController extends Controller {

	@Override
	public void run() {
        gi.display("***** Staff Page *****");
        ////log in section
        boolean valid = false;
        while (!valid) {
            valid = login();
            if (!valid) {
            	gi.display(new String[] {
            		"Invalid Account",
                    "1. Log in again",
                    "2. go back to previous page"
            	});
                int choice = gi.inputInteger("choice", 1, 2);
                if (choice == 2) return;
            }
        }
        /////after log in
        displayMenu();
        int choice;
        choice = gi.inputInteger("choice", 1, 7);
        while (choice<=6) {
            switch (choice) {
            case 1:
            case 2:
            	(new MovieManagingController()).run();
            	break;
            case 3:
            	(new ShowManagingController()).run();
                break;
            case 4: //set price
            	(new PriceManagingController()).run();
                break;
            case 5: //set holiday
            	(new HolidayManagingController()).run();
                break;
            case 6: //generate sales report
            	(new ReportController()).run();
                break;
            }
            displayMenu();
            choice = gi.inputInteger("choice", 1, 7);
        }
	}

    public void displayMenu() {
        gi.display(new String[] {
                "1. Update movie list",
        		"3. Update show list",
                "4. Set price",
                "5. Set holiday",
                "6. Generate sale revenue report",
                "7. Go back to previous page"});
    }
    
    public boolean login() {
        String username = gi.inputString("username")
             , password = gi.inputString("password");
        return (checkAccount(username, password));
    }

    public boolean checkAccount(String username,String password) {
    	Staff staffAccount = Staff.getByUsername(username);
    	if (staffAccount == null) {
    		return false;
    	}
    	if (staffAccount.getPassword().equals(password)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
