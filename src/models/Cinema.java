package models;

import java.util.List;

import utils.Model;

public class Cinema extends Model {
	private String cinemaCode;
	private Cineplex cineplex;
	private String seatPlan;
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
	public static List<Cinema> getAllByCineplex(Cineplex cineplex) {
		return null;
	}
}
