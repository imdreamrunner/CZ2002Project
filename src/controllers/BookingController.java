package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import models.Booking;
import models.Customer;
import models.Movie;
import models.Seat;
import models.Show;
import models.Ticket;
import utils.Controller;

public class BookingController extends Controller {

	@Override
	public void run() {
    	gi.display(menu);
        boolean result = false;
        
        Customer customer= null;
        System.out.print("Please enter the show id for booking: ");
        int showId = gi.inputInteger("showId");
        displayShowInfo(showId);
        displaySeats(showId);
        int quantity = gi.inputInteger("number of tickets", 1, Show.getOne(showId).getSeats().size());
        List<Integer> seatIdList = new ArrayList<Integer>(); 
        
        for (int i=0; i<quantity; i++) {
        	System.out.print("Please enter the seat id for booking: ");
            int seatId = gi.inputInteger("seatId");
            seatIdList.add(seatId);
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
        
        result = createBooking(showId, seatIdList, customer);
        displayBookingResult(result);
	}

        String[] menu = { 
        		"*****CUSTOMER PAGE*****",
        		"Welcome to XXX CinemaComplex Website"
        };
    
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
    	gi.display("*****SEAT AVAILABLE*****");
    	for (Seat seat : seatList) {
    		if (seat.getStatus()) {
    			gi.display(seat.getId() + " " + seat.getName());
    		}
    	}
    	gi.display("**************************");
    }
    
    
    public void displayBookingResult(boolean result){
    	if (result)
    		gi.display("***Booking successful!***");
    	else
    		gi.display("***Booking failed!***");
    }
    

	 public boolean createBooking(int showId, List<Integer> seatIdList, Customer customer) {
		 Show show = Show.getOne(showId);
		 int totalPrice = 0;
		 //set all price to be 10 now
		 int price = 10;
		 Booking booking = new Booking();
		 booking.setTID("-1");
		 booking.setCustomer(customer);
		 booking.setBookingStatus("success");
		 booking.setCreateTime(new Date());
		 booking.save();
		 for (int seatId : seatIdList) {
			 Seat seat = Seat.getOne(seatId);
			 if (seat == null) return false;
			 if (!seat.getStatus()) return false;
		 }
		 for (int seatId : seatIdList) {
			 Seat seat = Seat.getOne(seatId);
			 //change seat status
			 seat.setStatus(false);
			 seat.save();
			 totalPrice += price;
			 //create ticket
			 Ticket ticket = new Ticket();
			 ticket.setShow(show);
			 ticket.setPrice(price);
			 ticket.setSeat(seat);
			 ticket.setBooking(booking);
			 ticket.setTicketType("T T");
			 ticket.save();
		 }
		//create booking
		 booking.setTotalPrice(totalPrice);
		 booking.save();
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
