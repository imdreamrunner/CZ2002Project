package models;

import java.util.ArrayList;
import java.util.List;

import utils.Database;
import utils.Model;

public class Ticket extends Model{
	private Show show;
	private Seat seat;
	private int price;
	private Booking booking;
	private String 	ticketType;
	
	public void loadData() {
		setShow(get("showId").getInteger());
		setSeat(get("seatId").getInteger());
		price = get("price").getInteger();
		setBooking(get("bookingid").getInteger());
		ticketType = get("ticketType").getString();
	}
	
	public void saveData() {
		set("showId", show.getId());
		set("seatId", seat.getId());
		set("price", price);
		set("bookingId",booking.getId());
		set("ticketType", ticketType);
	}
	
	//two methods to set show
	public void setShow(int showId) {
		Show theShow = Show.getOne(showId);
		setShow(theShow);
	}
	public void setShow(Show show) {
		this.show = show;
	}
	
	//two methods to set seat
	public void setSeat(int seatId) {
		Seat seat = Seat.getOne(seatId);
		setSeat(seat);
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	
	//two methods to set booking
	public void setBooking(int bookingId) {
		Booking booking = Booking.getOneById(bookingId);
		setBooking(booking);
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	public Show getShow() {
		return show;
	}
	public Seat getSeat() {
		return seat;
	}
	public int getPrice() {
		return price;
	}
	public Booking getBooking() {
		return booking;
	}
	public String getTicketType() {
		return ticketType;
	}
	public Movie getMovie() {
		return show.getMovie();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Ticket> getAll() {
		return Database.getAll(Ticket.class);
	}
	public static List<Ticket> getAllByBooking(Booking booking) {
		List<Ticket> ticketList = getAll();
		List<Ticket> select = new ArrayList<Ticket>();
		for (Ticket ticket : ticketList) {
			if (ticket.getBooking().equals(booking)) {
				select.add(ticket);
			}
		}
		return select;
	}
	public static Ticket getOne(int id) {
		List<Ticket> ticketList = getAll();
		for (Ticket ticket : ticketList) {
			if (ticket.getId() == id) {
				return ticket;
			}
		}
		return null;
	}
}
