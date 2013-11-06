package views;

import java.util.Scanner;
import models.Customer;

public class HistoryInterface {
    
	public static void main() {
		displayMenu();
		int mobile;
		Customer customer = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
    	String name = scanner.next();
        System.out.println("Please enter your mobile number: ");
    	mobile = scanner.nextInt();
    	customer = 	Customer.getOneByMobile(mobile);
        
	}        

    public static void displayMenu() {
        System.out.println("***** Customer Page *****");
        System.out.println("***** History page *****");
    }
}
