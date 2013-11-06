package views;

import controllers.BookingController;
import controllers.SystemController;

import models.Customer;
import models.Show;
import models.Movie;
import models.Seat;

import java.util.Scanner;
import java.util.List;

public class BookingInterface {
	
    public static void main() {
    	displayMenu();
        Scanner scanner = new Scanner(System.in);
        boolean result = false;
        
        Customer customer= null;
        System.out.print("Please enter the show id for booking: ");
        int showId = scanner.nextInt();
        displayShowInfo(showId);
        displaySeats(showId);
        
        System.out.print("Please enter the seat id for booking: ");
        int seatId = scanner.nextInt();
        
        System.out.println("*****ENTER COSTOMER INFO*****");
    	System.out.print("Please enter your mobile number: ");
    	int mobile = scanner.nextInt();
    	if (Customer.getOneByMobile(mobile) != null) {  //!!!!!ugly code!!!!!
    		customer = 	Customer.getOneByMobile(mobile);
    	} else {
    		System.out.println("Please enter your name: ");
        	String name = scanner.next();
        	System.out.println("Please enter your email address: ");
        	String email = scanner.next();
        	customer = SystemController.CreateCustomer(name, mobile, email);
    	}
        
        result = BookingController.createBooking(showId, seatId, customer);
        displayBookingResult(result);
    }

    public static void displayMenu() {
        System.out.println("***** Customer Page *****");
        System.out.println("Welcome to XXX CinemaComplex Website ");
    }
    
    public static void displayShowInfo(int showId){
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
    public static void displaySeats(int showId){
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
    
    
    public static void displayBookingResult(boolean result){
    	if (result)
    		System.out.println("***Booking successful!***");
    	else
    		System.out.println("***Booking failure!***");
    }
    
}
