package controllers;

import java.util.Scanner;

import utils.Controller;

public class CustomerController extends Controller {

	@Override
	public void run() {
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();
        while (choice < 3) {
            switch (choice) {
                case 1:
                    (new BookingController()).run();
                    break;
                case 2:
                    (new HistoryController()).run();
                    break;
                case 3:
                    return;
                case 4:
                    //how to quit directly?
            }
            displayMenu();
            choice = scanner.nextInt();
        }
	}

    public void displayMenu() {
        System.out.println("***** Customer Page *****");
        System.out.printf("1. Make a booking \n" +
                "2. View booking history \n" +
                "3. Go back to previous page \n" +
                "4. Quit \n" +
                "Please input your choice: ");
    }

}
