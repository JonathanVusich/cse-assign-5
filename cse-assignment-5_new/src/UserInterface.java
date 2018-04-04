

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
public class UserInterface {

	public int getMainMenuChoice() {
		
		Scanner sc = new Scanner(System.in);
		int userInput;
		
		while (true) {
			try {
				System.out.println("Please select a feature that you would like to use from the following:");
				System.out.println("\t1.\tWeather Record");
				System.out.println("\t2.\tAverages");
				System.out.println("\t3.\tMaximums");
				System.out.println("\t4.\tMinimums");
				System.out.println("\t5.\tTotal Precipitation");
				System.out.println("\t6.\tExtremes");
				System.out.println("\t0.\tQuit");
				
				userInput = sc.nextInt();
				if (userInput >= 0 && userInput < 7) {
					return userInput;
				} else {
					System.out.println("Error! Not a valid choice!\n");
				}
			} catch (InputMismatchException ime) {
				System.out.println("Error! Invalid input!\n");
				sc.next();
			}
		}
	}
	
	public int[] getDate() {
		
		Scanner sc = new Scanner(System.in);
		String userInput;
		String[] date;
		int[] finalDate = new int[3];
		
		while (true) {
			try {
				System.out.println("\nPlease enter a date to view (MM/DD/2008, 0 to quit):");
				userInput = sc.next();
				if (userInput.equals(0)) {
					return null;
				}
				date = userInput.split("/");
				if (Integer.parseInt(date[0]) > 12 || Integer.parseInt(date[0]) < 1 || Integer.parseInt(date[1]) > 31 || Integer.parseInt(date[1]) < 1 || Integer.parseInt(date[2]) != 2008) {
					System.out.println("Error! Not a valid date!");
				} else {
					finalDate[0] = Integer.parseInt(date[0]);
					finalDate[1] = Integer.parseInt(date[1]);
					finalDate[2] = Integer.parseInt(date[2]);
					return finalDate;
				}
			} catch (NumberFormatException pe) {
				System.out.println("Error! Not a valid date!");
			}
		}
	}
	
	public int selectMaxMin(String choice) {
		
		Scanner sc = new Scanner(System.in);
		int userInput;
		
		while (true) {
			try {
				
				System.out.printf("Please select which data value that you wish to see the %s for:\n", choice);
				System.out.println("\t1.\tHighs");
				System.out.println("\t2.\tLows");
				System.out.println("\t3.\tWind");
				System.out.println("\t4.\tGusts");
				System.out.println("\t5.\tPrecipitation");
				System.out.println("\t0.\tQuit");
				
				userInput = sc.nextInt();
				if (userInput >= 0 && userInput < 6) {
					return userInput;
				} else {
					System.out.println("Error! Not a valid selection!\n");
				}
			} catch (InputMismatchException ime) {
				System.out.println("Error! Invalid input!\n");
				sc.next();
			}
			
		}
	}
	
	public int selectLocation(String[] locations) {
		
		Scanner sc = new Scanner(System.in);
		int userInput;
		
		while (true) {
			try {
				
				System.out.println("Please select the desired location or press 0 to quit:");
				for (int i = 0; i < locations.length; i++) {
					System.out.println("\t" + (i+1) + ".\t" + locations[i]);
				}
				userInput = sc.nextInt();
				if (userInput >= 0 && userInput <= locations.length) {
					return userInput;
				} else {
					System.out.println("Error! Not a valid choice!\n");
				}
			} catch (InputMismatchException ime) {
				System.out.println("Error! Invalid input!\n");
				sc.next();
			}
		}
	}
}