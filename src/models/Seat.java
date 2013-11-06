package models;

import java.util.ArrayList;
import java.util.List;

import utils.Database;
import utils.Model;

public class Seat extends Model {
	private String name;
	private boolean status;
	private Show show;
	public void loadData() {
		name = get("name").getString();
		status = get("status").getBoolean();
		setShow(get("showId").getInteger());
	}
	@Override
	public void saveData() {
		set("name", name);
		set("status", status);
		set("showId", show.getId());
	}
	public String getName() {
		return name;
	}
	public boolean getStatus() {
		return status;
	}
	public Show getShow() {
		return show;
	}
	public void setName(String theName) {
		name = theName;
	}
	public void setStatus(boolean theStatus) {
		status = theStatus;
	}
	public void setShow(Show theShow) {
		show = theShow;
	}
	public void setShow(int showId) {
		Show theShow = Show.getOne(showId);
		setShow(theShow);
	}
	@SuppressWarnings("unchecked")
	public static List<Seat> getAll() {
		return Database.getAll(Seat.class);
	}
	public static List<Seat> getAllByShow(Show show) {
		List<Seat> seats = getAll();
		List<Seat> selected = new ArrayList<Seat>();
		for (Seat seat : seats) {
			if (seat.getShow().equals(show)) {
				selected.add(seat);
			}
		}
		return selected;
	}
	public static Seat getOne(int id) {
		List<Seat> seats = getAll();
		for (Seat seat : seats) {
			if (seat.getId() == id) {
				return seat;
			}
		}
		return null;
	}
}
