package controllers;

import models.Cineplex;
import models.Movie;
import models.Show;
import models.Cinema;

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
}
