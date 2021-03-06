package com.lifewalk;

abstract public class WeatherFactory {
	WeatherFactory() {
		
	}
	private Forecast myForecast = new Forecast();
	
	protected int [] highTemp = myForecast.getHighTemps();
	protected int [] lowTemps = myForecast.getLowTemps();
	protected int [] probPrecHrly = myForecast.getProbPrecHrly();
	protected int [] hrlyTemp = myForecast.getHrlyTemps();
	protected int [] cloudCover = myForecast.getCloudCover();
	protected int [] windSpeed = myForecast.getWindSpeed();
	protected int [] windDirection = myForecast.getWindDirection();
	protected int [] relHumid = myForecast.getRelHumid();

	protected double [] liqPrecAmt = myForecast.getLiqPrecAmt();
	protected double [] snowAmt = myForecast.getSnowAmt();
	
	protected abstract int hrlyTempDec();
	protected abstract int probPrecDec();
	protected abstract int cloudCoverDec();
	protected abstract int liqPrecDec();
	protected abstract void displayNew();
	protected abstract void nowCast();
}
