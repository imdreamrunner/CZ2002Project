package controllers;

import java.util.List;
import java.util.Scanner;

import models.Holiday;
import models.TicketPrice;
import utils.Controller;

public class HolidayManagingController extends Controller {

	@Override
	public void run() {
		displayHolidayList();
		Scanner scanner = new Scanner(System.in);
        System.out.println("1. add a new holiday; 2. delete a holiday");
        boolean success;
        int operation = scanner.nextInt();
        if (operation == 1) {
        	System.out.print("Enter Month: ");
        	int month = scanner.nextInt();
        	System.out.print("Enter Day: ");
        	int day = scanner.nextInt();
        	success = addHoliday(month,day);
            if (success) System.out.println("Price added!");
            else System.out.println("Error!");
        } else if (operation == 2) {
        	System.out.print("Enter ID: ");
        	int id = scanner.nextInt();
        	success = deleteHoliday(id);
        	if (success) System.out.println("Holiday deleted!");
            else System.out.println("Error!");
        }
	}
	
	 public void displayHolidayList() {
        System.out.println("*****HOLIDAY LIST*****");
        List<Holiday> holidayList = getHolidayList();
        for (Holiday holiday : holidayList) {
        	System.out.println(holiday.getId() + " Month = " + holiday.getMonth() + " Day = " + holiday.getDay());
        }
        System.out.println("*****END OF HOLIDAY LIST*****");
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
