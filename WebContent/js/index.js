/**
 * 
 */

function getFileHref(file) {
	var APP_NAME = "MultistopWeb"
	return window.location.origin + "/" + APP_NAME + "/" + file
}

function onSearchFlights() {

	queryObj = Object()
	queryObj.stops = JSON.stringify(getStops())
	queryObj.events = JSON.stringify(getEvents())
	queryObj.dipartureAirport = $('#' + getDepartureAirportInputID()).val()
	queryObj.departureStartDate = $('#' + getDepartureStartDateInputID()).val()
	queryObj.departureEndDate = $('#' + getDepartureEndDateInputID()).val()
	queryObj.onlyDirectFlights = $('#' + getOnlyDirectFlightsInputID()).prop('checked')
	queryObj.tripLength = $('#' + getTripLenghtInputID()).val()
	queryObj.costLimit = $('#' + getCostLimitInputID()).val()

	$.ajax({
		type : 'get',
		url : "Search/Flights",
		data : queryObj,
		success : function(data) {
			alert("deta = " + data)
		}
	});

}

function onSearchHotels() {
	$.get("Search/Hotels", function(data, status) {
		alert("Data: " + data + "\nStatus: " + status);
		window.location = getFileHref("views/hotels.html")
	});
}

function getDepartureAirportInputID() {
	return 'departure_airport'
}

function getDepartureStartDateInputID() {
	return 'departure_start_date'
}

function getDepartureEndDateInputID() {
	return 'departure_end_date'
}

function getOnlyDirectFlightsInputID() {
	return 'only_direct_flights'
}

function getTripLenghtInputID() {
	return 'trip_length'
}

function getCostLimitInputID() {
	return 'cost_limit'
}
