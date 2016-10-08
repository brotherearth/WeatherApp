package com.lifewalk;

public class WalkForecast extends WeatherFactory {
	public WalkForecast() {

	}

	public void displayNew() {
		for (int i = 0; i < windSpeed.length; i++) {
			System.out.println("Wind Speed = " + windSpeed[i] + "\nWind Direction = " + windDirection[i]
					+ "\nRelative Humidity: " + relHumid[i]);
		}
	}
	
	public void printForecast() {
		System.out.println("\nTemp:" + hrlyTemp[0]
				+"\nProbability of Precip in next 3 hrs: " + probPrecHrly[0]
						+"\nCloud Cover: " + getCloudCover()
						+"\n3hr Precip Totals: " + liqPrecAmt[0]
								+ "\nWind: " + getWindDirection()
								+ " at "+ windSpeed[0] + " Knots");
	}
	
	public void nowCast() {
		if (getFavorability() > 8) {
			System.out.println("It's a great day for a walk fool!");
		}
		else if (getFavorability() > 4) {
			System.out.println("It's alright out, may as well go for a walk");
		}
		else
			System.out.println("Stay inside, it's especially nasty out");
		printForecast();
	}
	
	protected int getFavorability() {
		return (hrlyTempDec() + probPrecDec() + cloudCoverDec() + liqPrecDec() + windSpeedDec());
	}
	
	protected String getCloudCover() {
		if (cloudCover[0] == 0)
			return "clear";
		else if (cloudCover[0] < 50)
			return "Few to Scattered Cloud";
		else if (cloudCover[0] == 100)
			return "Overcast";
		else
			return "Mostly Cloudy";
	}
	
	protected String getWindDirection() {
		if (windDirection[0] > 340 || windDirection[0] < 20)
			return "Out of the North";
		else if (windDirection[0] > 300)
			return "Out of the Northwest";
		else if (windDirection[0] > 250)
			return "Out of the West";
		else if (windDirection[0] > 200)
			return "Out of the Southwest";
		else if (windDirection[0] > 160)
			return "Out of the South";
		else if (windDirection[0] > 120)
			return "Out of the Southeast";
		else if (windDirection[0] > 70)
			return "Out of the East";
		else
			return "Out of the Northeast";
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
	
	protected int windSpeedDec() {
		if (windSpeed[0] > 20)
			return 0;
		else if (windSpeed[0] > 10)
			return 1;
		return 2;
	}
	
	
}
