package models;

import java.util.List;
import utils.Database;
import utils.Model;

public class Ticket extends Model{
	private Show show;
	private Seat seat;
	private int price;
	private String 	ticketType;
	
	public void loadData() {
		setShow(get("showId").getInteger());
		setSeat(get("seatId").getInteger());
		price = get("price").getInteger();
		ticketType = get("ticketType").getString();
	}
	
	public void saveData() {
		set("showId", show.getId());
		set("seatId", seat.getId());
		set("price", price);
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
	public String ticketType() {
		return ticketType;
	}
}
