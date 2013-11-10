package controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import models.Cinema;
import models.Cineplex;
import models.Movie;
import models.Show;
import utils.Controller;

public class ShowManagingController extends Controller {

	public void run() {
        System.out.println("1. add a show 2. list coming shows");
        int choice = gi.inputInteger("choice", 1, 2);
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
		int movieId = gi.inputInteger("movie id");
		Movie movie = Movie.getOne(movieId);
		listCineplex();
		int cineplexId = gi.inputInteger("sineplex id");
		listCinema(cineplexId);
		int cinemaId = gi.inputInteger("cinema id");
		Cinema cinema = Cinema.getOne(cinemaId);
		int year = gi.inputInteger("year", 2000, 2099);
		int month =  gi.inputInteger("month", 1, 12);
		int day = gi.inputInteger("day", 1, 31);
		int hour = gi.inputInteger("hour", 1, 23);
		int minute = gi.inputInteger("minute", 1, 59);
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
