package controllers;

import gui.SearchGUI;
import utils.Controller;

public class MainController extends Controller {
	public void run() {
		int choice = 0;
		while (choice < 3) {
			gi.display("***** Welcome To MOBLIMA *****");
			String[] menu = {
					"Please select your entry:",
	                "1. Customer Entry",
	                "2. Staff Entry",
	                "3. Start GUI",
	                "4. Quit"
			};
			gi.display(menu);
			choice = gi.inputInteger("choice", 1, 3);
			switch (choice) {
			case 1:
				(new CustomerController()).run();
				break;
			case 2:
				(new ManagerController()).run();
				break;
			case 3:
				(new SearchGUI()).listMovie();
				break;
			}
		}
		gi.display("Thank you for using MOBLIMA");
	}
}
