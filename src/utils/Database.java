package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	private static File getDbFile(Class<? extends Model> classType) {
		String[] names = classType.getName().split("\\.");
		return new File("db/" + names[names.length - 1] + ".txt");
	}
	public static List getAll(Class<? extends Model> classType) throws FileNotFoundException {
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
	}
	
	static void save(Class<? extends Model> classType, Model Model) throws FileNotFoundException {
		boolean found = false;
		List<Model> Models = getAll(classType);
		for (int i = 0; i < Models.size(); i++) {
			Model temp = Models.get(i);
			if (temp.getId() == Model.getId()) {
				found = true;
				Models.set(i, Model);
			}
		}
		if (!found) {
			if (Models.size() > 0) {
				Model.setId(Models.get(Models.size() - 1).getId() + 1);
			} else {
				Model.setId(1);
			}
			System.out.println("new id " + Model.getId());
			Models.add(Model);
		}
		PrintWriter writer = new PrintWriter(getDbFile(classType));
		for (Model temp : Models) {
			writer.println(temp.getStringData());
		}
		writer.close();
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
