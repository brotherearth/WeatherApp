package com.lifewalk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Forecast {
	private int [] errorCatch = {1, 2, 3, 4};
	private int [] highTemps;
	private int [] lowTemps;
	private int [] hrlyTemps;
	private int [] probPrecHrly;
	private int [] cloudCover;
	private int [] windDirection;
	private int [] windSpeed;
	private int [] relHumid;

	private double [] doubleErrorCatch = {1.1, 2.2, 3.3};
	private double [] liqPrecAmt;
	private double [] snowAmt;
	
	private Parsewxml myParse = new Parsewxml();
	Document doc = myParse.getXML();
	
	public Forecast() {
		popHighTemps();
		popLowTemps();
		popHrlyPrec();
		popCloudCover();
		popLiqPrecAmt();
		popSnowAmt();
		popHrlyTemps();
		popWindDirection();
		popWindSpeed();
		popRelHumid();
	}

	public int [] getWindDirection() {
		return windDirection;
	}
	
	public int [] getWindSpeed() {
		return windSpeed;
	}
	
	public int [] getRelHumid() {
		return relHumid;
	}
	
	public int [] getHighTemps() {
		return highTemps;
	}
	
	public int [] getLowTemps() {
		return lowTemps;
	}
	
	public int [] getProbPrecHrly() {
		return probPrecHrly;
	}
	
	public int [] getCloudCover() {
		return cloudCover;
	}
	
	public int [] getHrlyTemps() {
		return hrlyTemps;
	}
	
	public double [] getLiqPrecAmt() {
		return liqPrecAmt;
	}
	
	public double [] getSnowAmt() {
		return snowAmt;
	}
	
	public void popHrlyTemps() {
		hrlyTemps = getSection(doc, "temperature", "name", "Temperature", "value");
	}
	
	public void popHighTemps() {
		highTemps = getSection(doc, "temperature", "name", "Daily Maximum Temperature", "value");
	}

	public void popLowTemps() {
		lowTemps = getSection(doc, "temperature", "name", "Daily Minimum Temperature", "value");			
	}
	
	public void popHrlyPrec() {
		probPrecHrly = getSection(doc, "probability-of-precipitation", "name", "12 Hourly Probability of Precipitation", "value");			
	}
	
	public void popCloudCover() {
		cloudCover = getSection(doc, "cloud-amount", "name", "Cloud Cover Amount", "value");			
	}
	
	public void popWindDirection() {
		windDirection = getSection(doc, "direction", "name", "Wind Direction", "value");
	}
	
	public void popWindSpeed() {
		windSpeed = getSection(doc, "wind-speed", "name", "Wind Speed", "value");
	}
	
	public void popRelHumid() {
		relHumid = getSection(doc, "humidity", "name", "Relative Humidity", "value");
	}
	
	public void popLiqPrecAmt() {
		liqPrecAmt = getDoubleSection(doc, "precipitation", "name", "Liquid Precipitation Amount", "value");			
	}
	
	public void popSnowAmt() {
		snowAmt = getDoubleSection(doc, "precipitation", "name", "Snow Amount", "value");			
	}
	
	// populate double arrays with data from document (weather XML file)
	public double [] getDoubleSection(Document doc, String parent, String child, String childTxt, String sibling) {
		
		NodeList nList = doc.getElementsByTagName(parent);							// find matching categories and generate list
		for ( int i = 0; i < nList.getLength(); i++ ) {								// iterate through the list
			
			Node nNode = nList.item(i);												// make each matching element a node
			
			if ( nNode.getNodeType() == Node.ELEMENT_NODE ) {		
				
				Element eElement = (Element) nNode;									// make node an element
				
				if( eElement.getElementsByTagName(child)							// if category is matching				
						.item(0).getTextContent().equalsIgnoreCase(childTxt) ) {
											
					/* System.out.print("\nSection: " +								// print current section
									nNode.getNodeName() + " \n");	
					System.out.println("Name: "+ eElement							// print name
							.getElementsByTagName(child)								
							.item(0).getTextContent());*/
					double [] myData = new double[eElement.getElementsByTagName(sibling).getLength()];

					for ( int j = 0; j < eElement.getElementsByTagName(sibling)		// iterate through the element's values
										.getLength(); j++ ) {	
						myData[j] = Double.parseDouble(eElement.getElementsByTagName(sibling).item(j).getTextContent());
						/* System.out.println(sibling + ": " + eElement				// print out values
							.getElementsByTagName(sibling)
							.item(j).getTextContent());*/ 
					}
					return myData;

					/*if( eElement.getElementsByTagName(child)						// get corresponding time			
							.item(0).getNodeName()
							.equalsIgnoreCase("layout-key") == false ){
						
						getSection(doc, "time-layout", "layout-key",				// get time
								eElement.getAttribute("time-layout"), "start-valid-time");
					*/
					}
				}
			}
		// not sure what to do with this yet
		return doubleErrorCatch;
		}
	
	// populate int arrays with data from document (weather XML file)
	public int [] getSection(Document doc, String parent, String child, String childTxt, String sibling) {
		
		NodeList nList = doc.getElementsByTagName(parent);							// find matching categories and generate list
		for ( int i = 0; i < nList.getLength(); i++ ) {								// iterate through the list
			
			Node nNode = nList.item(i);												// make each matching element a node
			
			if ( nNode.getNodeType() == Node.ELEMENT_NODE ) {		
				
				Element eElement = (Element) nNode;									// make node an element
				
				if( eElement.getElementsByTagName(child)							// if category is matching				
						.item(0).getTextContent().equalsIgnoreCase(childTxt) ) {
											
					/* System.out.print("\nSection: " +								// print current section
									nNode.getNodeName() + " \n");	
					System.out.println("Name: "+ eElement							// print name
							.getElementsByTagName(child)								
							.item(0).getTextContent());*/ 
					int [] myData = new int[eElement.getElementsByTagName(sibling).getLength()];

					for ( int j = 0; j < eElement.getElementsByTagName(sibling)		// iterate through the element's values
										.getLength(); j++ ) {	
						myData[j] = Integer.parseInt(eElement.getElementsByTagName(sibling).item(j).getTextContent());
						/* System.out.println(sibling + ": " + eElement				// print out values
							.getElementsByTagName(sibling)
							.item(j).getTextContent());*/ 
					}
					return myData;

					/*if( eElement.getElementsByTagName(child)						// get corresponding time			
							.item(0).getNodeName()
							.equalsIgnoreCase("layout-key") == false ){
						
						getSection(doc, "time-layout", "layout-key",				// get time
								eElement.getAttribute("time-layout"), "start-valid-time");
					*/
					}
				}
			}
		return errorCatch;
		}
}
