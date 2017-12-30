package model;

public class Stop {

	private Airport mAirport = null;
	private int mDaysCount = 0;
	
	public Stop(Airport airport, int daysCount) {
		mAirport = airport;
		mDaysCount = daysCount;
	}
	
	public Airport getAirport() {
		return mAirport;
	}
	
	public int getDaysCount() {
		return mDaysCount;
	}
}
