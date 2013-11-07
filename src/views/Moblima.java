package views;
import java.util.Scanner;

public class Moblima {

    public static void main(String[] args) {
        /////welcome message
        Scanner scanner = new Scanner(System.in);
        displayMenu();
        int choice;
        choice = scanner.nextInt();
        while (choice<3) {
            if (choice == 1) CustomerInterface.main();
            else if (choice == 2) ManagerInterface.main();
            else break;
            displayMenu();
            choice = scanner.nextInt();
        }
        System.out.printf("Thank you for using MOBLIMA");
    }

    public static void displayMenu() {
        System.out.printf("***** Welcome To MOBLIMA *****\n");
        System.out.print("Please select your entry: \n" +
                "1. Customer Entry \n" +
                "2. Staff Entry \n" +
                "3. Quit \n" +
                "Please input your choice: ");
    }
}

