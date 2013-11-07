package views;

import java.util.Scanner;

import models.Movie;
import controllers.ReportController;

public class ReportInterface {
	
	public static void main() {
		displayMenu();
		Scanner scanner = new Scanner(System.in);
		int operation = scanner.nextInt();
		switch (operation) {
			case 1:
				int year,month,day;
				System.out.print("Input the year:");
				year = scanner.nextInt();
				System.out.print("Input the month:");
				month = scanner.nextInt();
				System.out.print("Input the day:");
				day = scanner.nextInt();
				ReportController.ListByMovie(year,month,day);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
		}
	}
	
	public static void displayMenu() {
        System.out.printf("*****REPORT CENTER*****\n");
        System.out.print("Input your option: \n" +
                "1. List by movie \n" +
                "2. List by cineplex \n" +
                "3. List by day \n" +
                "4. List by month \n" + 
                "5. Return to previous page \n" +
                "Please input your choice: ");		
	}
	
	public static void displayRevenue(String name, int revenue) {
		System.out.println(name + " : " + revenue);
	}
	
	public static void displayTotalRevenue(int revenue) {
		System.out.println("Total Revenue: " + revenue);
	}


}
