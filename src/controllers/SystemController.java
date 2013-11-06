package controllers;

import java.util.List;

import models.Movie;
import models.TicketPrice;
import models.Holiday;
import models.Customer;

public class SystemController {

    public static boolean addMovie(String newMovieName, String newMovieType, int newMovieStatus) {
    	Movie newMovie = new Movie();
    	newMovie.setName(newMovieName);
    	newMovie.setType(newMovieType);
    	newMovie.setStatus(newMovieStatus);
    	newMovie.save();
        return true;  //for successful adding
    }
    
    public static boolean editMovie(int id, String key, String value) {
    	Movie movie = Movie.getOne(id);
    	if (key.equals("Name")) {
    		movie.setName(value);
    	} else if (key.equals("Type")) {
    		movie.setType(value);
    	} else if (key.equals("Status")) {
    		try {
    			int newStatus = Integer.parseInt(value);
    			movie.setStatus(newStatus);
    		} catch (Exception e) {
    			return false;
    		}
    	} else return false;
    	movie.save();
        return true;
    }
    
    public static boolean editPrice(int id, int newPrice) {
    	TicketPrice tp = TicketPrice.getOne(id);
    	if (tp==null) return false;
    	tp.setValue(newPrice);
    	tp.save();
        return true;
    }
    
    public static boolean addPrice(String key, int value) {
    	//add some restriction
    	TicketPrice tp = new TicketPrice();
    	tp.setKey(key);
    	tp.setValue(value);
    	tp.save();
    	return true;
    }
    
    public static boolean deletePrice(int id) {
    	TicketPrice tp = TicketPrice.getOne(id);
    	if (tp==null) return false;
    	tp.delete();
    	return true;
    }
    
    public static boolean addHoliday(int month, int day) {
    	//add some restriction here
    	Holiday holiday = new Holiday();
    	holiday.setMonth(month);
    	holiday.setDay(day);
    	holiday.save();
    	return true;
    }
    
    public static boolean deleteHoliday(int id) {
    	Holiday holiday = Holiday.getOne(id);
    	if (holiday == null) return false;
    	holiday.delete();
    	return true;
    }
    public static List<Holiday> getHolidayList() {
    	List<Holiday> holidayList = Holiday.getAll();
    	return holidayList;
    }
    
    public static void  CreateCustomer(String name, int mobile, String email){
    Customer customer = new Customer();
    customer.setMobile(mobile);
	customer.setName(name);
	customer.setEmail(email);
	customer.save();
    }
}
