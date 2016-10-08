package com.lifewalk;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ForecastByLocation {

  public static void main(String[] args) {
	  /*Forecast myForecast = new Forecast();
	  myForecast.printHighTemps();
	  myForecast.printLowTemps();
	  myForecast.printHrlyTemps();
	  myForecast.printHrlyPrec();
	  myForecast.printCloudCover();
	  myForecast.printLiqPrecAmt();
	  myForecast.printSnowAmt(); */
	  
	  WeatherFactory myFactory = new WalkForecast();
	  // myFactory.displayNew();
	  myFactory.nowCast();
  }
}