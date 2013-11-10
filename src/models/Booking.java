package models;

import java.util.Date;
import java.util.List;

import utils.Database;
import utils.Model;

public class Booking extends Model{
	private String TID;
	private int totalPrice;
	private String bookingStatus;
	private Customer customer;
	private Date createTime;
	
	public void loadData() {
		TID = get("TID").getString();
		totalPrice = get("totalPrice").getInteger();
		bookingStatus = get("bookingStatus").getString();
		setCustomer(get("customerId").getInteger());
		createTime = get("createTime").getData();
	}
	
	public void saveData() {
		set("TID", TID);
		set("totalPrice", totalPrice);
		set("bookingStatus", bookingStatus);
		set("customerId", customer.getId());
		set("createTime", createTime);
	}
	
	public void setTID(String TID) {
		this.TID = TID;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void calculateTotalPrice() {
		int total = 0;
		for (Ticket ticket : getTickets()) {
			ticket.calculatePrice();
			total += ticket.getPrice();
		}
		totalPrice = total;
		save();
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
	public void setCreateTime(Date theCreateTime) {
		createTime = theCreateTime;
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
	public Date getCreateTime() {
		return createTime;
	}
	public Movie getMovie() {
		return getTickets().get(0).getMovie();
	}
	public List<Ticket> getTickets() {
		return Ticket.getAllByBooking(this);
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
	@SuppressWarnings("unchecked")
	public static List<Booking> getAll() {
		return Database.getAll(Booking.class);
	}
}
