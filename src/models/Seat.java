package models;

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
}
