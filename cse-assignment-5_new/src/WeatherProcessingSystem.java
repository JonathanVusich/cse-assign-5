
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class WeatherProcessingSystem {

	private WeatherData[][][] weatherData = new WeatherData[4][12][31];
	private WeatherDataLoad data = new WeatherDataLoad();
	private String[] locations = {"Eagle, NE", "New York, NY", "Houston, TX", "LA, CA"};
	
	public static void main(String[] args) throws FileNotFoundException {
		
//		PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
//    	System.setOut(out);

    	WeatherProcessingSystem main = new WeatherProcessingSystem();
    	UserInterface ui = new UserInterface();
    	int userChoice;
    	GregorianCalendar userDate;
    	boolean running = true;
    	main.initialize();
    	
	    while (true) {
    		userChoice = ui.getMainMenuChoice();
	    	switch (userChoice) {
	    	case 0:
	    		System.out.println("Have a nice day!");
	    		System.exit(0);
	    	case 1:
	    		while (true) {
	    			int[] userInput = ui.getDate();
	    			if (userInput == null) {
	    				break;
	    			} else {
	    				main.printInfoForDay(userInput);
	    			}
	    		}
	    		break;
	    	case 2:
	    		running = true;
	    		while (running) {
	    			userChoice = ui.selectMaxMin("averages");
	    			switch (userChoice) {
	    			case 1:
	    				main.printAvgHigh();
	    				break;
	    			case 2:
	    				main.printAvgLow();
	    				break;
	    			case 3:
	    				main.printAvgWind();
	    				break;
	    			case 4:
	    				main.printAvgGusts();
	    				break;
	    			case 5:
	    				main.printAvgPrecip();
	    				break;
	    			default:
	    				running = false;
	    				break;
	    			}
	    		}
	    		break;
	    	case 3:
	    		running = true;
	    		while (running) {
	    			userChoice = ui.selectMaxMin("maximums");
	    			switch (userChoice) {
	    			case 1:
	    				main.printMaxHigh();
	    				break;
	    			case 2:
	    				main.printMaxLow();
	    				break;
	    			case 3:
	    				main.printMaxWind();
	    				break;
	    			case 4:
	    				main.printMaxGusts();
	    				break;
	    			case 5:
	    				main.printMaxPrecip();
	    				break;
	    			default:
	    				running = false;
	    				break;
	    			}
	    		}
	    		break;
	    	case 4:
	    		running = true;
	    		while (running) {
	    			userChoice = ui.selectMaxMin("minimums");
	    			switch (userChoice) {
	    			case 1:
	    				main.printMinHigh();
	    				break;
	    			case 2:
	    				main.printMinLow();
	    				break;
	    			case 3:
	    				main.printMinAvgWind();
	    				break;
	    			case 4:
	    				main.printMinGusts();
	    				break;
	    			case 5:
	    				main.printMinPrecip();
	    				break;
	    			default:
	    				running = false;
	    				break;
	    			}
	    		}
	    		break;
	    	case 5:
	    		running = true;
	    		while (running) {
	    			userChoice = ui.selectLocation(main.locations);
	    			switch (userChoice) {
	    			case 1:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			case 2:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			case 3:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			case 4:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			default:
	    				running = false;
	    				break;
	    			}
	    		}
	    		break;
	    	case 6:
	    		running = true;
	    		while (running) {
	    			userChoice = ui.selectLocation(main.locations);
	    			switch (userChoice) {
	    			case 1:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			case 2:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			case 3:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			case 4:
	    				main.printTotalPrecip(userChoice-1);
	    				break;
	    			default:
	    				running = false;
	    				break;
	    			}
	    		}
	    		break;
	    	default:
	    		System.out.println("Error! Invalid input!");
	    	}
	    }
	}
	
	public void setInfo(int dayCounter, int location, int month, int day) {
		WeatherData weatherDay = new WeatherData();
		GregorianCalendar date = new GregorianCalendar(2008,month,day+1);
		weatherDay.setDate(date);
		weatherDay.setHigh(this.data.getHIGHS(dayCounter));
		weatherDay.setLow(this.data.getLOWS(dayCounter));
		weatherDay.setWind(this.data.getWIND(dayCounter));
		weatherDay.setGusts(this.data.getGUSTS(dayCounter));
		weatherDay.setPrecipitation(this.data.getPRECIP(dayCounter));
		weatherDay.setLocation(this.locations[location]);
		weatherData[location][month][day] = weatherDay;
	}
	
	public void printInfoForDay(int[] date) {
		int month = date[0];
		int day = date[1];
		for (int i = 0; i < weatherData.length; i++) {
			WeatherData wd = weatherData[i][month-1][day-1];
			wd.print();
			System.out.println("\n");
		}
	}
	
	public void initialize() {
		int dayCounter = 0;
		for (int location = 0; location < 4; location++) {
			 for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 setInfo(dayCounter, location, month, day);
						 dayCounter++;
					 }
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 setInfo(dayCounter, location, month, day);
						 dayCounter++;
					 }
				 } else {
					 for (int day = 0; day < 31; day++) {
						 setInfo(dayCounter, location, month, day);
						 dayCounter++;
					 }
				 }
			 }
		}	
	}
	
	public void printAvgHigh() {
		int total = 0;
		double avg;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 total += weatherData[location][month][day].getHigh();
					 }
					 avg = total/30.0;
					 total = 0;
					 System.out.printf("Average high in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 total += weatherData[location][month][day].getHigh();
					 }
					 avg = total/29.0;
					 total = 0;
					 System.out.printf("Average high in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else {
					 for (int day = 0; day < 31; day++) {
						 total += weatherData[location][month][day].getHigh();
					 }
					 avg = total/31.0;
					 total = 0;
					 if (month != 11) {
						 System.out.printf("Average high in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 } else {
						 System.out.printf("Average high in %s was: %.4f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 }
				 }
			 }
		}	
		
	}
	
	public void printAvgLow() {
		int total = 0;
		double avg;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 total += weatherData[location][month][day].getLow();
					 }
					 avg = total/30.0;
					 total = 0;
					 System.out.printf("Average low in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 total += weatherData[location][month][day].getLow();
					 }
					 avg = total/29.0;
					 total = 0;
					 System.out.printf("Average low in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else {
					 for (int day = 0; day < 31; day++) {
						 total += weatherData[location][month][day].getLow();
					 }
					 avg = total/31.0;
					 total = 0;
					 if (month != 11) {
						 System.out.printf("Average low in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 } else {
						 System.out.printf("Average low in %s was: %.4f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 }
				 }
			 }
		}
	}
	
	public void printAvgWind() {
		int total = 0;
		double avg;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 total += weatherData[location][month][day].getWind();
					 }
					 avg = total/30.0;
					 total = 0;
					 System.out.printf("Average wind in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 total += weatherData[location][month][day].getWind();
					 }
					 avg = total/29.0;
					 total = 0;
					 System.out.printf("Average wind in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else {
					 for (int day = 0; day < 31; day++) {
						 total += weatherData[location][month][day].getWind();
					 }
					 avg = total/31.0;
					 total = 0;
					 if (month != 11) {
						 System.out.printf("Average wind in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 } else {
						 System.out.printf("Average wind in %s was: %.4f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 }
				 }
			 }
		}
	}
	
	public void printAvgGusts() {
		int total = 0;
		double avg;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 total += weatherData[location][month][day].getGusts();
					 }
					 avg = total/30.0;
					 total = 0;
					 System.out.printf("Average gusts in %s were: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 total += weatherData[location][month][day].getGusts();
					 }
					 avg = total/29.0;
					 total = 0;
					 System.out.printf("Average gusts in %s were: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else {
					 for (int day = 0; day < 31; day++) {
						 total += weatherData[location][month][day].getGusts();
					 }
					 avg = total/31.0;
					 total = 0;
					 if (month != 11) {
						 System.out.printf("Average gusts in %s were: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 } else {
						 System.out.printf("Average gusts in %s were: %.4f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 }
				 }
			 }
		}
	}
	
	public void printAvgPrecip() {
		double total = 0;
		double avg;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 total += weatherData[location][month][day].getPrecipitation();
					 }
					 avg = total/30.0;
					 total = 0;
					 System.out.printf("Average precipitation in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 total += weatherData[location][month][day].getPrecipitation();
					 }
					 avg = total/29.0;
					 total = 0;
					 System.out.printf("Average precipitation in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
				 } else {
					 for (int day = 0; day < 31; day++) {
						 total += weatherData[location][month][day].getPrecipitation();
					 }
					 avg = total/31.0;
					 total = 0;
					 if (month != 11) {
					 	System.out.printf("Average precipitation in %s was: %.4f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
					 } else {
						 System.out.printf("Average precipitation in %s were: %.4f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), avg);
						 
					 }
				} 
			 }
		}
	}
	
	public void printMaxHigh() {
		int high = Integer.MIN_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (high < weatherData[location][month][day].getHigh()) {
							 high = weatherData[location][month][day].getHigh();
						 }
					 }
					 System.out.printf("The high for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
					 high = Integer.MIN_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (high < weatherData[location][month][day].getHigh()) {
							 high = weatherData[location][month][day].getHigh();
						 }
					 }
					 System.out.printf("The high for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
					 high = Integer.MIN_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (high < weatherData[location][month][day].getHigh()) {
							 high = weatherData[location][month][day].getHigh();}
					 }
					 if (month != 11) {
						 System.out.printf("The high for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
						 high = Integer.MIN_VALUE;
					 } else {
						 System.out.printf("The high for the month of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
					 }
					 
	 			 }
				 
			}
	
		}
	}
	
	public void printMaxLow() {
		
		int low = Integer.MIN_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (low < weatherData[location][month][day].getLow()) {
							 low = weatherData[location][month][day].getLow();
						 }
					 }
					 System.out.printf("The highest low for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
					 low = Integer.MIN_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (low < weatherData[location][month][day].getLow()) {
							 low = weatherData[location][month][day].getLow();
						 }
					 }
					 System.out.printf("The highest low for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
					 low = Integer.MIN_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (low < weatherData[location][month][day].getLow()) {
							 low = weatherData[location][month][day].getLow();}
					 }
					 if (month != 11) {
						 System.out.printf("The highest low for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
						 low = Integer.MIN_VALUE;
					 } else {
						 System.out.printf("The highest low for the month of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
						 
					 }
				}
				 
			}
	
		}
		
	}
	
	public void printMaxWind() {
		
		int wind = Integer.MIN_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (wind < weatherData[location][month][day].getWind()) {
							 wind = weatherData[location][month][day].getWind();
						 }
					 }
					 System.out.printf("The highest average wind for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
					 wind = Integer.MIN_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (wind < weatherData[location][month][day].getWind()) {
							 wind = weatherData[location][month][day].getWind();
						 }
					 }
					 System.out.printf("The highest average wind for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
					 wind = Integer.MIN_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (wind < weatherData[location][month][day].getWind()) {
							 wind = weatherData[location][month][day].getWind();}
					 }
					 if (month != 11) {
						 System.out.printf("The highest average wind for the month of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
						 wind = Integer.MIN_VALUE; 
					 } else {
						 System.out.printf("The highest average wind for the month of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
					 }
				} 
			}
	
		}
	}
	
	public void printMaxGusts() {
		
		int gusts = Integer.MIN_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (gusts < weatherData[location][month][day].getGusts()) {
							 gusts = weatherData[location][month][day].getGusts();
						 }
					 }
					 System.out.printf("The highest gust for %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
					 gusts = Integer.MIN_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (gusts < weatherData[location][month][day].getGusts()) {
							 gusts = weatherData[location][month][day].getGusts();
						 }
					 }
					 System.out.printf("The highest gust for %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
					 gusts = Integer.MIN_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (gusts < weatherData[location][month][day].getGusts()) {
							 gusts = weatherData[location][month][day].getGusts();}
					 }
					 if (month != 11) {
						 System.out.printf("The highest gust for %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
						 gusts = Integer.MIN_VALUE;
					 } else {
						 System.out.printf("The highest gust for %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
					 }
				 } 
			}
	
		}
	}
	
	public void printMaxPrecip() {
		
		double precipitation = Double.MIN_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (precipitation < weatherData[location][month][day].getPrecipitation()) {
							 precipitation = weatherData[location][month][day].getPrecipitation();
						 }
					 }
					 System.out.printf("The highest amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
					 precipitation = Double.MIN_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (precipitation < weatherData[location][month][day].getPrecipitation()) {
							 precipitation = weatherData[location][month][day].getPrecipitation();
						 }
					 }
					 System.out.printf("The highest amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
					 precipitation = Double.MIN_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (precipitation < weatherData[location][month][day].getPrecipitation()) {
							 precipitation = weatherData[location][month][day].getPrecipitation();}
					 }
					 if (month != 11) {
						 System.out.printf("The highest amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
						 precipitation = Double.MIN_VALUE;
					 } else {
						 System.out.printf("The highest amount of precipitation received in %s was: %.2f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
					 }
					 
	 			 }
				 
			}
	
		}
	}
	
	public void printMinHigh() {
		
		int high = Integer.MAX_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (high > weatherData[location][month][day].getHigh()) {
							 high = weatherData[location][month][day].getHigh();
						 }
					 }
					 System.out.printf("The lowest high of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
					 high = Integer.MAX_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (high > weatherData[location][month][day].getHigh()) {
							 high = weatherData[location][month][day].getHigh();
						 }
					 }
					 System.out.printf("The lowest high of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
					 high = Integer.MAX_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (high > weatherData[location][month][day].getHigh()) {
							 high = weatherData[location][month][day].getHigh();
						 }
					 }
					 if (month != 11) {
						 System.out.printf("The lowest high of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
						 high = Integer.MAX_VALUE;
					 } else {
						 System.out.printf("The lowest high of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), high);
					 }
					 
				 }
			 }
		}
	}
	
	public void printMinLow() {
		
		int low = Integer.MAX_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (low > weatherData[location][month][day].getLow()) {
							 low = weatherData[location][month][day].getLow();
						 }
					 }
					 System.out.printf("The lowest low of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
					 low = Integer.MAX_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (low > weatherData[location][month][day].getLow()) {
							 low = weatherData[location][month][day].getLow();
						 }
					 }
					 System.out.printf("The lowest low of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
					 low = Integer.MAX_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (low > weatherData[location][month][day].getLow()) {
							 low = weatherData[location][month][day].getLow();
						 }
					 }
					 if (month != 11) {
						 System.out.printf("The lowest low of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
						 low = Integer.MAX_VALUE;
					 } else {
						 System.out.printf("The lowest low of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), low);
					 }
				}
			}
		}
	}
	
	
	public void printMinGusts() {
		
		int gusts = Integer.MAX_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (gusts > weatherData[location][month][day].getGusts()) {
							 gusts = weatherData[location][month][day].getGusts();
						 }
					 }
					 System.out.printf("The calmest gust of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
					 gusts = Integer.MAX_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (gusts > weatherData[location][month][day].getGusts()) {
							 gusts = weatherData[location][month][day].getGusts();
						 }
					 }
					 System.out.printf("The calmest gust of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
					 gusts = Integer.MAX_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (gusts > weatherData[location][month][day].getGusts()) {
							 gusts = weatherData[location][month][day].getGusts();
						 }
					 }
					 if (month != 11) {
						 System.out.printf("The calmest gust of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
						 gusts = Integer.MAX_VALUE;
					 } else {
						 System.out.printf("The calmest gust of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), gusts);
						 
					 }
				}
			 }
		}
	}
	
	
	public void printMinAvgWind() {
		
		int wind = Integer.MAX_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (wind > weatherData[location][month][day].getWind()) {
							 wind = weatherData[location][month][day].getWind();
						 }
					 }
					 System.out.printf("The calmest average wind of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
					 wind = Integer.MAX_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (wind > weatherData[location][month][day].getWind()) {
							 wind = weatherData[location][month][day].getWind();
						 }
					 }
					 System.out.printf("The calmest average wind of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
					 wind = Integer.MAX_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (wind > weatherData[location][month][day].getWind()) {
							 wind = weatherData[location][month][day].getWind();
						 }
					 }
					 if (month != 11) {
						 System.out.printf("The calmest average wind of %s was: %d\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
						 wind = Integer.MAX_VALUE;
					 } else {
						 System.out.printf("The calmest average wind of %s was: %d\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), wind);
					 }
				}
			}
		}
	}
	
	
	public void printMinPrecip() {
		
		double precipitation = Double.MAX_VALUE;
		
		for (int location = 0; location < 4; location++) {
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 if (precipitation > weatherData[location][month][day].getPrecipitation()) {
							 precipitation = weatherData[location][month][day].getPrecipitation();
						 }
					 }
					 System.out.printf("The lowest amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
					 precipitation = Double.MAX_VALUE;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 if (precipitation > weatherData[location][month][day].getPrecipitation()) {
							 precipitation = weatherData[location][month][day].getPrecipitation();
						 }
					 }
					 System.out.printf("The lowest amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
					 precipitation = Double.MAX_VALUE;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 if (precipitation > weatherData[location][month][day].getPrecipitation()) {
							 precipitation = weatherData[location][month][day].getPrecipitation();
						 }
					 }
					 if (month != 11) {
						 System.out.printf("The lowest amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
						 precipitation = Double.MAX_VALUE;
					 } else {
						 System.out.printf("The lowest amount of precipitation received in %s was: %.2f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), precipitation);
					 }
				} 
			}
		}
	}
	
	public void printTotalPrecip(int location) {
		
		double totalPrecip = 0;
		
			System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
			for (int month = 0; month < 12; month++) {
				 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
					 for (int day = 0; day < 30; day++) {
						 totalPrecip += weatherData[location][month][day].getPrecipitation();
					 }
					 System.out.printf("The total amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), totalPrecip);
					 totalPrecip = 0;
				 } else if (month == 1) {
					 for (int day = 0; day < 29; day++) {
						 totalPrecip += weatherData[location][month][day].getPrecipitation();
					 }
					 System.out.printf("The total amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), totalPrecip);
					 totalPrecip = 0;
	 			 } else {
					 for (int day = 0; day < 31; day++) {
						 totalPrecip += weatherData[location][month][day].getPrecipitation();
					 }
					 if (month != 11) {
						 System.out.printf("The total amount of precipitation received in %s was: %.2f\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), totalPrecip);
						 totalPrecip = 0;
					 } else {
						 System.out.printf("The total amount of precipitation received in %s was: %.2f\n\n", weatherData[location][month][0].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), totalPrecip);
					 }
	 			 }
			}
		}
	
	public void printExtremes(int location) {
		
		int high = Integer.MIN_VALUE;
		int highDayCounter = 0;
		int highMonthCounter = 0;
		int low = Integer.MAX_VALUE;
		int lowDayCounter = 0;
		int lowMonthCounter = 0;
		int gusts = Integer.MIN_VALUE;
		int gustDayCounter = 0;
		int gustMonthCounter = 0;
		double precipitation = Double.MIN_VALUE;
		int precipDayCounter = 0;
		int precipMonthCounter = 0;
		
		System.out.println("Location: " + weatherData[location][0][0].getLocation() + "\n");
		for (int month = 0; month < 12; month++) {
			 if (month == 3 || month == 5 || month == 8 || month == 10) {	 
				 for (int day = 0; day < 30; day++) {
					 if (weatherData[location][month][day].getHigh() > high) {
						 high = weatherData[location][month][day].getHigh();
						 highDayCounter = day;
						 highMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getLow() < low) {
						 low = weatherData[location][month][day].getLow();
						 lowDayCounter = day;
						 lowMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getGusts() > gusts) {
						 gusts = weatherData[location][month][day].getGusts();
						 gustDayCounter = day;
						 gustMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getPrecipitation() > precipitation) {
						 precipitation = weatherData[location][month][day].getPrecipitation();
						 precipDayCounter = day;
						 precipMonthCounter = month;
					 }
				 }
			} else if (month == 1) {
				 for (int day = 0; day < 29; day++) {
					 if (weatherData[location][month][day].getHigh() > high) {
						 high = weatherData[location][month][day].getHigh();
						 highDayCounter = day;
						 highMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getLow() < low) {
						 low = weatherData[location][month][day].getLow();
						 lowDayCounter = day;
						 lowMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getGusts() > gusts) {
						 gusts = weatherData[location][month][day].getGusts();
						 gustDayCounter = day;
						 gustMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getPrecipitation() > precipitation) {
						 precipitation = weatherData[location][month][day].getPrecipitation();
						 precipDayCounter = day;
						 precipMonthCounter = month;
					 }
				 }
 			 } else {
				 for (int day = 0; day < 31; day++) {
					 if (weatherData[location][month][day].getHigh() > high) {
						 high = weatherData[location][month][day].getHigh();
						 highDayCounter = day;
						 highMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getLow() < low) {
						 low = weatherData[location][month][day].getLow();
						 lowDayCounter = day;
						 lowMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getGusts() > gusts) {
						 gusts = weatherData[location][month][day].getGusts();
						 gustDayCounter = day;
						 gustMonthCounter = month;
					 }
					 if (weatherData[location][month][day].getPrecipitation() > precipitation) {
						 precipitation = weatherData[location][month][day].getPrecipitation();
						 precipDayCounter = day;
						 precipMonthCounter = month;
					 }
				} 
 			 }
			 System.out.printf("The max temperature in 2008 was %d F and was recorded on %s %s.", weatherData[location][highMonthCounter][highDayCounter].getHigh(), weatherData[location][highMonthCounter][highDayCounter].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), weatherData[location][highMonthCounter][highDayCounter].getDate().getDisplayName(GregorianCalendar.DAY_OF_MONTH, GregorianCalendar.LONG, Locale.getDefault()));
			 System.out.printf("The lowest temperature in 2008 was %d F and was recorded on %s %s.", weatherData[location][lowMonthCounter][lowDayCounter].getLow(), weatherData[location][lowMonthCounter][lowDayCounter].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), weatherData[location][highMonthCounter][highDayCounter].getDate().getDisplayName(GregorianCalendar.DAY_OF_MONTH, GregorianCalendar.LONG, Locale.getDefault()));
			 System.out.printf("The maximum wind in 2008 was %d mph and was recorded on %s %s.", weatherData[location][gustMonthCounter][gustDayCounter].getGusts(), weatherData[location][gustMonthCounter][gustDayCounter].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), weatherData[location][highMonthCounter][highDayCounter].getDate().getDisplayName(GregorianCalendar.DAY_OF_MONTH, GregorianCalendar.LONG, Locale.getDefault()));
			 System.out.printf("The max precipitation in 2008 was %d inches and was recorded on %s %s.", weatherData[location][precipMonthCounter][precipDayCounter].getPrecipitation(), weatherData[location][precipMonthCounter][precipDayCounter].getDate().getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), weatherData[location][highMonthCounter][highDayCounter].getDate().getDisplayName(GregorianCalendar.DAY_OF_MONTH, GregorianCalendar.LONG, Locale.getDefault()));
			 
		}
	}
}