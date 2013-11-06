package controllers;

import java.util.List;
import models.Movie;

public class SystemController {

    public static boolean addMovie(String newMovieName, String newMovieType, int newMovieStatus) {
    	Movie newMovie = new Movie();
    	newMovie.setName(newMovieName);
    	newMovie.setType(newMovieType);
    	newMovie.setStatus(newMovieStatus);
    	newMovie.save();
        return true;  //for successful adding
    }
    public static boolean editMovie(int id, String key, String value) {
    	/*
    	 * movie.getId() + " Movie Name = " + movie.getName()
    			+ " Movie Type = " + movie.getType() + "Movie Status = " + movie.getStatus());
    	 */
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
        return true;
    }
    public static boolean editPrice(int id, int newPrice) {
        return true;
    }
    
    public static List<Movie> getMovieList() {
    	List<Movie> movieList = Movie.getAll();
    	return movieList;
    }
}
