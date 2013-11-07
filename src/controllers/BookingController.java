package controllers;

import controllers.SystemController;
import models.Customer;
import models.Seat;
import models.Show;
import models.Movie;
import models.Booking;
import models.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookingController {
	 public static boolean createBooking(int showId, List<Integer> seatIdList, Customer customer) {
		 Show show = Show.getOne(showId);
		 int totalPrice = 0;
		 //set all price to be 10 now
		 int price = 10;
		 Booking booking = new Booking();
		 booking.save();
		 for (int seatId : seatIdList) {
			 Seat seat = Seat.getOne(seatId);
			 if (seat == null) return false;
			 if (!seat.getStatus()) return false;
		 }
		 for (int seatId : seatIdList) {
			 Seat seat = Seat.getOne(seatId);
			 //change seat status
			 seat.setName(customer.getName());
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
		 booking.setTID("-1");
		 booking.setTotalPrice(totalPrice);
		 booking.setCustomer(customer);
		 booking.setBookingStatus("success");
		 booking.setCreateTime(new Date());
		 booking.save();
		 return true;
	 }
}
