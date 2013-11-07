package controllers;

import utils.Controller;

public class MainController extends Controller {
	public void run() {
		gi.display("***** Welcome To MOBLIMA *****");
		String[] menu = {
				"Please select your entry:",
                "1. Customer Entry",
                "2. Staff Entry",
                "3. Quit",
                "Please input your choice: "
		};
		gi.display(menu);
		gi.display("Thank you for using MOBLIMA");
	}
}
