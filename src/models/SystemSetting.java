package models;

import java.util.List;

import utils.Database;
import utils.Model;

public class SystemSetting extends Model {
	private String key;
	private  String value;
	public void loadData() {
		key = get("key").getString();
		value = get("value").getString();
	}
	public void saveData() {
		set("key", key);
		set("value", value);
	}
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public void setKey(String theKey) {
		key = theKey;
	}
	public void setValue(String theValue) {
		value = theValue;
	}
	@SuppressWarnings("unchecked")
	public static List<SystemSetting> getAll() {
		return Database.getAll(SystemSetting.class);
	}
	public static SystemSetting getOne(int id) {
		List<SystemSetting> settings = getAll();
		for (SystemSetting setting : settings) {
			if (setting.getId() == id) {
				return setting;
			}
		}
		return null;
	}
	public static SystemSetting getOneByKey(String key) {
		List<SystemSetting> settings = getAll();
		for (SystemSetting setting : settings) {
			if (setting.getKey().equals(key)) {
				return setting;
			}
		}
		return null;
	}
}
