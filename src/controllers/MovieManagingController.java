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
            int newMovieStatus = gi.inputInteger("status (0. coming soon 1. preview 2. on show 3. end)", 0, 3);
            String newMovieRating = gi.inputString("rating");
            boolean success = addMovie(newMovieName,newMovieType,newMovieStatus, newMovieRating);
            if (success) System.out.println("Movie added!");
            else System.out.println("Error!");
			break;
		case 3: 
        	displayMovieList();
            int movieId = gi.inputInteger("movie id");
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
    	// gi.display("*****END OF MOVIE LIST*****");
    }
	
	public String movieType(int type) {
		if (type == 0)
			return "Regular";
		else
			return "3D";
	}
	
	public String movieStatus(int type) {
		switch (type) {
		case 0:
			return "coming soon";
		case 1:
			return "preview";
		case 2:
			return "on show";
		}
		return "end of show";
	}
	
	public void displayMovieInfo(Movie movie) {
    	gi.display("Movie " + movie.getId()
    			+ " \nName: " + movie.getName()
    			+ "\nType: " + movie.getType() 
    			+ "\nStatus: " + movieStatus(movie.getStatus())
    			+ "\nRating: " + movie.getRating()
    			+ "\n----------");
    }

	public boolean addMovie(String newMovieName, int newMovieType, int newMovieStatus, String newMovieRating) {
    	Movie newMovie = new Movie();
    	newMovie.setName(newMovieName);
    	newMovie.setType(newMovieType);
    	newMovie.setStatus(newMovieStatus);
    	newMovie.setRating(newMovieRating);
    	newMovie.save();
        return true;  //for successful adding
    }
    
    public boolean editMovie(int id) {
    	gi.display("Edit 1. name 2. type 3. status 4. rating");
        int key = gi.inputInteger("choice", 1, 4);
    	Movie movie = Movie.getOne(id);
    	if (key == 1) {
    		 String value = gi.inputString("name");
    		movie.setName(value);
    	} else if (key == 2) {
    		 int value = gi.inputInteger("type", 0, 1);
    		 movie.setType(value);
    	} else if (key == 3) {
    		int value = gi.inputInteger("status (0. coming soon 1. preview 2. on show 3. end)", 0, 3);
			movie.setStatus(value);
    	} else if (key == 4) {
    		movie.setRating(gi.inputString("rating"));
    	}
    	movie.save();
        return true;
    }
}
