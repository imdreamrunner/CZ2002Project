package views;

import java.util.List;
import java.util.Scanner;

import controllers.ListingController;
import controllers.SystemController;
import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Show;

public class ShowManagingInterface {
	public static Scanner scanner;
	public static void main(String[] args) {
		main();
	}
	public static void main() {
		scanner = new Scanner(System.in);
        System.out.println("1. add a show 2. delete a show 3. list coming shows");
        int choice = scanner.nextInt();
        switch(choice) {
        case 1:
        	addShow();
        	break;
        case 2:
        	deleteShow();
        	break;
        }
	}
	public static void addShow() {
		listMovie();
		int movieId = scanner.nextInt();
		Movie movie = Movie.getOne(movieId);
		listCineplex();
		int cineplexId = scanner.nextInt();
		listCinema(cineplexId);
		int cinemaId = scanner.nextInt();
		Cinema cinema = Cinema.getOne(cinemaId);
		System.out.print("Input Month (1 ~ 12): ");
		int month = scanner.nextInt();
	}
	public static void deleteShow() {
		
	}
	public static void listMovie() {
		List<Movie> movieList = ListingController.getMovieList();
		for (Movie movie : movieList) {
			System.out.println("" + movie.getId() + " " + movie.getName());
		}
	}
	public static void listCineplex() {
		List<Cineplex> cineplexList = ListingController.getCineplexList();
		for (Cineplex cineplex : cineplexList) {
			System.out.println("" + cineplex.getId() + " " + cineplex.getName());
		}
	}
	public static void listCinema(int cineplexId) {
		List<Cinema> cinemaList = ListingController.getCinemaByCineplex(cineplexId);
		for (Cinema cinema : cinemaList) {
			System.out.println("" + cinema.getId() + " " + cinema.getCinemaCode());
		}
	}
}
