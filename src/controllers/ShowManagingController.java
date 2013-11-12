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
        System.out.println("1. add a show 2. list coming shows 3. update a show 4. remove a show");
        int choice = gi.inputInteger("choice", 1, 3);
        switch(choice) {
        case 1:
        	addShow();
        	break;
        case 2:
        	listShow();
        	break;
        case 3:
        	listShow();
        	updateShow();
        	break;
        case 4:
        	listShow();
        	removeShow();
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
		int hour = gi.inputInteger("hour", 0, 23);
		int minute = gi.inputInteger("minute", 0, 59);
		Calendar showTime = new GregorianCalendar(year, month-1, day, hour, minute, 0);
		Show show = new Show(movie, cinema, showTime.getTime());
		show.createSeats();
	}
	public void deleteShow() {
		
	}
	public void listMovie() {
		List<Movie> movieList = Movie.getAll();
		for (Movie movie : movieList) {
			if (movie.getStatus() < 3)
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
	public void updateShow() {
		int showId = gi.inputInteger("show id");
		int year = gi.inputInteger("year", 2000, 2099);
		int month =  gi.inputInteger("month", 1, 12);
		int day = gi.inputInteger("day", 1, 31);
		int hour = gi.inputInteger("hour", 0, 23);
		int minute = gi.inputInteger("minute", 0, 59);
		Show show = Show.getOne(showId);
		if (show == null) {
			gi.display("No such show");
			return;
		}
		Calendar showTime = new GregorianCalendar(year, month-1, day, hour, minute, 0);
		show.setShowTime(showTime.getTime());
		show.save();
		gi.display("changes saved");
	}
	public void removeShow() {
		int showId = gi.inputInteger("show id");
		Show show = Show.getOne(showId);
		if (show == null) {
			gi.display("No such show");
			return;
		}
		show.setDeleted(true);
		show.save();
		gi.display("changes saved");
	}
}
