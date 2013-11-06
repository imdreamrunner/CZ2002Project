package controllers;
import models.Customer;

public class HistoryController {
	public static Customer validCustomer(String name, int mobile){
    	Customer customer = null;
    	customer = Customer.getOneByMobile(mobile);
    	if (customer == null) return customer;
    	if (!customer.getName().equalsIgnoreCase(name))
    		customer = null;
    	return customer;
    }
		


}
