package views;

import java.util.List;
import java.util.Scanner;

import controllers.SystemController;
import models.Holiday;

public class HolidayManagingInterface {
	
	public static void main() {
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
        	success = SystemController.addHoliday(month,day);
            if (success) System.out.println("Price added!");
            else System.out.println("Error!");
        } else if (operation == 2) {
        	System.out.print("Enter ID: ");
        	int newID = scanner.nextInt();
        	success = SystemController.deletePrice(newID);
        	if (success) System.out.println("Price deleted!");
            else System.out.println("Error!");
        }
        
        public static void displayHolidayList() {
        	System.out.println("*****HOLIDAY LIST*****");
        	List<Holiday> holidayList = SystemController.getHolidayList();
        	for (Holiday holiday : holidayList) {
        		System.out.println(holiday.getId() + " " + holiday.getMonth() + " " + holiday.getDay());
        	}
        	System.out.println("*****END OF HOLIDAY LIST*****");
        }

}
