package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trip {
	
	private List<TripDay> mDays = null;
	private Date mDate = null;
	
	public Trip() {
		mDays = new ArrayList<TripDay>();
	}
	
	public int getLength() { 
		return mDays.size();
	}
	
	public TripDay getDay(int index) { 
		return mDays.get(index);
	}
	
	public Date getDate(int index) { 
		return mDate;
	}
}
