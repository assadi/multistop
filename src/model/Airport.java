package model;

public class Airport {
	
	private String mName = null;
	private String mCity = null;
	private String mCountry = null;
	
	public Airport(String name, String city, String country) {
		mName = name;
		mCity = city;
		mCountry = country;
	}
	
	
	/**
	 * @param airportStr a string that represents the airport 
	 * the formar is name, city, county
	 */
	public Airport(String airportStr) {
		String[] airportStrSplitted = airportStr.split(",");
		assert(airportStrSplitted.length == 3);
		mName = airportStrSplitted[0].trim();
		mCity = airportStrSplitted[1].trim();
		mCountry = airportStrSplitted[2].trim();
	}
	
	public String getName() {
		return mName;
	}
	
	public String getCity() {
		return mCity;
	}
	
	public String getCountry() {
		return mCountry;
	}
	
}
