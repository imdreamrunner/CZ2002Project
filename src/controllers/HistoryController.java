package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Booking;
import models.Customer;
import models.Ticket;
import utils.Controller;

public class HistoryController extends Controller {

	@Override
	public void run() {
		gi.display(menu);
		
    	String name = gi.inputString("name");
    	//do we need any error checking on this?
    	int mobile = gi.inputInteger("mobile");
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
    		printBooking(booking);
    		/*
    		gi.display(booking.getCreateTime().toString() + " " + booking.getMovie().getName()
    				+ "*" + booking.getTickets().size()
    				+ " $" + booking.getTotalPrice()/100);
    		*/
    		
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
    
    public void printBooking(Booking booking) {
		 List<Ticket> tickets = booking.getTickets();
		 int i = 0;
		 gi.display("****************");
		 gi.display("Movie " + tickets.get(0).getMovie().getName());
		 gi.display("Cineplex " + tickets.get(0).getCineplex().getName());
		 gi.display("Cinema " + tickets.get(0).getCinema().getCinemaCode());
		 gi.display("Time " + tickets.get(0).getShow().getShowTime().toLocaleString());
		 gi.display("--------------");
		 for (Ticket ticket : tickets) {
			 i++;
			 gi.display("Ticket " + i);
			 gi.display("Seat " + ticket.getSeat().getName());
			 gi.display("Price " + ticket.getPrice() / 100.0);
			 String type = "Adult";
			 if (ticket.getTicketType() == 2)
				 type = "Senior";
			 if (ticket.getTicketType() == 3)
				 type = "Student";
			 gi.display("Type " + type);
			 gi.display("--------------");
		 }
		 gi.display("Totol price: " + booking.getTotalPrice() / 100.0);
	 }
}
