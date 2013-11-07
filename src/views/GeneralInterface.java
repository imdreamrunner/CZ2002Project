package views;

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
		System.out.print("Please input an integer for " + title + ":");
		return scanner.nextInt();
	}
}
