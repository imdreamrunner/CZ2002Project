package controllers;

import java.util.List;

import models.TicketPrice;
import utils.Controller;

public class PriceManagingController extends Controller {

	@Override
	public void run() {
		displayPriceList();
		gi.display("1. change a price 2. return");
        boolean success;
        int operation = gi.inputInteger("operation", 1, 2);
        if (operation == 1) {
        	int newID = gi.inputInteger("id");
        	int newPrice = gi.inputInteger("new price in cents");
        	success = editPrice(newID,newPrice);
            if (success) 
            	gi.display("Price updated!");
            else 
            	gi.display("Error!");
        } else if (operation == 2) {
        	return;
        } else if (operation == 3) {
        	gi.display("Enter New Key: ");
        	String newKey = gi.inputString("newKey");
        	gi.display("Enter New Value: ");
        	int newValue = gi.inputInteger("newValue");
        	success = addPrice(newKey,newValue);
            if (success) 
            	gi.display("Price added!");
            else 
            	gi.display("Error!");
        } else if (operation == 4) {
        	gi.display("Enter ID: ");
        	int newID = gi.inputInteger("newID");
        	success = deletePrice(newID);
        	if (success) 
        		gi.display("Price deleted!");
            else 
            	gi.display("Error!");
        }
	}
    public void displayPriceList() {
    	gi.display("*****PRICE LIST*****");
    	List<TicketPrice> priceList = TicketPrice.getAll();
    	for (TicketPrice tp : priceList) {
    		gi.display(tp.getId() + " " + tp.getKey() + " " + (tp.getValue()/100) + "." + (tp.getValue()%100));
    	}
    	gi.display("*****END OF PRICE LIST*****");
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
}
