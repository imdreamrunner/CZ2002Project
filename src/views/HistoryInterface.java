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
		
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
    	String name = scanner.next();
        System.out.println("Please enter your mobile number: ");
    	int mobile = scanner.nextInt();
    	Customer customer = HistoryController.validateCustomer(name, mobile);
    	
    	if ( customer == null) {
    		System.out.println("Sorry, no matching customers found.");
    	} else {
    		List<Booking> bookingList = ListingController.getBookingByCustomer(customer);
    		displayHistory(bookingList);
    	}
	}        

    public static void displayMenu() {
        System.out.println("***** Customer History Page *****");
    }    
    public static void displayHistory(List<Booking> bookingList){
    	for (Booking booking : bookingList) {
    		System.out.println(booking.getDate().toString() + " " + booking.getMovie().getName()
    				+ " " + booking.getTotalPrice());
    		
    	}
    	System.out.println("********************************");
    }
}
