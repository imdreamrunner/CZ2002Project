package models;

import java.util.ArrayList;
import java.util.List;

import utils.Database;
import utils.Model;

public class Cinema extends Model {
	private String cinemaCode;
	private Cineplex cineplex;
	private String seatPlan;
	private int cinemaClass;
	
	public void loadData() {
		cinemaCode = get("cinemaCode").getString();
		cineplex = Cineplex.getOne(get("cineplexId").getInteger());
		seatPlan = get("seatPlan").getString();
	}
	
	public void saveData() {
		set("cinemaCode", cinemaCode);
		set("cineplexId", cineplex.getId());
		set("seatPlan", seatPlan);
	}
	
	public String getCinemaCode() {
		return cinemaCode;
	}
	
	public Cineplex getCineplex() {
		return cineplex;
	}
	public String getSeatPlan() {
		return seatPlan;
	}
	public int getCinemaClass() {
		return cinemaClass;
	}
	public void setCinemaCode(String theCinemaCode) {
		cinemaCode = theCinemaCode;
	}
	public void setCineplex(Cineplex theCineplex) {
		cineplex = theCineplex;
	}
	public void setCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		setCineplex(cineplex);
	}
	public void setSeatPlan(String theSeatPlan) {
		seatPlan = theSeatPlan;
	}
	public void setCinemaClass(int theCinemaClass) {
		cinemaClass = theCinemaClass;
	}
	@SuppressWarnings("unchecked")
	public static List<Cinema> getAll() {
		return Database.getAll(Cinema.class);
	}
	public static Cinema getOne(int id) {
		List<Cinema> cinemas = getAll();
		for (Cinema cinema : cinemas) {
			if (cinema.getId() == id)
				return cinema;
		}
		return null;
	}
	public static List<Cinema> getAllByCineplex(Cineplex cineplex) {
		List<Cinema> cinemas = getAll();
		List<Cinema> selected = new ArrayList<Cinema>();
		for (Cinema cinema : cinemas) {
			if (cinema.getCineplex().equals(cineplex)) {
				selected.add(cinema);
			}
		}
		return selected;
	}
	public static List<Cinema> getAllByCineplex(int cineplexId) {
		Cineplex cineplex = Cineplex.getOne(cineplexId);
		return getAllByCineplex(cineplex);
	}
}
