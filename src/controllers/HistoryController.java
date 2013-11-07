package controllers;
import models.Customer;

public class HistoryController {
	
	public static Customer validateCustomer(String name, int mobile){
		
    	Customer customer = Customer.getOneByMobile(mobile);
    	if (customer == null) return null;
    	if (!customer.getName().equals(name)) return null;
    	return customer;
    }
		
}
