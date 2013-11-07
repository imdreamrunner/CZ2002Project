package controllers;

import utils.Controller;

public class MainController extends Controller {
	public void run() {
		gi.display("***** Welcome To MOBLIMA *****");
		String[] menu = {
				"Please select your entry: \n",
                "1. Customer Entry \n",
                "2. Staff Entry \n",
                "3. Quit \n",
                "Please input your choice: "
		};
		gi.display(menu);
		gi.display("Thank you for using MOBLIMA");
	}
}
