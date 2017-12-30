package model;

import java.util.Date;

public class Event {

	private Airport mLocation = null;
	private Date mStartDate = null;
	private Date mEndDate = null;
	
	public Event(Airport location, Date startDate, Date endDate) {
		mLocation = location;
		mStartDate = startDate;
		mEndDate = endDate;
	}
	
	public Airport getLocation() {
		return mLocation;
	}
	
	public Date getStartDate() {
		return mStartDate;
	}
	
	public Date getEndDate() {
		return mEndDate;
	}
}
