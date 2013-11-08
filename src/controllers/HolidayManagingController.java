package controllers;

import java.util.List;

import models.Holiday;
import models.TicketPrice;
import utils.Controller;

public class HolidayManagingController extends Controller {

	@Override
	public void run() {
		displayHolidayList();
		gi.display("1. add a new holiday; 2. delete a holiday");
        boolean success;
        int operation = gi.inputInteger("operation");
        if (operation == 1) {
        	gi.display("Enter Month: ");
        	int month = gi.inputInteger("month");
        	gi.display("Enter Day: ");
        	int day = gi.inputInteger("day");
        	success = addHoliday(month,day);
            if (success) 
            	gi.display("Price added!");
            else 
            	gi.display("Error!");
        } else if (operation == 2) {
        	gi.display("Enter ID: ");
        	int id = gi.inputInteger("id");
        	success = deleteHoliday(id);
        	if (success) 
        		gi.display("Holiday deleted!");
            else 
            	gi.display("Error!");
        }
	}
	
	 public void displayHolidayList() {
        gi.display("*****HOLIDAY LIST*****");
        List<Holiday> holidayList = getHolidayList();
        for (Holiday holiday : holidayList) {
        	gi.display(holiday.getId() + " Month = " + holiday.getMonth() + " Day = " + holiday.getDay());
        }
        gi.display("*****END OF HOLIDAY LIST*****");
    }
	 
    public List<Holiday> getHolidayList() {
    	List<Holiday> holidayList = Holiday.getAll();
    	return holidayList;
    }
    
    public boolean addHoliday(int month, int day) {
    	//add some restriction here
    	Holiday holiday = new Holiday();
    	holiday.setMonth(month);
    	holiday.setDay(day);
    	holiday.save();
    	return true;
    }
    
    public boolean deleteHoliday(int id) {
    	Holiday holiday = Holiday.getOne(id);
    	if (holiday == null) return false;
    	holiday.delete();
    	return true;
    }
}
