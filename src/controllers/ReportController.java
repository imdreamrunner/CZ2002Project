package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Cineplex;
import models.Movie;
import models.Ticket;
import utils.Controller;

public class ReportController extends Controller {

	@Override
	public void run() {
		gi.display(menu);
		int operation = gi.inputInteger("operation");
		int year, month, day;
		switch (operation) {
			case 1:
				year = gi.inputInteger("year");
				month = gi.inputInteger("month", 1, 12);
				day = gi.inputInteger("day", 1, 31);
				ListByMovie(year,month,day);
				break;
			case 2:
				year = gi.inputInteger("year");
				month = gi.inputInteger("month", 1, 12);
				day = gi.inputInteger("day", 1, 31);
				ListByCineplexes(year, month, day);
				break;
			case 3:
				year = gi.inputInteger("year");
				month = gi.inputInteger("month", 1, 12);
				ListByDay(year, month);
				break;
			case 4:
				year = gi.inputInteger("year");
				ListByMonth(year);
				break;
			case 5:
				break;
		}
	}

	String[] menu = {
			"*****REPORT CENTER****",
			"Input your option:", 
			"1. List by movie",
			"2. List by cineplex",
			"3. List by day",
			"4. List by month",
			"5. Return to previous page"	
	};
	
	public void displayRevenue(String name, int revenue) {
		gi.display(name + " : " + revenue);
	}
	
	public void displayTotalRevenue(int revenue) {
		gi.display("Total Revenue: $" + revenue/100.0);
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
			//display the day first
			gi.display("day " + day);
			ListByCineplexes(year,month,day);
		}
	}
	
	public void ListByMonth(int year) {
		for (int month = 1; month <= 12; month++) {
			gi.display("month " + month);
			List<Ticket> ticketList = Ticket.getAllByMonth(year,month);
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
	}
}
