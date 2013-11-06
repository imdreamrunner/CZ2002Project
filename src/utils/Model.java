package utils;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import utils.Database.Value;

public abstract class Model {
	private int id = 0;
	private Map<String, Value> data = new HashMap<String, Value>();
	public abstract void loadData();
	public abstract void saveData();
	public void setStringData(String str) {
		String[] pairs = str.split("&");
		for (String pair : pairs) {
			String[] keyValue = pair.split("=");
			if (keyValue[0].equals("id")) {
				id = Integer.parseInt(keyValue[1]);
			} else {
				data.put(keyValue[0], new Value(keyValue[1]));
			}
		}
		loadData();
	}
	private String getStrPair(String key, Value value) {
		return key + "=" + value.getString();
	}
	protected String getStringData() {
		String strData = "id=" + getId();
		for (Entry<String, Value> entry : data.entrySet()) {
			strData += "&" + getStrPair(entry.getKey(), entry.getValue());
		}
		return strData;
	}
	public int getId() {
		return id;
	}
	protected void setId(int theId) {
		id = theId;
	}
	protected void set(String key, Integer value) {
		set(key, new Value(value.toString()));
	}
	protected void set(String key, String value) {
		set(key, new Value(value));
	}
	protected void set(String key, Date value) {
		set(key, new Value(((Long)value.getTime()).toString()));
	}
	protected void set(String key, boolean value) {
		Integer number = value ? 1 : 0;
		set(key, new Value(number.toString()));
	}
	protected void set(String key, Value value) {
		data.put(key, value);
	}
	protected Value get(String key) {
		return data.get(key);
	}
	public void save() {
		saveData();
		Database.save(getClass(), this);
	}
	public void delete() {
		Database.delete(getClass(), this);
	}
	@Override
	public boolean equals(Object model) {
		if (model instanceof Model) {
			return id == ((Model)model).getId();
		} else {
			return false;
		}
	}
}
