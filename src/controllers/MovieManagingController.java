package controllers;

import java.util.List;

import models.Movie;
import utils.Controller;

public class MovieManagingController extends Controller {

	@Override
	public void run() {
		String[] menu = {
				"1. List All Movies",
				"2. Add a Movie",
				"3. Edit a Movie",
				"4. Back"
		};
		gi.display(menu);
		int choice = gi.inputInteger("choice", 1, 4);
		switch(choice) {
		case 1:
			displayMovieList();
			break;
		case 2:
            String newMovieName = gi.inputString("movie name");
            int newMovieType = gi.inputInteger("type (0 regular, 1 3D)", 0, 1);
            int newMovieStatus = gi.inputInteger("status (0. coming soon 1. preview 2. on show 3. coming soon)", 0, 3);
            boolean success = addMovie(newMovieName,newMovieType,newMovieStatus);
            if (success) System.out.println("Movie added!");
            else System.out.println("Error!");
			break;
		case 3: 
        	displayMovieList();
            int movieId = gi.inputInteger("movie id");
            gi.display("Enter filed number to edit: [Name,Type,Status] ");
            success = editMovie(movieId);
            if (success) gi.display("Movie details updated!");
            else gi.display("Error!");
			break;
		}
	}
	
	public void displayMovieList() {
    	gi.display("*****MOVIE LIST*****");
    	List<Movie> movieList = Movie.getAll();
    	for (Movie movie : movieList) {
    		displayMovieInfo(movie);
    	}
    	gi.display("*****END OF MOVIE LIST*****");
    }
	
	public void displayMovieInfo(Movie movie) {
    	gi.display("MovieID = " + movie.getId() + " Movie Name = " + movie.getName()
    			+ " Movie Type = " + movie.getType() + " Mvoie Status = " + movie.getStatus());
    }

	public boolean addMovie(String newMovieName, int newMovieType, int newMovieStatus) {
    	Movie newMovie = new Movie();
    	newMovie.setName(newMovieName);
    	newMovie.setType(newMovieType);
    	newMovie.setStatus(newMovieStatus);
    	newMovie.save();
        return true;  //for successful adding
    }
    
    public boolean editMovie(int id) {
        String key = gi.inputString("key");
    	Movie movie = Movie.getOne(id);
    	if (key.equals("Name")) {
    		 String value = gi.inputString("movie name");
    		movie.setName(value);
    	} else if (key.equals("Type")) {
    		 int value = gi.inputInteger("movie type", 0, 1);
    		 movie.setType(value);
    	} else if (key.equals("Status")) {
    		int value = gi.inputInteger("movie status", 0, 3);
			movie.setStatus(value);
    	} else return false;
    	movie.save();
        return true;
    }
}
