package controllers;

import controllers.SystemController;
import models.Customer;
import models.Seat;
import models.Show;
import models.Movie;

import java.util.Scanner;

public class BookingController {
	 public static boolean createBooking(int showId, int seatId, Customer customer){
	        Show show = Show.getOne(showId);
	    	Seat seat = Seat.getOne(seatId);
	    	if (seat.getStatus()==false){
	    		seat.setName(customer.getName());
	    		seat.setStatus(true);
	        	seat.save();
	        	return true;
	    	}else
	    		return false;
	 }

}
