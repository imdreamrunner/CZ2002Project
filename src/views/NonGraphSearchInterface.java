package views;

import models.Cineplex;
import models.Movie;
import models.Show;

import java.util.List;
import java.util.Scanner;

import old_controllers.ListingController;

public class NonGraphSearchInterface {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		char search = 'y';
		int i;
		
		System.out.println("***** Movie Searching and Listing *****");
		while(search == 'y'){
			
			System.out.println("==== Cineplex List ====");
			List<Cineplex> cineplexList = ListingController.getCineplexList();
			i = 0;
			for(Cineplex cineplex : cineplexList){
				System.out.printf("%4d. %s\n",i,cineplex.getName());
				i++;
			}
			
			System.out.print("\nEnter CineplexId to display Movie List on show: ");
			int cineplexId = sc.nextInt();             //In this case, cineplexId is index, not real cineplexId, the same to movieId.
			System.out.println("==== Movie List ====");
			List<Movie> movieList = ListingController.getMovieByCineplex(cineplexList.get(cineplexId));
			i = 0;
			for(Movie movie : movieList){
				System.out.printf("%4d. %s\n",i,movie.getName());
				i++;
			}
			
			System.out.print("\nEnter movieId to display showtime: ");
			int movieId = sc.nextInt();
			Movie movie = movieList.get(movieId);
			System.out.println("Title: " + movie.getName() +
					           "\nType: " + movie.getType() +
					           "\nRating" + movie.getRating());
			System.out.printf("==== Showtime of %s ====\n",movie.getName());
			//System.out.println("ShowId  Cinema    Showtime            Type    Rating");
			System.out.println("ShowId  Cinema    Showtime");
			List<Show> showList = ListingController.getShowByCineplextAndMovie(cineplexList.get(cineplexId),movieList.get(movieId));
			for(Show show : showList){
				//System.out.printf("%-8d%-10s%-20s%-8s%s\n",show.getId(),show.getCinema().getCinemaCode(),show.getShowTime().toString(),show.getMovie().getType(),show.getMovie().getRating());
				System.out.printf("%-8d%-10s%-20s\n",show.getId(),show.getCinema().getCinemaCode(),show.getShowTime().toString(),show.getMovie().getType());
			}
			
			System.out.print("\nEnter 'y' to search again, or else quit searching: ");
			search = sc.next().charAt(0);
		}
	}
}
