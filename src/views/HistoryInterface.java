package views;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import models.Booking;
import models.Customer;
import controllers.ListingController;
import controllers.HistoryController;

public class HistoryInterface {
    
	public static void main() {
		displayMenu();
		int mobile;
		String name;
		Customer customer = null;
		List<Booking> bookingList = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
    	name = scanner.next();
        System.out.println("Please enter your mobile number: ");
    	mobile = scanner.nextInt();
    	if (HistoryController.validCustomer(name, mobile) == null){
    		System.out.println("Sorry, no matching customer found.");
    	}else{
    		customer = HistoryController.validCustomer(name, mobile);
    	//	List<Booking> bookingList = ListingController.getHistory(customer);
    		displayHistory(bookingList);
    	}
	}        

    public static void displayMenu() {
        System.out.println("***** Customer History Page *****");
    }    
    public static void displayHistory(List<Booking> bookingList){
    	for (int i =0; i<bookingList.size(); i++){
    		System.out.println("");
    		
    	}
    	System.out.println("********************************");
    }
}
