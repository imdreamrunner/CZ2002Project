package controllers;

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
        return true;
    }
    public static boolean editPrice(int id, int newPrice) {
        return true;
    }
}
