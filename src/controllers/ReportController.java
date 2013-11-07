package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Cineplex;
import models.Movie;
import models.Ticket;
import utils.Controller;

public class ReportController extends Controller {

	@Override
	public void run() {
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
				ListByMovie(year,month,day);
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

	public void displayMenu() {
        System.out.printf("*****REPORT CENTER*****\n");
        System.out.print("Input your option: \n" +
                "1. List by movie \n" +
                "2. List by cineplex \n" +
                "3. List by day \n" +
                "4. List by month \n" + 
                "5. Return to previous page \n" +
                "Please input your choice: ");		
	}
	
	public void displayRevenue(String name, int revenue) {
		System.out.println(name + " : " + revenue);
	}
	
	public void displayTotalRevenue(int revenue) {
		System.out.println("Total Revenue: " + revenue);
	}
	

	public static final int[] NumberOfDay = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	public void ListByMovie(int year, int month, int day) {
		List<Ticket> ticketList = Ticket.getAllByDay(year,month,day);
		List<Movie> movieList = new ArrayList<Movie>();
		for (Ticket ticket : ticketList) {
			if (!movieList.contains(ticket.getMovie())) {
				movieList.add(ticket.getMovie());
			}
		}
		int totalRevenue = 0;
		for (Movie movie: movieList) {
			int revenue = 0;
			for (Ticket ticket : ticketList) {
				if (ticket.getMovie().equals(movie)) {
					revenue += ticket.getPrice();
				}
			}
			displayRevenue(movie.getName(),revenue);
			totalRevenue += revenue;
		}
		displayTotalRevenue(totalRevenue);
	}
	
	public void ListByCineplexes(int year, int month, int day) {
		List<Ticket> ticketList = Ticket.getAllByDay(year,month,day);
		List<Cineplex> cineplexList = new ArrayList<Cineplex>();
		for (Ticket ticket : ticketList) {
			if (!cineplexList.contains(ticket.getCineplex())) {
				cineplexList.add(ticket.getCineplex());
			}
		}
		int totalRevenue = 0;
		for (Cineplex cineplex: cineplexList) {
			int revenue = 0;
			for (Ticket ticket : ticketList) {
				if (ticket.getCineplex().equals(cineplex)) {
					revenue += ticket.getPrice();
				}
				
			}
			displayRevenue(cineplex.getName(),revenue);
			totalRevenue += revenue;
		}
		displayTotalRevenue(totalRevenue);
	}
	
	
	public void ListByDay(int year, int month) {
		for (int day=1; day<NumberOfDay[month-1]; day++) {
			//display the day frist
			ListByCineplexes(year,month,day);
		}
	}
}
