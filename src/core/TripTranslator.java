package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Airport;
import model.Event;
import model.Stop;
import model.Trip;

public class TripTranslator {
	
	private List<Trip> mTrip = null;
	
	public TripTranslator() {}
	
	public List<Trip> translate(Airport departureAirport, Date departureStart, Date departureEnd, List<Stop> stops, List<Event> events) {
		mTrip = new ArrayList<Trip>();
		return mTrip;
	}
}
