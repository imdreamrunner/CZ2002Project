package controllers;

import utils.Controller;

public class CustomerController extends Controller {

	@Override
	public void run() {
		int choice = 0;
		while (choice < 4) {
			gi.display("***** Customer Page *****");
			String[] menu = {
					"1. Make a booking",
					"2. View booking history",
					"3. Go back to previous page",
					"4. Quit"
			};
		gi.display(menu);
		choice = gi.inputInteger("choice", 1, 4);

        switch (choice) {
        	case 1:
        		(new BookingController()).run();
        		break;
        	case 2:
        		(new HistoryController()).run();
        		break;
        	case 3:
        		return;
            }
        }
		gi.display("Thank you for using MOBLIMA");
	}
}
