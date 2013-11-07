package views;

public class ReportInterface {
	
	public static void main() {
		displayMenu();
	}
	
	public static void displayMenu() {
        System.out.printf("*****REPORT CENTER*****\n");
        System.out.print("Input your option: \n" +
                "1. List by movie \n" +
                "2. List by cineplex \n" +
                "3. List by day \n" +
                "4. List by month \n" + 
                "5. Return to previous page \n" +
                "Please input your choice: ");		
	}

}
