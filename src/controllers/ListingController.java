package controllers;

import models.Cineplex;
import models.Holiday;
import models.Movie;
import models.Show;
import models.Cinema;
import models.TicketPrice;

import java.util.ArrayList;
import java.util.List;

public class ListingController {
	
	public static List<Cineplex> getCineplexList() {
		List<Cineplex> cineplexList = Cineplex.getAll();
		return cineplexList;
	}
	
	public static List<Movie> getMovieByCineplex(Cineplex cineplex) {
		List<Show> showList = Show.getAll();
		List<Movie> movieList = new ArrayList<Movie>();
		for (Show show : showList) {
			boolean sameCineplex = show.getCinema().getCineplex().equals(cineplex);
			boolean notInList = !(movieList.contains(show.getMovie()));
			if ( sameCineplex && notInList ) {
				movieList.add(show.getMovie());
			}
		}
		return movieList;
	}
	
	public static List<Movie> getMovieByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getMovieByCineplex(cineplex);
	}
	
	public static List<Cinema> getCinemaByCineplex(Cineplex cineplex) {
		List<Cinema> cinemaList = Cinema.getAll();
		List<Cinema> resultList = new ArrayList<Cinema>();
		for (Cinema cinema : cinemaList) {
			if (cinema.getCineplex().equals(cineplex)) {
				resultList.add(cinema);
			}
		}
		return resultList;
	}
	
	public static List<Cinema> getCinemaByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getCinemaByCineplex(cineplex);
	}
	
    public static List<Movie> getMovieList() {
    	List<Movie> movieList = Movie.getAll();
    	return movieList;
    }
    
    public static List<TicketPrice> getPriceList() {
    	List<TicketPrice> priceList = TicketPrice.getAll();
    	return priceList;
    }
    
    public static List<Holiday> getHolidayList() {
    	List<Holiday> holidayList = Holiday.getAll();
    	return holidayList;
    }
    
    public static List<Show> getShowList() {
    	return Show.getAll();
    }
    
    public static List<Show> getShowByCineplextAndMovie(Cineplex cineplex, Movie movie) {
    	List<Show> showList = Show.getAll();
    	List<Show> resultList = new ArrayList<Show>();
    	for (Show show : showList) {
    		if (show.getMovie().equals(movie) && show.getCinema().getCineplex().equals(cineplex)) {
    			resultList.add(show);
    		}
    	}
    	return resultList;
    }

    public static List<Show> getShowByCineplextAndMovie(int cineplexId, int movieId) {
    	Cineplex cineplex = Cineplex.getOne(cineplexId);
    	Movie movie = Movie.getOne(movieId);
    	return getShowByCineplextAndMovie(cineplex, movie);
    }
    
    
}
