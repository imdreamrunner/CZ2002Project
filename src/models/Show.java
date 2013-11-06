package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import utils.Database;
import utils.Model;

public class Show extends Model {
	private Movie movie;
	private Cinema cinema;
	private Date showTime;
	public void loadData() {
		setMovie(get("movieId").getInteger());
		setCinema(get("cinemaId").getInteger());
		showTime = get("showTime").getData();
	}
	public void saveData() {
		set("movieId", movie.getId());
		set("cinemaId", cinema.getId());
		set("showTime", showTime);
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
}
