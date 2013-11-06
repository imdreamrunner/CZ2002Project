package models;

import java.util.List;
import utils.Database;
import utils.Model;

public class Booking extends Model{
	private String TID;
	private int totalPrice;
	private String bookingStatus;
	private Ticket[] ticket;
	private Customer customer;
	
	public void loadData() {
		TID = get("TID").getString();
		totalPrice = get("totalPrice").getInteger();
		bookingStatus = get("bookingStatus").getString();
		
		setCustomer(get("customerId").getInteger());
	}
	
	public void setData() {
		
	}
	
	public void setCustomer(int customerId) {
		Customer customer = Customer.getOneById(customerId);
		setCustomer(customer);
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
