package models;

import java.util.List;

import utils.Database;
import utils.Model;

public class Customer extends Model {
	private String name;
	private int mobile;
	private String email;
	
	public void loadData() {
		name = get("name").getString();
		mobile = get("mobile").getInteger();
		email = get("email").getString();
	}
	
	public void saveData() {
		set("name", name);
		set("mobile", mobile);
		set("email", email);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public int getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	
	public static List<Customer> getAll() {
		@SuppressWarnings("unchecked")
		List<Customer> customers = Database.getAll(Customer.class);
		return customers;
	}
	
	public static Customer getOneByName(String name) {
		List<Customer> customers = getAll();
		for (Customer customer : customers) {
			if (customer.getName() == name) {
				return customer;
			}
		}
		return null;
	}
	
	public static Customer getOneByMobile(int mobile) {
		List<Customer> customers = getAll();
		for (Customer customer : customers) {
			if (customer.getMobile() == mobile) {
				return customer;
			}
		}
		return null;
	}
}
