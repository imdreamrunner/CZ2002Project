package models;

import java.io.FileNotFoundException;
import java.util.List;

import utils.Database;
import utils.Model;

public class Staff extends Model {
	private String username;
	private String password;
	public Staff() {
	}
	public void loadData() {
		username = get("username").getString();
		password = get("password").getString();
	}
	public void saveData() {
		set("username", username);
		set("password", password);
	}
	public void setUsername(String theUsername) {
		username = theUsername;
	}
	public void setPassword(String thePassword) {
		password = thePassword;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	public static List<Staff> getAllStaff() throws FileNotFoundException {
		List<Staff> staffs = Database.getAll(Staff.class);
		return staffs;
	}

	public static Staff getStaffByUsername(String username) throws FileNotFoundException {
		List<Staff> staffs = getAllStaff();
		for (Staff staff : staffs) {
			if (staff.getUsername().equals(username)) {
				return staff;
			}
		}
		return null;
	}
}
