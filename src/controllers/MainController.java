package controllers;

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
	                "3. Quit",
	                "Please input your choice: "
			};
			gi.display(menu);
			choice = gi.inputInteger("choice", 1, 3);
			switch (choice) {
			case 1:
				break;
			case 2:
				break;
			}
		}
		gi.display("Thank you for using MOBLIMA");
	}
}
