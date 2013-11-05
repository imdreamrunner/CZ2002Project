package models;

import java.sql.Date;

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
}
