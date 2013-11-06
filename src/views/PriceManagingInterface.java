package views;

import java.util.List;
import java.util.Scanner;

import controllers.SystemController;
import models.TicketPrice;

public class PriceManagingInterface {
	
	public static void main() {
		displayPriceList();
		Scanner scanner = new Scanner(System.in);
        System.out.println("1. change a price; 2. add a new price; 3. delete a price");
        boolean success;
        int operation = scanner.nextInt();
        if (operation == 1) {
        	System.out.print("Enter ID: ");
        	int newID = scanner.nextInt();
        	System.out.print("Enter Value: ");
        	int newPrice = scanner.nextInt();
        	success = SystemController.editPrice(newID,newPrice);
            if (success) System.out.println("Price updated!");
            else System.out.println("Error!");
        } else if (operation == 2) {
        	System.out.print("Enter New Key: ");
        	String newKey = scanner.next();
        	System.out.print("Enter New Value: ");
        	int newValue = scanner.nextInt();
        	success = SystemController.addPrice(newKey,newValue);
            if (success) System.out.println("Price added!");
            else System.out.println("Error!");
        } else if (operation == 3) {
        	System.out.print("Enter ID: ");
        	int newID = scanner.nextInt();
        	success = SystemController.deletePrice(newID);
        	if (success) System.out.println("Price deleted!");
            else System.out.println("Error!");
        }
	}
	
    public static void displayPriceList() {
    	System.out.println("*****PRICE LIST*****");
    	List<TicketPrice> priceList = SystemController.getPriceList();
    	for (TicketPrice tp : priceList) {
    		System.out.println(tp.getId() + " " + tp.getKey() + " " + (tp.getValue()/100) + "." + (tp.getValue()%100));
    	}
    	System.out.println("*****END OF PRICE LIST*****");
    }

}
