package models;

import java.util.List;

import utils.Database;
import utils.Model;

public class Holiday extends Model {
	private int month;
	private int day;
	public void loadData() {
		month = get("month").getInteger();
		day = get("day").getInteger();
	}
	public void saveData() {
		set("month", month);
		set("day", day);
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public void setMonth(int theMonth) {
		month = theMonth;
	}
	public void setDay(int theDay) {
		day = theDay;
	}
	@SuppressWarnings("unchecked")
	public static List<Holiday> getAll() {
		return Database.getAll(Holiday.class);
	}
	public static Holiday getOne(int id) {
		List<Holiday> holidays = getAll();
		for (Holiday holiday : holidays) {
			if (holiday.getId() == id) {
				return holiday;
			}
		}
		return null;
	}
	public static boolean isHoliday(int month, int day) {
		List<Holiday> holidays = getAll();
		for (Holiday holiday : holidays) {
			if (holiday.getMonth() == month && holiday.getDay() == day) {
				return true;
			}
		}
		return false;
	}
}
