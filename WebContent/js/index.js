/**
 * 
 */

function getFileHref(file){
	var APP_NAME = "MultistopWeb"
	return window.location.origin + "/" + APP_NAME + "/" + file
}

function onSearchFlights(){
	window.location = getFileHref("views/flights.html")
}

function onSearchHotels(){
    $.get("Search/Hotels", function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
        window.location = getFileHref("views/hotels.html")
    });
}