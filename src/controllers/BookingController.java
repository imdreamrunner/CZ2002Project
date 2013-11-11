package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Booking;
import models.Customer;
import models.Movie;
import models.Seat;
import models.Show;
import models.Ticket;
import utils.Controller;

public class BookingController extends Controller {
    private String[] menu = { 
    		"*****CUSTOMER PAGE*****",
    		"Please get show info from GUI."
    };
    
	@Override
	public void run() {
    	gi.display(menu);
    	GraphSearchInterface.run();
        boolean result = false;
        
        Customer customer= null;
        int showId = gi.inputInteger("show id");
        Show show = Show.getOne(showId);
        if (show == null) {
        	gi.display("No such show");
        	return;
        }
        displayShowInfo(showId);
        displaySeats(showId);
        int quantity = gi.inputInteger("number of tickets", 1, show.getAvailableSeats().size());
        List<Integer> seatIdList = new ArrayList<Integer>();
        List<Integer> seatTypeList = new ArrayList<Integer>(); 
        
        for (int i=0; i<quantity; i++) {
        	while (true) {
        		int seatId;
                String seatName = gi.inputString("seat");
                Seat seat = Seat.getOneByShowAndName(show, seatName);
                if (seat == null) {
                	gi.display("no such seat");
                	continue;
                } else {
                	seatId = seat.getId();
                	if (seatIdList.contains(seatId)) {
                		gi.display("already selected");
                		continue;
                	}
                }
                int seatType = gi.inputInteger("type (1 Adult, 2 Senior Citizen, 3 Student)");
                seatIdList.add(seatId);
                seatTypeList.add(seatType);
                break;
        	}
        }
        	
        gi.display("*****ENTER COSTOMER INFO*****");
    	int mobile = gi.inputInteger("mobile number");
    	if (Customer.getOneByMobile(mobile) != null) {  //!!!!!ugly code!!!!!
    		customer = 	Customer.getOneByMobile(mobile);
    		System.out.println("Customer Record Retrieved...");
    	} else {
        	String name = gi.inputString("name");
        	String email = gi.inputString("email");
        	customer = CreateCustomer(name, mobile, email);
    	}
        
        result = createBooking(showId, seatIdList, seatTypeList, customer);
        displayBookingResult(result);
	}
    
    public void displayShowInfo(int showId){
    	gi.display("*****SHOW INFO*****");
    	Show show = null;
    	Movie movie = null;
    	show = models.Show.getOne(showId);
    	movie = show.getMovie();
    	gi.display("Movie name: "+ movie.getName());
    	gi.display("Cineplex: "+ show.getCinema().getCineplex().getName());
    	gi.display("Cinema: "+ show.getCinema().getCinemaCode());
    	gi.display("Date: "+ show.getShowTime().getTime());
    	gi.display("*****END OF SHOW INFO*****");
    }

    public void displaySeats(int showId){
    	List<Seat> seatList;
    	Show show = Show.getOne(showId);
    	seatList = show.getSeats();
    	int width = 1;
    	char height = 'A';
    	for (Seat seat : seatList) {
    		String name = seat.getName();
    		char row = name.charAt(0);
    		int col = Integer.parseInt(name.substring(1));
    		if (col > width) {
    			width = col;
    		}
    		if (row > height) {
    			height = row;
    		}
    	}
    	for (char row = 'A'; row <= height; row++) {
    		for (int col = 1; col <= width; col++) {
    			System.out.println("" + row + col);
    		}
    	}
    	gi.display("*****SEAT AVAILABLE*****");
    	for (Seat seat : seatList) {
			gi.display(seat.getId() + " " + seat.getName());
    	}
    	gi.display("**************************");
    }
    
    
    public void displayBookingResult(boolean result){
    	if (result)
    		gi.display("***Booking successful!***");
    	else
    		gi.display("***Booking failed!***");
    }
    

	 public boolean createBooking(int showId, List<Integer> seatIdList, List<Integer> seatTypeList, Customer customer) {
		 Show show = Show.getOne(showId);
		 Booking booking = new Booking();
		 booking.setTID("" + new Date().getTime());
		 booking.setCustomer(customer);
		 booking.setBookingStatus("success");
		 booking.setCreateTime(new Date());
		 booking.save();
		 for (int seatId : seatIdList) {
			 Seat seat = Seat.getOne(seatId);
			 if (seat == null) return false;
			 if (!seat.getStatus()) return false;
		 }
		 for (int i = 0; i < seatIdList.size(); i++) {
			 int seatId = seatIdList.get(i);
			 int type = seatTypeList.get(i);
			 Seat seat = Seat.getOne(seatId);
			 //change seat status
			 seat.setStatus(false);
			 seat.save();
			 //create ticket
			 Ticket ticket = new Ticket();
			 ticket.setShow(show);
			 ticket.setSeat(seat);
			 ticket.setBooking(booking);
			 ticket.setTicketType(1);
			 ticket.save();
		 }
		//create booking
		 booking.save();
		 booking.calculateTotalPrice();
		 gi.display("Totol price: " + booking.getTotalPrice());
		 return true;
	 }
	 

	    public Customer CreateCustomer(String name, int mobile, String email){
	    	Customer customer = new Customer();
	    	customer.setMobile(mobile);
	    	customer.setName(name);
	    	customer.setEmail(email);
	    	customer.save();
	    	return customer;
	    }
}
