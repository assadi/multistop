package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Airport;
import model.Event;
import model.Stop;
import model.Trip;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import core.TripTranslator;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search/*")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String FLIGHTS_HTML_PATH = "/html/flights.html";
	private final String HOTELS_HTML_PATH = "/html/hotels.html";

	public Search() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String subPath = request.getPathInfo();
			String stopsStr = request.getParameter("stops");
			String eventsStr = request.getParameter("events");
			String departureAirportStr = request.getParameter("dipartureAirport");
			String departureStartDateStr = request.getParameter("departureStartDate");
			String departureEndDateStr = request.getParameter("departureEndDate");
			String onlyDirectFlightsStr = request.getParameter("onlyDirectFlights");
			String tripLengthStr = request.getParameter("tripLength");
			String costLimitStr = request.getParameter("costLimit");
			
			List<Stop> stops = parseStops(stopsStr);
			List<Event> events = parseEvents(eventsStr);
			Airport departureAirport = new Airport(departureAirportStr);

	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date departureStartDate = departureStartDateStr.isEmpty()? null : formatter.parse(departureStartDateStr);
            Date departureEndDate = departureEndDateStr.isEmpty()? null : formatter.parse(departureEndDateStr);
            
            boolean onlyDirectFlights = false; 
            if (onlyDirectFlightsStr.toLowerCase().equals("true")) {
            	onlyDirectFlights = true;
            }
            
            int tripLength = -1;
            if (!tripLengthStr.isEmpty()) {
            	tripLength = Integer.parseInt(tripLengthStr);
            }
            
            int costLimit = -1;
            if (!costLimitStr.isEmpty()) {
            	costLimit = Integer.parseInt(costLimitStr);
            }
			
            // here call trip translator to generate list of trips
            TripTranslator tripTranlator = new TripTranslator();
            List<Trip> trips = tripTranlator.translate(departureAirport, departureStartDate, departureEndDate, stops, events);
            
            // Now we should the trips to the main algorithm that will ask the API for the result
			
			if (subPath.equals("/Hotels")) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.append("Reached search hotels servlet");
				out.close();
				response.setStatus(200);
				return;
			}
			response.setStatus(200);
		} catch (Exception e) {
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private List<Stop> parseStops(String stopsStr) throws ParseException {
		List<Stop> stops = new ArrayList<Stop>();
		JSONParser parser = new JSONParser();
		Object o = parser.parse(stopsStr);
		JSONArray stopsJsonArray = (JSONArray)parser.parse(stopsStr);
		for (int i = 0; i < stopsJsonArray.size(); i++) {
			String stopStr = stopsJsonArray.get(i).toString();
			JSONObject stopJson = (JSONObject) parser.parse(stopStr);
			String airportStr = stopJson.get("airport").toString();
			String daysStr = stopJson.get("days").toString();
			
			Airport airport = new Airport(airportStr);
			int days = -1;
			if (!daysStr.isEmpty()) {
				days = Integer.parseInt(daysStr);
			}
			
			Stop stop = new Stop(airport, days);
			stops.add(stop);
		}
		
		return stops;
	}
	
	private List<Event> parseEvents(String eventsStr) throws ParseException, java.text.ParseException {
		List<Event> events = new ArrayList<Event>();
		JSONParser parser = new JSONParser();
		Object o = parser.parse(eventsStr);
		JSONArray eventsJsonArray = (JSONArray)parser.parse(eventsStr);
		for (int i = 0; i < eventsJsonArray.size(); i++) {
			String eventStr = eventsJsonArray.get(i).toString();
			JSONObject eventJson = (JSONObject) parser.parse(eventStr);
			String locationStr = eventJson.get("location").toString();
			String startDateStr = eventJson.get("startDate").toString();
			String endDateStr = eventJson.get("endDate").toString();
			
			Airport location = new Airport(locationStr);

	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = formatter.parse(startDateStr);
            Date endDate = formatter.parse(endDateStr);
            
			Event event = new Event(location, startDate, endDate);
			events.add(event);
		}
		
		return events;
	}

}
