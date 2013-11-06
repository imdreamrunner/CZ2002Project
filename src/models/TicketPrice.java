package models;

import java.util.List;

import utils.Database;
import utils.Model;

public class TicketPrice extends Model {
	private String key;
	private int value;
	public void loadData() {
		key = get("key").getString();
		value = get("value").getInteger();
	}
	public void saveData() {
		set("key", key);
		set("value", value);
	}
	public String getKey() {
		return key;
	}
	public int getValue() {
		return value;
	}
	public void setKey(String theKey) {
		key = theKey;
	}
	public void setValue(int theValue) {
		value = theValue;
	}
	@SuppressWarnings("unchecked")
	public static List<TicketPrice> getAll() {
		return Database.getAll(TicketPrice.class);
	}
	public static TicketPrice getOne(int id) {
		List<TicketPrice> settings = getAll();
		for (TicketPrice setting : settings) {
			if (setting.getId() == id) {
				return setting;
			}
		}
		return null;
	}
	public static TicketPrice getOneByKey(String key) {
		List<TicketPrice> settings = getAll();
		for (TicketPrice setting : settings) {
			if (setting.getKey().equals(key)) {
				return setting;
			}
		}
		return null;
	}
}
