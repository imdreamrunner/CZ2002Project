package views;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GeneralInterface {
	Scanner scanner;
	public GeneralInterface() {
		scanner = new Scanner(System.in);
	}
	public void display(String toDisplay) {
		System.out.println(toDisplay);
	}
	public void display(String[] toDisplay) {
		for (String str : toDisplay) {
			System.out.println(str);
		}
	}
	public int inputInteger(String title) {
		while (true) {
			try {
				System.out.print(">> " + title + " (number): ");
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			System.out.println("   ERROR: Please enter a valid input.");
		}
	}
	public int inputInteger(String title, int lower, int upper) {
		while (true) {
			try {
				System.out.print(">> " + title + " (" + lower + "~" + upper + "): ");
				int input =scanner.nextInt();
				if (input >= lower & input <= upper) {
					return input;
				}
			} catch (InputMismatchException e) {
				scanner.nextLine();
			}
			System.out.println("   ERROR: Please enter a valid input.");
		}
	}
	public String inputString(String title) {
		System.out.print(">> " + title + ": ");
		return scanner.nextLine();
	}
	public Date inputDate(String title) {
		System.out.print(">> [[" + title + "]] ");
		while (true) {
			int year = inputInteger("year");
			int month = inputInteger("month", 1, 12);
			int day = inputInteger("day", 1, 31);
			break;
		}
		return new Date();
	}
}
