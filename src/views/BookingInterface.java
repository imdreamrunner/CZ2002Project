package views;
import controllers.BookingController;
import controllers.SystemController;
import models.Customer;
import models.Show;
import models.Movie;

import java.util.Scanner;

public class BookingInterface {
    public static void main() {
    	displayMenu();
        Scanner scanner = new Scanner(System.in);
        int showId = 0, seatId = 0, result=-1;
        Customer customer= null;
        System.out.println("Please enter the show id for booking: ");
        showId = scanner.nextInt();
        displaySeats(showId);
        System.out.println("Please enter the seat id for booking: ");
        seatId = scanner.nextInt();
        inputUserInfo(customer);
        displayBookingInfor(showId);
        inputUserInfo(customer);
        result = BookingController.createBooking(showId, seatId, customer);
        displayBookingResult(result);
    }

    public static void displayMenu() {
        System.out.println("***** Customer Page *****");
        System.out.printf("Welcome to XXX CinemaComplex Website ");
    }
    
    public static void displayBookingInfor(int showId){
    	System.out.println("***** Movie Info *****");
    	Show show = null;
    	Movie movie = null;
    	show = models.Show.getOne(showId);
    	movie = show.getMovie();
        System.out.printf("Movie name: "+ movie.getName());
        System.out.printf("Cineplex: "+ show.getCinema().getCineplex().getName());
        System.out.printf("Cinema: "+ show.getCinema().getCinemaCode());
        System.out.printf("Date: "+ show.getShowTime().getTime());
    }
    public static void displaySeats(int showId){
    	int[] seats;
    	int i=0;
//    	seats=controllers.SystemController.getSeats(showId);
    	System.out.println("***** Seat Info *****");
    	System.out.println("***** Seat Available *****");
/*    	while(seats[i]){
    		System.out.print(seats[i] + " ");	
    	}
*/
    	System.out.println("**************************");
    }
    public static void inputUserInfo(Customer customer){
    	int mobile;
    	int i=0;
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("***** Enter Customer Info *****");
    	System.out.println("Please enter your mobile number: ");
    	mobile = scanner.nextInt();
    	while (Customer.getOneByMobile(100) != null){
    		customer = 	Customer.getOneByMobile(100);
    	}customer.setMobile(mobile);
    	String name = null, email = null;
    	System.out.println("Please enter your name: ");
    	customer.setName(name);
    	System.out.println("Please enter your email address: ");
    	customer.setEmail(email);
    	customer.saveData();
    }
    public static void displayBookingResult(int result){
    	if (result>=0)
    		System.out.println("***Booking successful!***");
    	else
    		System.out.println("***Booking failure!***");
    }
    
}
