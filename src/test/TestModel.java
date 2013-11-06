package test;

import models.Cineplex;
import models.Staff;

public class TestModel {
	public static void main(String[] args) {
		testCineplex();
	}
	
	public static void testStaff() {
		Staff staff = new Staff();
		staff.setUsername("admin");
		staff.setPassword("password");
		staff.save();
		Staff staff2 = new Staff();
		staff2.setUsername("admin2");
		staff2.setPassword("password2");
		staff2.save();
		Staff test = Staff.getByUsername("admin");
		System.out.println(test.getPassword());
		staff.delete();
		staff2.delete();
	}
	
	public static void testCineplex() {
		Cineplex cineplex = new Cineplex();
		cineplex.setName("Apple");
		cineplex.save();
	}
}
