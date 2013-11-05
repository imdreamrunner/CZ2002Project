package models;

import utils.Model;

public class Cineplex extends Model {
	private String name;
	private String location;
	public void loadData() {
		name = get("name").getString();
		location = get("location").getString();
	}
	public void saveData() {
		set("name", name);
		set("location", location);
	}

}
