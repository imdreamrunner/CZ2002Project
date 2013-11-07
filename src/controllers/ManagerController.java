package controllers;

import java.util.List;
import java.util.Scanner;

import models.Movie;
import models.Staff;
import utils.Controller;

public class ManagerController extends Controller {

	@Override
	public void run() {
        gi.display("***** Staff Page *****");
        ////log in section
        boolean valid = false;
        while (!valid) {
            valid = login();
            if (!valid) {
            	gi.display(new String[] {
            		"Invalid Account \n",
                    "1. Log in again \n",
                    "2. go back to previous page \n",
                    "Please input your choice: "
            	});
                int choice = gi.inputInteger("choice", 1, 2);
                if (choice == 2) return;
            }
        }
        /////after log in
        displayMenu();
        int choice;
        choice = gi.inputInteger("choice", 1, 7);
        while (choice<=6) {
            switch (choice) {
                case 1: //add movie
                    String newMovieName = gi.inputString("movie name");
                    String newMovieType = gi.inputString("movie type");
                    int newMovieStatus = gi.inputInteger("status (1. now showing 2. coming soon)", 1, 2);
                    boolean success = addMovie(newMovieName,newMovieType,newMovieStatus);
                    if (success) System.out.println("Movie added!");
                    else System.out.println("Error!");
                    break;
                    
                case 2: //update movie details
                	displayMovieList();
                    int movieId = gi.inputInteger("movie id");
                    gi.display("Enter filed number to edit: [Name,Type,Satus] ");
                    String key = gi.inputString("key");
                    String value = gi.inputString("new value");
                    success = editMovie(movieId,key,value);
                    if (success) gi.display("Movie details updated!");
                    else gi.display("Error!");
                    break;
                    
                case 3:
                	(new ShowManagingController()).run();
                    break;
                case 4: //set price
                	(new PriceManagingController()).run();
                    break;
                case 5: //set holiday
                	(new HolidayManagingController()).run();
                    break;
                case 6: //generate sales report
                	(new ReportController()).run();
                    break;
            }
            displayMenu();
            choice = gi.inputInteger("choice", 1, 7);
        }
	}

    public void displayMenu() {
        gi.display(new String[] {"1. Enter forthcoming movie",
                "2. Update movie details",
        		"3. Update show list",
                "4. Set price",
                "5. Set holiday",
                "6. Generate sale revenue report",
                "7. Go back to previous page"});
    }
    
    public void displayMovieList() {
    	System.out.println("*****MOVIE LIST*****");
    	List<Movie> movieList = getMovieList();
    	for (Movie movie : movieList) {
    		displayMovieInfo(movie);
    	}
    	System.out.println("*****END OF MOVIE LIST*****");
    }
    
    public void displayMovieInfo(Movie movie) {
    	System.out.println("MovieID = " + movie.getId() + " Movie Name = " + movie.getName()
    			+ " Movie Type = " + movie.getType() + " Mvoie Status = " + movie.getStatus());
    }
   
    

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.print("Username: ");
        username = scanner.next();
        System.out.print("Password: ");
        password = scanner.next();
        return (checkAccount(username, password));
    }

    public List<Movie> getMovieList() {
    	List<Movie> movieList = Movie.getAll();
    	return movieList;
    }
    
    public boolean checkAccount(String username,String password) {
    	Staff staffAccount = Staff.getByUsername(username);
   
    	if (staffAccount.getPassword().equals(password)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean addMovie(String newMovieName, String newMovieType, int newMovieStatus) {
    	Movie newMovie = new Movie();
    	newMovie.setName(newMovieName);
    	newMovie.setType(newMovieType);
    	newMovie.setStatus(newMovieStatus);
    	newMovie.save();
        return true;  //for successful adding
    }
    
    public boolean editMovie(int id, String key, String value) {
    	Movie movie = Movie.getOne(id);
    	if (key.equals("Name")) {
    		movie.setName(value);
    	} else if (key.equals("Type")) {
    		movie.setType(value);
    	} else if (key.equals("Status")) {
    		try {
    			int newStatus = Integer.parseInt(value);
    			movie.setStatus(newStatus);
    		} catch (Exception e) {
    			return false;
    		}
    	} else return false;
    	movie.save();
        return true;
    }
    
}
