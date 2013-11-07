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
            String newMovieType = gi.inputString("movie type");
            int newMovieStatus = gi.inputInteger("status (1. now showing 2. coming soon)", 1, 2);
            boolean success = addMovie(newMovieName,newMovieType,newMovieStatus);
            if (success) System.out.println("Movie added!");
            else System.out.println("Error!");
			break;
		case 3: 
        	displayMovieList();
            int movieId = gi.inputInteger("movie id");
            gi.display("Enter filed number to edit: [Name,Type,Satus] ");
            String key = gi.inputString("key");
            String value = gi.inputString("new value");
            success = editMovie(movieId,key,value);
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
