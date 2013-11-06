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
	
	public static List<Movie> getMovieByCineplexList(Cineplex cineplex) {
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
}
