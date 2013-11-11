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
		switch (operation) {
			case 1:
				int year, month, day;
				gi.display("Input the year:");
				year = gi.inputInteger("year");
				gi.display("Input the month:");
				month = gi.inputInteger("month");
				gi.display("Input the day:");
				day = gi.inputInteger("day");
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
		gi.display("Total Revenue: " + revenue);
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
