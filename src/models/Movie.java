package models;

import utils.Model;

public class Movie extends Model {
	private String name;
	private String type;
	private String rating;
	private int status;
	public void loadData() {
		name = get("name").getString();
		type = get("type").getString();
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
	public String getType() {
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
	public void setType(String theType) {
		type = theType;
	}
	public void setRating(String theRating) {
		rating = theRating;
	}
	public void setStatus(int theStatus) {
		status = theStatus;
	}
}
