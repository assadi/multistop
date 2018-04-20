package model;

import java.time.LocalDate;

public class Event {

	private Airport mLocation = null;
	private LocalDate mStartDate = null;
	private LocalDate mEndDate = null;
	
	public Event(Airport location, LocalDate startDate, LocalDate endDate) {
		mLocation = location;
		mStartDate = startDate;
		mEndDate = endDate;
	}
	
	public Airport getLocation() {
		return mLocation;
	}
	
	public LocalDate getStartDate() {
		return mStartDate;
	}
	
	public LocalDate getEndDate() {
		return mEndDate;
	}
}
