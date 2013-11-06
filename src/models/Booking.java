package models;

import java.util.List;

import utils.Database;
import utils.Model;

public class Booking extends Model{
	private String TID;
	private int totalPrice;
	private String bookingStatus;
	private Customer customer;
	
	public void loadData() {
		TID = get("TID").getString();
		totalPrice = get("totalPrice").getInteger();
		bookingStatus = get("bookingStatus").getString();
		setCustomer(get("customerId").getInteger());
	}
	
	public void saveData() {
		set("TID", TID);
		set("totalPrice", totalPrice);
		set("bookingStatus", bookingStatus);
		set("customerId", customer.getId());
	}
	
	public void setTID(String TID) {
		this.TID = TID;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}	
	public void setCustomer(int customerId) {
		Customer customer = Customer.getOneById(customerId);
		setCustomer(customer);
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getTID() {
		return TID;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public Customer getCustomer() {
		return customer;
	}
	public List<Booking> getTickets() {
		return null;
	}
	
	public static Booking getOneById(int id) {
		List<Booking> bookings = getAll();
		for (Booking booking : bookings) {
			if (booking.getId() == id) {
				return booking;
			}
		}
		return null;
	}
	public static List<Booking> getAll() {
		return Database.getAll(Booking.class);
	}
}
