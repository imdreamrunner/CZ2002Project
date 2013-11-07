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
import old_controllers.SystemController;
import utils.Controller;

public class BookingController extends Controller {

	@Override
	public void run() {
    	displayMenu();
        Scanner scanner = new Scanner(System.in);
        boolean result = false;
        
        Customer customer= null;
        System.out.print("Please enter the show id for booking: ");
        int showId = scanner.nextInt();
        displayShowInfo(showId);
        displaySeats(showId);
        System.out.print("Please enter the number of seats you want to book: ");
        int quantity = scanner.nextInt();
        List<Integer> seatIdList = new ArrayList<Integer>(); 
        for (int i=0; i<quantity; i++) {
        	System.out.print("Please enter the seat id for booking: ");
            int seatId = scanner.nextInt();
            seatIdList.add(seatId);
        }
        	
        System.out.println("*****ENTER COSTOMER INFO*****");
    	System.out.print("Please enter your mobile number: ");
    	int mobile = scanner.nextInt();
    	if (Customer.getOneByMobile(mobile) != null) {  //!!!!!ugly code!!!!!
    		customer = 	Customer.getOneByMobile(mobile);
    		System.out.println("Customer Record Retrieved...");
    	} else {
    		scanner.nextLine();
    		System.out.println("Please enter your name: ");
        	String name = scanner.nextLine();
        	System.out.println("Please enter your email address: ");
        	String email = scanner.nextLine();
        	customer = SystemController.CreateCustomer(name, mobile, email);
    	}
        
        result = createBooking(showId, seatIdList, customer);
        displayBookingResult(result);
	}

    public void displayMenu() {
        System.out.println("*****CUSTOMER PAGE*****");
        System.out.println("Welcome to XXX CinemaComplex Website ");
    }
    
    public void displayShowInfo(int showId){
    	System.out.println("*****SHOW INFO*****");
    	Show show = null;
    	Movie movie = null;
    	show = models.Show.getOne(showId);
    	movie = show.getMovie();
        System.out.printf("Movie name: "+ movie.getName());
        System.out.printf("Cineplex: "+ show.getCinema().getCineplex().getName());
        System.out.printf("Cinema: "+ show.getCinema().getCinemaCode());
        System.out.printf("Date: "+ show.getShowTime().getTime());
        System.out.println("*****END OF SHOW INFO*****");
    }

    public void displaySeats(int showId){
    	List<Seat> seatList;
    	Show show = Show.getOne(showId);
    	seatList = show.getSeats();
    	System.out.println("*****SEAT AVAILABLE*****");
    	for (Seat seat : seatList) {
    		if (seat.getStatus()) {
    			System.out.println(seat.getId() + " " + seat.getName());
    		}
    	}
    	System.out.println("**************************");
    }
    
    
    public void displayBookingResult(boolean result){
    	if (result)
    		System.out.println("***Booking successful!***");
    	else
    		System.out.println("***Booking failed!***");
    }
    

	 public boolean createBooking(int showId, List<Integer> seatIdList, Customer customer) {
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
