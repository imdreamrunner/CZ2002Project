package controllers;

import models.Ticket;
import models.Movie;
import models.Cineplex;
import views.ReportInterface;

import java.util.ArrayList;
import java.util.List;

public class ReportController {
	
	public static final int[] NumberOfDay = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	public static void ListByMovie(int year, int month, int day) {
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
			ReportInterface.displayRevenue(movie.getName(),revenue);
			totalRevenue += revenue;
		}
		ReportInterface.displayTotalRevenue(totalRevenue);
	}
	
	public static void ListByCineplexes(int year, int month, int day) {
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
			ReportInterface.displayRevenue(cineplex.getName(),revenue);
			totalRevenue += revenue;
		}
		ReportInterface.displayTotalRevenue(totalRevenue);
	}
	
	
	public static void ListByDay(int year, int month) {
		for (int day=1; day<NumberOfDay[month-1]; day++) {
			//display the day frist
			ListByCineplexes(year,month,day);
		}
	}
}
