package models;

import java.util.ArrayList;
import java.util.List;

import utils.Database;
import utils.Model;

public class Movie extends Model {
	private String name;
	private int type;
	private String rating;
	private int status;
	public void loadData() {
		name = get("name").getString();
		type = get("type").getInteger();
		rating = get("rating").getString();
		status = get("status").getInteger();
	}
	public void saveData() {
		set("name", name);
		set("type", type);
		set("rating", rating);
		set("status", status);
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public String getRating() {
		return rating;
	}
	public int getStatus() {
		return status;
	}
	public void setName(String theName) {
		name = theName;
	}
	public void setType(int theType) {
		type = theType;
	}
	public void setRating(String theRating) {
		rating = theRating;
	}
	public void setStatus(int theStatus) {
		status = theStatus;
	}
	@SuppressWarnings("unchecked")
	public static List<Movie> getAll() {
		return Database.getAll(Movie.class);
	}
	public static Movie getOne(int id) {
		List<Movie> movies = getAll();
		for (Movie movie : movies) {
			if (movie.getId() == id)
				return movie;
		}
		return null;
	}
	public static List<Movie> getAllByCineplex(Cineplex cineplex) {
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
	public static List<Movie> getMovieByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getAllByCineplex(cineplex);
	}
}
