package controllers;

import java.util.List;

import models.Cineplex;
import models.Movie;
import models.Show;
import utils.Controller;
import utils.MovieLister;

public class SearchController extends Controller implements MovieLister {

	@Override
	public void run() {
		int i;
		
		gi.display("***** Movie Searching and Listing *****");
		gi.display("==== Cineplex List ====");
		List<Cineplex> cineplexList = Cineplex.getAll();
		i = 0;
		for(Cineplex cineplex : cineplexList){
			System.out.printf("%4d. %s\n",i,cineplex.getName());
			i++;
		}
		int cineplexId = gi.inputInteger("cineplexId", 0, cineplexList.size() - 1);             //In this case, cineplexId is index, not real cineplexId, the same to movieId.
		gi.display("==== Movie List ====");
		List<Movie> movieList = Movie.getAllByCineplex(cineplexList.get(cineplexId), true);
		i = 0;
		for(Movie movie : movieList){
			System.out.printf("%4d. %s\n",i,movie.getName());
			i++;
		}
		
		int movieId = gi.inputInteger("movieId", 0 , movieList.size() - 1);
		Movie movie = movieList.get(movieId);
		gi.display("Title: " + movie.getName() +
				   "\nType: " + movie.getType() +
				   "\nRating" + movie.getRating());
		gi.display(String.format("==== Showtime of %s ====",movie.getName()));
		gi.display("ShowId  Cinema    Showtime");
		List<Show> showList = Show.getAllByCineplextAndMovie(cineplexList.get(cineplexId),movieList.get(movieId));
		for(Show show : showList){
			//System.out.printf("%-8d%-10s%-20s%-8s%s\n",show.getId(),show.getCinema().getCinemaCode(),show.getShowTime().toString(),show.getMovie().getType(),show.getMovie().getRating());
			gi.display(String.format("%-8d%-10s%-20s",show.getId(),show.getCinema().getCinemaCode(),show.getShowTime().toString(),show.getMovie().getType()));
		}
	}

	@Override
	public void listMovie() {
		run();
	}
}
