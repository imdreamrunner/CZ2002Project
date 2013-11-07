package test;

import cui.GeneralInterface;

public class TestGeneralInterface {
	public static void main(String[] args) {
		GeneralInterface gi = new GeneralInterface();
		gi.display("Welcome to Lalala");
		String[] stringList = {
				"Line A",
				"Line B"
		};
		gi.display(stringList);
		// int month = gi.inputInteger("month", 1, 12);
		String name = gi.inputString("name");
		System.out.println(name);
	}
}
