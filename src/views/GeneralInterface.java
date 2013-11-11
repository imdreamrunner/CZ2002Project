package views;

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
				int num = scanner.nextInt();
				scanner.nextLine();
				return num;
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
				scanner.nextLine();
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
}
