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
		
		gi.display("\nEnter CineplexId to display Movie List on show: ");
		int cineplexId = gi.inputInteger("cineplexId");             //In this case, cineplexId is index, not real cineplexId, the same to movieId.
		gi.display("==== Movie List ====");
		List<Movie> movieList = Movie.getAllByCineplex(cineplexList.get(cineplexId));
		i = 0;
		for(Movie movie : movieList){
			System.out.printf("%4d. %s\n",i,movie.getName());
			i++;
		}
		
		gi.display("\nEnter movieId to display showtime: ");
		int movieId = gi.inputInteger("movieId");
		Movie movie = movieList.get(movieId);
		gi.display("Title: " + movie.getName() +
				   "\nType: " + movie.getType() +
				   "\nRating" + movie.getRating());
		System.out.printf("==== Showtime of %s ====\n",movie.getName());
		//System.out.println("ShowId  Cinema    Showtime            Type    Rating");
		gi.display("ShowId  Cinema    Showtime");
		List<Show> showList = Show.getAllByCineplextAndMovie(cineplexList.get(cineplexId),movieList.get(movieId));
		for(Show show : showList){
			//System.out.printf("%-8d%-10s%-20s%-8s%s\n",show.getId(),show.getCinema().getCinemaCode(),show.getShowTime().toString(),show.getMovie().getType(),show.getMovie().getRating());
			System.out.printf("%-8d%-10s%-20s\n",show.getId(),show.getCinema().getCinemaCode(),show.getShowTime().toString(),show.getMovie().getType());
		}
		
		//System.out.print("\nEnter 'y' to search again, or else quit searching: ");
		//search = gi.inputString("search").charAt(0);
	}

	@Override
	public void listMovie() {
		run();
	}
}
