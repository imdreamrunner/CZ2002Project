package views;

import java.util.List;
import java.util.Scanner;

import controllers.SystemController;
import models.Cineplex;
import models.Movie;

public class ShowManagingInterface {
	public static void main(String[] args) {
		main();
	}
	public static void main() {
		Scanner scanner = new Scanner(System.in);
        System.out.println("1. add a show 2. delete a show 3. list coming shows");
        int choice = scanner.nextInt();
        switch(choice) {
        case 1:
        	addShow();
        	break;
        case 2:
        	deleteShow();
        	break;
        }
	}
	public static void addShow() {
		
	}
	public static void deleteShow() {
		
	}
	public void listMovie() {
		List<Movie> movieList = SystemController.getMovieList();
		for (Movie movie : movieList) {
			System.out.println("" + movie.getId() + " " + movie.getName());
		}
	}
}
