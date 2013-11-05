package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	private static File getDbFile(Class<? extends Model> classType) {
		String[] names = classType.getName().split("\\.");
		return new File("db/" + names[names.length - 1] + ".txt");
	}
	@SuppressWarnings("rawtypes")
	public static List getAll(Class<? extends Model> classType) {
		try {
			List<Model> models = new ArrayList<Model>();
			Scanner scanner = new Scanner(getDbFile(classType));
			while (scanner.hasNext()) {
				try {
					Model model = classType.newInstance();
					model.setStringData(scanner.nextLine());
					models.add(model);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// new Model(scanner.nextLine());
			}
			scanner.close();
			return models;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	static void save(Class<? extends Model> classType, Model model) {
		try {
			boolean found = false;
			@SuppressWarnings("unchecked")
			List<Model> models = getAll(classType);
			for (int i = 0; i < models.size(); i++) {
				Model temp = models.get(i);
				if (temp.getId() == model.getId()) {
					found = true;
					models.set(i, model);
				}
			}
			if (!found) {
				if (models.size() > 0) {
					model.setId(models.get(models.size() - 1).getId() + 1);
				} else {
					model.setId(1);
				}
				System.out.println("new id " + model.getId());
				models.add(model);
			}
			PrintWriter writer = new PrintWriter(getDbFile(classType));
			for (Model temp : models) {
				writer.println(temp.getStringData());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("[FileNotFound]" + e.getMessage());
			System.exit(0);
		}
	}
	
	public static void delete(Class<? extends Model> classType, Model model) {
		try {
			@SuppressWarnings("unchecked")
			List<Model> models = getAll(classType);
			PrintWriter writer = new PrintWriter(getDbFile(classType));
			for (Model temp : models) {
				if (temp.getId() != model.getId()) {
					writer.println(temp.getStringData());
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("[FileNotFound]" + e.getMessage());
			System.exit(0);
		}
	}
	
	public static class Value {
		private String value;
		public Value(String theValue) {
			value = theValue;
		}
		public String getString() {
			return value;
		}
		public int getInteger() {
			if (value == null || value.equals("")) {
				return 0;
			} else {
				return Integer.parseInt(value);
			}
		}
		public long getLong() {
			if (value == null || value.equals("")) {
				return 0;
			} else {
				return Long.parseLong(value);
			}
		}
		public Date getData() {
			long timestamp = getLong();
			return new Date(timestamp);
		}
	}
}
