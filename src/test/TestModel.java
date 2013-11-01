package test;

import java.io.FileNotFoundException;

import models.Staff;

public class TestModel {
	public static void main(String[] args) throws FileNotFoundException {
		Staff staff = new Staff();
		staff.setUsername("admin");
		staff.setPassword("password");
		staff.save();
		Staff staff2 = new Staff();
		staff2.setUsername("admin2");
		staff2.setPassword("password2");
		staff2.save();
		Staff test = Staff.getStaffByUsername("admin");
		System.out.println(test.getPassword());
	}
}
