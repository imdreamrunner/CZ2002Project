package models;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import utils.Database;
import utils.Model;

public class Show extends Model {
	private Movie movie;
	private Cinema cinema;
	private Date showTime;
	private boolean deleted;
	
	public void loadData() {
		setMovie(get("movieId").getInteger());
		setCinema(get("cinemaId").getInteger());
		showTime = get("showTime").getData();
		deleted = get("deleted").getBoolean();
	}
	
	public void saveData() {
		set("movieId", movie.getId());
		set("cinemaId", cinema.getId());
		set("showTime", showTime);
		set("deleted", deleted);
	}
	public Show() {
	}
	public Show(Movie movie, Cinema cinema, Date date) {
		setMovie(movie);
		setCinema(cinema);
		setShowTime(date);
		save();
	}
	public void createSeats() {
		save();
		String seatPlan = cinema.getSeatPlan();
		String[] seatNames = seatPlan.split(" ");
		for (String seatName : seatNames) {
			Seat seat = new Seat();
			seat.setName(seatName);
			seat.setShow(this);
			seat.setStatus(true);
			seat.save();
		}
	}
	public Movie getMovie() {
		return movie;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public Date getShowTime() {
		return showTime;
	}
	public boolean getDeleted() {
		return deleted;
	}
	public List<Seat> getSeats() {
		return Seat.getAllByShow(this);
	}
	public List<Seat> getAvailableSeats() {
		return Seat.getAvailableByShow(this);
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public void setMovie(int movieId) {
		movie = Movie.getOne(movieId);
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public void setCinema(int cinemaId) {
		cinema = Cinema.getOne(cinemaId);
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public Seat getSeat(String name) {
		List<Seat> seats = getSeats();
		for (Seat seat : seats) {
			if (seat.getName().equals(name))
				return seat;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static List<Show> getAll() {
		return Database.getAll(Show.class);
	}
	public static List<Show> getAllByMovie(Movie movie) {
		List<Show> shows = getAll();
		List<Show> selected = new ArrayList<Show>();
		for (Show show : shows) {
			if (show.getMovie().equals(movie)) {
				selected.add(show);
			}
		}
		return selected;
	}
	public static Show getOne(int id) {
		List<Show> shows = getAll();
		for (Show show : shows) {
			if (show.getId() == id) {
				return show;
			}
		}
		return null;
	}
	public static List<Show> getAllByCineplextAndMovie(Cineplex cineplex, Movie movie) {
    	List<Show> showList = getAll();
    	List<Show> resultList = new ArrayList<Show>();
    	for (Show show : showList) {
    		if (show.getMovie().equals(movie) && show.getCinema().getCineplex().equals(cineplex)) {
    			resultList.add(show);
    		}
    	}
    	return resultList;
    }
	public static List<Show> getAllByCineplextAndMovie(int cineplexId, int movieId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		Movie movie = Movie.getOne(movieId);
		return getAllByCineplextAndMovie(cineplex, movie);
	}
}
