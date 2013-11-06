package views;


import java.util.Scanner;

public class CustomerInterface {

    public static void main() {
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();
        while (choice < 3) {
            switch (choice) {
                case 1:
                    //BookingInterface.main();
                    break;
                case 2:
                    HistoryInterface.main();
                    break;
                case 3:
                    return;
                case 4:
                    //how to quit directly?
            }
            displayMenu();
            choice = scanner.nextInt();
        }
    }

    public static void displayMenu() {
        System.out.println("***** Customer Page *****");
        System.out.printf("1. Make a booking \n" +
                "2. View booking history \n" +
                "3. Go back to previous page \n" +
                "4. Quit \n" +
                "Please input your choice: ");
    }
}
