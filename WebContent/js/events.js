/**
 * A script that handles the events table
 */

function getEventsTableID() {
	return 'event_table'
}

function addEvent() {
	var line_number = getAmountRowsInEventTable();
	var row = createEventsTableRow(line_number)
	$('#' + getEventsTableID()).append(row)
	var rowID = row.attr('id')
	var rowLocationInputID = getEventLocationInputID(rowID)
    assignAirportsAutocomplete("#" + rowLocationInputID);
}

function getEventsTableRows() {
	allRows = $('#' + getEventsTableID() + ' tr')
	var result = []
	for (var i = 1; i < allRows.length; i++) {
		result.push(allRows[i])
	}
	return result
}

function getAmountRowsInEventTable() {
	return getEventsTableRows().length;
}

function getEventLocationInputID(row_id) {
	return row_id.toString() + '_event_location'
}

function getStartDateInputID(row_id) {
	return row_id.toString() + '_event_start_date'
}

function getEndDateInputID(row_id) {
	return row_id.toString() + '_event_end_date'
}

function deleteEventRow(btn) {

	var row = btn.parentNode.parentNode;
	
	row.parentNode.removeChild(row);
}

function createEventsTableRow(line_number) {
	row = $('<tr>')
	row.attr('class', 'EventRow')
	row.attr('id', line_number.toString())
	
	locationCell = $('<td>')
	locationInput = $('<input>')
	locationInput.attr('id', getEventLocationInputID(line_number))
	locationInput.attr('placeholder', 'Event location')
	locationCell.append(locationInput)
	
	dateCell = $('<td>')
	startDateInput = $('<input>')
	startDateInput.attr('id', getStartDateInputID(line_number))
	startDateInput.attr('type', 'date')
	startDateInput.attr('placeholder', 'Event start')
	dateCell.append(startDateInput)
	dateCell.append('-')
	endDateInput = $('<input>')
	endDateInput.attr('id', getEndDateInputID(line_number))
	endDateInput.attr('type', 'date')
	endDateInput.attr('placeholder', 'Event end')
	dateCell.append(endDateInput)
	
	
	removeCell = $('<td>')
	removeButton = $('<input>')
	removeButton.attr('type', 'button')
	removeButton.attr('value', 'Remove')
	removeButton.attr('onClick', 'deleteEventRow(this)')
	removeCell.append(removeButton)
	
	row.append(locationCell)
	row.append(dateCell)
	row.append(removeCell)
	
	return row
}

/*
 * Return the events as an array of Event object (array of Jsons) by reading the events table
 * */
function getEvents() {
	
	eventsTableRows = getEventsTableRows()
	events = []
	for (var i = 0; i < eventsTableRows.length; i++) {
		row = eventsTableRows[i]
		rowID = row.id
		
		locationInputID = getEventLocationInputID(rowID)
		startDateInputID = getStartDateInputID(rowID)
		endDateInputID = getEndDateInputID(rowID)
		
		location = $("#" + locationInputID).val()
		startDate = $("#" + startDateInputID).val()
		endDate = $("#" + endDateInputID).val()
		
		var eventObject = Object()
		eventObject.location = location
		eventObject.startDate = startDate
		eventObject.endDate = endDate
		
		events.push(eventObject)
	}
	
	return events
}
