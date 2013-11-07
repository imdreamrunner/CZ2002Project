package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Show;
import utils.Controller;

public class ShowManagingController extends Controller {

	public Scanner scanner;
	public void run() {
		scanner = new Scanner(System.in);
        System.out.println("1. add a show 2. list coming shows");
        int choice = scanner.nextInt();
        switch(choice) {
        case 1:
        	addShow();
        	break;
        case 2:
        	listShow();
        	break;
        }
	}
	public void addShow() {
		listMovie();
		int movieId = scanner.nextInt();
		Movie movie = Movie.getOne(movieId);
		listCineplex();
		int cineplexId = scanner.nextInt();
		listCinema(cineplexId);
		int cinemaId = scanner.nextInt();
		Cinema cinema = Cinema.getOne(cinemaId);
		System.out.print("Input Year (eg 2013): ");
		int year = scanner.nextInt();
		System.out.print("Input Month (1 ~ 12): ");
		int month = scanner.nextInt();
		System.out.print("Input Day: ");
		int day = scanner.nextInt();
		System.out.print("Input Hour: ");
		int hour = scanner.nextInt();
		System.out.print("Input Minute: ");
		int minute = scanner.nextInt();
		Calendar showTime = new GregorianCalendar(year, month, day, hour, minute, 0);
		Show show = new Show(movie, cinema, showTime.getTime());
		show.createSeats();
	}
	public void deleteShow() {
		
	}
	public void listMovie() {
		List<Movie> movieList = Movie.getAll();
		for (Movie movie : movieList) {
			System.out.println("" + movie.getId() + " " + movie.getName());
		}
	}
	public void listCineplex() {
		List<Cineplex> cineplexList = Cineplex.getAll();
		for (Cineplex cineplex : cineplexList) {
			System.out.println("" + cineplex.getId() + " " + cineplex.getName());
		}
	}
	public void listCinema(int cineplexId) {
		List<Cinema> cinemaList = Cinema.getAllByCineplex(cineplexId);
		for (Cinema cinema : cinemaList) {
			System.out.println("" + cinema.getId() + " " + cinema.getCinemaCode());
		}
	}
	public void listShow() {
		List<Show> showList = Show.getAll();
		for (Show show : showList) {
			System.out.println("" + show.getId() 
					+ " | " + show.getMovie().getName() 
					+ " | " + show.getCinema().getCineplex().getName()
					+ " " + show.getCinema().getCinemaCode() 
					+ " | " + show.getShowTime().toLocaleString());
		}
	}
    
}
