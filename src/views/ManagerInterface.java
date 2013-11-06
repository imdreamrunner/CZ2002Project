package views;


import controllers.LoginController;
import controllers.SystemController;

import java.util.List;
import java.util.Scanner;

import models.Movie;
import models.TicketPrice;

public class ManagerInterface {


    public static void main() {
        System.out.println("***** Staff Page *****");
        Scanner scanner = new Scanner(System.in);

        ////log in section
        boolean valid = false;
        while (!valid) {
            valid = login();
            if (!valid) {
                System.out.printf("Invalid Account \n" +
                        "1. Log in again \n" +
                        "2. go back to previous page \n" +
                        "Please input your choice: ");
                int choice;
                choice = scanner.nextInt();
                if (choice == 2) return;
            }
        }
        /////after log in
        displayMenu();
        int choice;
        choice = scanner.nextInt();
        while (choice<6) {
            switch (choice) {
            
                case 1: //add movie
                    System.out.println("Enter movie name: ");
                    String newMovieName = scanner.next();
                    System.out.println("Enter movie type: ");
                    String newMovieType = scanner.next();
                    System.out.println("Enter movie status: 1. now showing; 2. coming soon;");
                    int newMovieStatus = scanner.nextInt();
                    
                    boolean success = SystemController.addMovie(newMovieName,newMovieType,newMovieStatus);
                    if (success) System.out.println("Movie added!");
                    else System.out.println("Error!");
                    break;
                    
                case 2: //update movie details
                	displayMovieList();
                    System.out.print("Enter movie id: ");
                    int movieId = scanner.nextInt();
                    System.out.print("Enter filed number to edit: [Name,Type,Satus] ");
                    String key = scanner.next();
                    System.out.print("Enter new value: ");
                    String value = scanner.next();
                    success = SystemController.editMovie(movieId,key,value);
                    if (success) System.out.println("Movie details updated!");
                    else System.out.println("Error!");
                    break;
                    
                case 3: //set price
                    //print current price rule]
                	displayPriceList();
                    System.out.println("1. change a price; 2. add a new price; 3. delete a price");
                    int operation = scanner.nextInt();
                    if (operation == 1) {
                    	System.out.print("Enter ID: ");
                    	int newID = scanner.nextInt();
                    	System.out.print("Enter Value: ");
                    	int newPrice = scanner.nextInt();
                    	success = SystemController.editPrice(newID,newPrice);
                        if (success) System.out.println("Price updated!");
                        else System.out.println("Error!");
                    } else if (operation == 2) {
                    	System.out.print("Enter New Key: ");
                    	String newKey = scanner.next();
                    	System.out.print("Enter New Value: ");
                    	int newValue = scanner.nextInt();
                    	success = SystemController.addPrice(newKey,newValue);
                        if (success) System.out.println("Price added!");
                        else System.out.println("Error!");
                    } else if (operation == 3) {
                    	System.out.print("Enter ID: ");
                    	int newID = scanner.nextInt();
                    	success = SystemController.deletePrice(newID);
                    	if (success) System.out.println("Price deleted!");
                        else System.out.println("Error!");
                    }
                    break;
                case 4: //set holiday
                    break;
                case 5: //generate sales report
                    break;
            }
            displayMenu();
            choice = scanner.nextInt();
        }
    }

    public static void displayMenu() {
        System.out.print("1. Enter forthcoming movie \n" +
                "2. Update movie details \n" +
                "3, Set price \n" +
                "4. Set holiday \n" +
                "5. Generate sale revenue report \n" +
                "6. Go back to previous page \n" +
                "Please input your choice: ");
    }
    
    public static void displayMovieList() {
    	System.out.println("*****MOVIE LIST*****");
    	List<Movie> movieList = SystemController.getMovieList();
    	for (Movie movie : movieList) {
    		displayMovieInfo(movie);
    	}
    	System.out.println("*****END OF MOVIE LIST*****");
    }
    
    public static void displayMovieInfo(Movie movie) {
    	System.out.println("MovieID = " + movie.getId() + " Movie Name = " + movie.getName()
    			+ " Movie Type = " + movie.getType() + " Mvoie Status = " + movie.getStatus());
    }
    
    public static void displayPriceList() {
    	System.out.println("*****PRICE LIST*****");
    	List<TicketPrice> priceList = SystemController.getPriceList();
    	for (TicketPrice tp : priceList) {
    		System.out.println(tp.getId() + " " + tp.getKey() + " " + (tp.getValue()/100) + "." + (tp.getValue()%100));
    	}
    	System.out.println("*****END OF PRICE LIST*****");
    }

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.print("Username: ");
        username = scanner.next();
        System.out.print("Password: ");
        password = scanner.next();
        return (LoginController.checkAccount(username, password));
    }
}
