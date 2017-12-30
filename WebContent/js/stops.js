/**
 * A scripts that handles the stops table
 */


function getStopsTableID() {
	return 'stop_table'
}

function addStop() {
	var line_number = getAmountRowsInStopTable();
	var row = createStopTableRow(line_number)
	$('#' + getStopsTableID()).append(row)
	var rowID = row.attr('id')
	var rowInputID = getAirportInputID(rowID)
    assignAirportsAutocomplete("#" + rowInputID);
}

function getStopsTableRows() {
	allRows = $('#' + getStopsTableID() + ' tr')
	var result = []
	for (var i = 1; i < allRows.length; i++) {
		result.push(allRows[i])
	}
	return result
}

function getAmountRowsInStopTable() {
	return getStopsTableRows().length;
}

function getAirportInputID(row_id) {
	return row_id.toString() + '_airport'
}

function getDaysInputID(row_id) {
	return row_id.toString() + '_days'
}

function deleteStopRow(btn) {

	var row = btn.parentNode.parentNode;
	
	row.parentNode.removeChild(row);
}

function createStopTableRow(line_number) {
	row = $('<tr>')
	row.attr('class', 'StopRow')
	row.attr('id', line_number.toString())
	
	numberCell = $('<td>')
	numberCell.append(line_number)
	
	airportCell = $('<td>')
	airportInput = $('<input>')
	airportInput.attr('id', getAirportInputID(line_number))
	airportInput.attr('placeholder', 'Country, city or airport')
	airportCell.append(airportInput)
	
	daysCell = $('<td>')
	daysInput = $('<input>')
	daysInput.attr('id', getDaysInputID(line_number))
	daysInput.attr('type', 'text')
	daysInput.attr('placeholder', 'Days')
	daysCell.append(daysInput)
	
	removeCell = $('<td>')
	removeButton = $('<input>')
	removeButton.attr('type', 'button')
	removeButton.attr('value', 'Remove')
	removeButton.attr('onClick', 'deleteStopRow(this)')
	removeCell.append(removeButton)
	
	row.append(numberCell)
	row.append(airportCell)
	row.append(daysCell)
	row.append(removeCell)
	
	return row
}

/*
 * Return the stops as an array of Stops object (array of Jsons) by reading the stops table
 * */
function getStops() {
	
	stopsTableRows = getStopsTableRows()
	stops = []
	for (var i = 0; i < stopsTableRows.length; i++) {
		row = stopsTableRows[i]
		rowID = row.id
		
		airportInputID = getAirportInputID(rowID)
		daysInputID = getDaysInputID(rowID)
		
		airport = $("#" + airportInputID).val()
		days = $("#" + daysInputID).val()
		
		var stopObject = Object()
		stopObject.airport = airport
		stopObject.days = days
		
		stops.push(stopObject)
	}
	
	return stops
}
