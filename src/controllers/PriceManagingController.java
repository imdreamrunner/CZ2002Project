package controllers;

import java.util.List;
import java.util.Scanner;

import models.TicketPrice;
import utils.Controller;

public class PriceManagingController extends Controller {

	@Override
	public void run() {
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
        	success = editPrice(newID,newPrice);
            if (success) System.out.println("Price updated!");
            else System.out.println("Error!");
        } else if (operation == 2) {
        	System.out.print("Enter New Key: ");
        	String newKey = scanner.next();
        	System.out.print("Enter New Value: ");
        	int newValue = scanner.nextInt();
        	success = addPrice(newKey,newValue);
            if (success) System.out.println("Price added!");
            else System.out.println("Error!");
        } else if (operation == 3) {
        	System.out.print("Enter ID: ");
        	int newID = scanner.nextInt();
        	success = deletePrice(newID);
        	if (success) System.out.println("Price deleted!");
            else System.out.println("Error!");
        }
	}
    public void displayPriceList() {
    	System.out.println("*****PRICE LIST*****");
    	List<TicketPrice> priceList = getPriceList();
    	for (TicketPrice tp : priceList) {
    		System.out.println(tp.getId() + " " + tp.getKey() + " " + (tp.getValue()/100) + "." + (tp.getValue()%100));
    	}
    	System.out.println("*****END OF PRICE LIST*****");
    }

    public boolean editPrice(int id, int newPrice) {
    	TicketPrice tp = TicketPrice.getOne(id);
    	if (tp==null) return false;
    	tp.setValue(newPrice);
    	tp.save();
        return true;
    }
    
    public boolean addPrice(String key, int value) {
    	//add some restriction
    	TicketPrice tp = new TicketPrice();
    	tp.setKey(key);
    	tp.setValue(value);
    	tp.save();
    	return true;
    }
    
    public boolean deletePrice(int id) {
    	TicketPrice tp = TicketPrice.getOne(id);
    	if (tp==null) return false;
    	tp.delete();
    	return true;
    }
    
    public List<TicketPrice> getPriceList() {
    	List<TicketPrice> priceList = TicketPrice.getAll();
    	return priceList;
    }
}
