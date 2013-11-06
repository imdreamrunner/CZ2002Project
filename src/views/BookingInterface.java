package views;
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
        Customer customer= new Customer();
        System.out.println("Please enter the show id for booking: ");
        showId = scanner.nextInt();
        displaySeats(showId);
        System.out.println("Please enter the seat id for booking: ");
        seatId = scanner.nextInt();
        inputUserInfo(customer);
        displayBookingInfor(showId);
        getUserInfo(customer);
        result = controllers.BookingController.createBooking(showId, seatId, customer);
        displayBookingResult(result);
    }

    public static void displayMenu() {
        System.out.println("***** Customer Page *****");
        System.out.printf("Welcome to XXX CinemaComplex Website ");
    }
    
    public static void displayBookingInfor(int showId){
    	System.out.println("***** Movie Info *****");
    	Show show = new Show();
    	Movie movie = new Movie();
    	show = models.Show.getOne(showId);
    	movie = Show.getMovie();
        System.out.printf("Movie name: "+ Movie.getName());
        System.out.printf("Cineplex: "+ Movie.getCinema().getCineplex().getname());
        System.out.printf("Cinema: "+ Movie.getCinema().getCinemaCode());
        System.out.printf("Date: "+ Show.getShowTime().getTime());
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
    	while (Customer.getOneByMobile() != null){
    		customer = 	Customer.getOneByMobile();
    	}customer.setMobile(mobile);
    	String name, email;
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
