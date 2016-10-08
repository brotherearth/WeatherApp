package com.lifewalk;

public class WalkForecast extends WeatherFactory {
	public WalkForecast() {

	}

	public void nowCast() {
		if (getFavorability() > 6) {
			System.out.println("It's a great day for a walk fool!");
		}
		else if (getFavorability() > 2) {
			System.out.println("It's alright out, may as well go for a walk");
		}
		else
			System.out.println("Stay inside, it's especially nasty out");
	}
	protected int getFavorability() {
		return (hrlyTempDec() + probPrecDec() + cloudCoverDec() + liqPrecDec());
	}
	// is temp favorable in next 3 hours
	protected int hrlyTempDec() {
		// unfavorable
		if (hrlyTemp[0] > 89 || hrlyTemp[0] < 40) 
		return 0;
		// Marginal
		else if (hrlyTemp[0] > 79 || hrlyTemp[0] < 59)
			return 1;
		// Favorable
		return 2;
	}

	// is precip prob favorable in next 12 hours
	protected int probPrecDec() {
		// favorable
		if (probPrecHrly[0] < 20)
			return 2;
		// marginal
		else if (probPrecHrly[0] > 60)
			return 1;
		// unfavorable
		else
			return 0;
	}

	// is cloud cover favorable in next 12 hours
	protected int cloudCoverDec() {
		// favorable
		if (cloudCover[0] < 20)
			return 2;
		// marginal
		else if (cloudCover[0] < 60)
			return 1;
		// unfavorable
		else
			return 0;	
		}

	// is the amount of rain in next 12 hours favorable
	protected int liqPrecDec() {
		// unfavorable
		if (liqPrecAmt[0] > 0.1)
			return 0;
		// marginal
		else if (liqPrecAmt[0] > 0)
			return 1;
		// favorable
		else
			return 2;
	}
	
	
}
