package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Booking;
import models.Customer;
import utils.Controller;

public class HistoryController extends Controller {

	@Override
	public void run() {
		displayMenu();
		
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
    	String name = scanner.next();
        System.out.println("Please enter your mobile number: ");
    	int mobile = scanner.nextInt();
    	Customer customer = validateCustomer(name, mobile);
    	
    	if ( customer == null) {
    		System.out.println("Sorry, no matching customers found.");
    	} else {
    		List<Booking> bookingList = getBookingByCustomer(customer);
    		displayHistory(bookingList);
    	}
	}
    public void displayMenu() {
        System.out.println("***** Customer History Page *****");
    }    
    public void displayHistory(List<Booking> bookingList){
    	for (Booking booking : bookingList) {
    		System.out.println(booking.getCreateTime().toString() + " " + booking.getMovie().getName()
    				+ " " + booking.getTotalPrice());
    		
    	}
    	System.out.println("********************************");
    }
    
    public Customer validateCustomer(String name, int mobile){
    	Customer customer = Customer.getOneByMobile(mobile);
    	if (customer == null) return null;
    	if (!customer.getName().equals(name)) return null;
    	return customer;
    }
		
    public List<Booking> getBookingByCustomer(Customer customer) {
    	List<Booking> bookingList = Booking.getAll();
    	List<Booking> resultList = new ArrayList<Booking>();
    	for (Booking booking : bookingList) {
    		if (booking.getCustomer().equals(customer)) 
    			resultList.add(booking);
    	}
    	return resultList;
    }
    
}
