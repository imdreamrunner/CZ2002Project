package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Booking;
import models.Customer;
import utils.Controller;

public class HistoryController extends Controller {

	@Override
	public void run() {
		gi.display(menu);
		
		gi.display("Please enter your name: ");
    	String name = gi.inputString("name");
    	gi.display("Please enter your mobile number: ");
    	
    	//do we need any error checking on this?
    	int mobile = gi.inputInteger("mobile", 0, 99999999);
    	Customer customer = validateCustomer(name, mobile);
    	
    	if ( customer == null) {
    		gi.display("Sorry, no matching customers found.");
    	} else {
    		List<Booking> bookingList = getBookingByCustomer(customer);
    		displayHistory(bookingList);
    	}
	}
	String[] menu ={"***** Customer History Page *****"};
    	
    public void displayHistory(List<Booking> bookingList){
    	for (Booking booking : bookingList) {
    		gi.display(booking.getCreateTime().toString() + " " + booking.getMovie().getName()
    				+ " " + booking.getTotalPrice());
    		
    	}
    	gi.display("********************************");
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
