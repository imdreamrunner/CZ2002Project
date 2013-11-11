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
            	(new MovieManagingController()).run();
            	break;
            case 2:
            	(new ShowManagingController()).run();
                break;
            case 3: //set price
            	(new PriceManagingController()).run();
                break;
            case 4: //set holiday
            	(new HolidayManagingController()).run();
                break;
            case 5: //generate sales report
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
        		"2. Update show list",
                "3. Set price",
                "4. Set holiday",
                "5. Generate sale revenue report",
                "6. Go back to previous page"});
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
