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
		List<Movie> movieList = getMovieList();
		for (Movie movie : movieList) {
			System.out.println("" + movie.getId() + " " + movie.getName());
		}
	}
	public void listCineplex() {
		List<Cineplex> cineplexList = getCineplexList();
		for (Cineplex cineplex : cineplexList) {
			System.out.println("" + cineplex.getId() + " " + cineplex.getName());
		}
	}
	public void listCinema(int cineplexId) {
		List<Cinema> cinemaList = getCinemaByCineplex(cineplexId);
		for (Cinema cinema : cinemaList) {
			System.out.println("" + cinema.getId() + " " + cinema.getCinemaCode());
		}
	}
	public void listShow() {
		List<Show> showList = getShowList();
		for (Show show : showList) {
			System.out.println("" + show.getId() 
					+ " | " + show.getMovie().getName() 
					+ " | " + show.getCinema().getCineplex().getName()
					+ " " + show.getCinema().getCinemaCode() 
					+ " | " + show.getShowTime().toLocaleString());
		}
	}
	
	public List<Cineplex> getCineplexList() {
		List<Cineplex> cineplexList = Cineplex.getAll();
		return cineplexList;
	}
	
	public List<Movie> getMovieByCineplex(Cineplex cineplex) {
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
	
	public List<Movie> getMovieByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getMovieByCineplex(cineplex);
	}
	
	public List<Cinema> getCinemaByCineplex(Cineplex cineplex) {
		List<Cinema> cinemaList = Cinema.getAll();
		List<Cinema> resultList = new ArrayList<Cinema>();
		for (Cinema cinema : cinemaList) {
			if (cinema.getCineplex().equals(cineplex)) {
				resultList.add(cinema);
			}
		}
		return resultList;
	}
	
	public List<Cinema> getCinemaByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getCinemaByCineplex(cineplex);
	}
	
    public List<Movie> getMovieList() {
    	List<Movie> movieList = Movie.getAll();
    	return movieList;
    }
    
    public List<Show> getShowList() {
    	return Show.getAll();
    }
    
}
