
import java.util.GregorianCalendar;
import java.util.Locale;

public class WeatherData {

	private String location;
	private GregorianCalendar date;
	private int high;
	private int low;
	private int wind;
	private int gusts;
	private double precipitation;
	
	public void print() {
		System.out.printf("Location: %-20s Date: %s %d, %d\n", location, date.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, Locale.getDefault()), date.get(GregorianCalendar.DAY_OF_MONTH), date.get(GregorianCalendar.YEAR));
		System.out.printf("High Temp: %-19d Low Temp: %d\n", this.high, this.low);
		System.out.printf("Avg Wind: %-20d Max Wind: %d\n", this.wind, this.gusts);
		System.out.printf("Precipitation: %.2f inches", this.precipitation);
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the date
	 */
	
	public GregorianCalendar getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	
	/**
	 * @return the high
	 */
	
	public int getHigh() {
		return high;
	}
	
	/**
	 * @param high the high to set
	 */
	
	public void setHigh(int high) {
		this.high = high;
	}
	
	/**
	 * @return the low
	 */
	
	public int getLow() {
		return low;
	}
	
	/**
	 * @param low the low to set
	 */
	
	public void setLow(int low) {
		this.low = low;
	}
	
	/**
	 * @return the wind
	 */
	
	public int getWind() {
		return wind;
	}
	
	/**
	 * @param wind the wind to set
	 */
	
	public void setWind(int wind) {
		this.wind = wind;
	}
	
	/**
	 * @return the gusts
	 */
	
	public int getGusts() {
		return gusts;
	}
	
	/**
	 * @param gusts the gusts to set
	 */
	
	public void setGusts(int gusts) {
		this.gusts = gusts;
	}
	
	/**
	 * @return the precipitation
	 */
	
	public double getPrecipitation() {
		return precipitation;
	}
	
	/**
	 * @param precipitation the precipitation to set
	 */
	
	public void setPrecipitation(double precipitation) {
		this.precipitation = precipitation;
	}
	
	
	
}
