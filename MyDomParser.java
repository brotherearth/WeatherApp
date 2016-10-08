/* Ariel Niedo Jr.
 * NOAA Weather XML Parser
 * Downloads latest weather data from NOAA and parses data
 */
package com.lifewalk;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyDomParser {
	
	public static void main(String[] args) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
		try {
			
			/* Retrieve XML from weather.gov */
			URL url = new URL("http://graphical.weather.gov/xml/SOAP_server/ndfdXMLclient.php?whichClient=NDFDgenMultiZipCode&lat=&lon=&listLatLon=&lat1=&lon1=&lat2=&lon2=&resolutionSub=&listLat1=&listLon1=&listLat2=&listLon2=&resolutionList=&endPoint1Lat=&endPoint1Lon=&endPoint2Lat=&endPoint2Lon=&listEndPoint1Lat=&listEndPoint1Lon=&listEndPoint2Lat=&listEndPoint2Lon=&zipCodeList=97219&listZipCodeList=&centerPointLat=&centerPointLon=&distanceLat=&distanceLon=&resolutionSquare=&listCenterPointLat=&listCenterPointLon=&listDistanceLat=&listDistanceLon=&listResolutionSquare=&citiesLevel=&listCitiesLevel=&sector=&gmlListLatLon=&featureType=&requestedTime=&startTime=&endTime=&compType=&propertyName=&product=time-series&begin=2004-01-01T00%3A00%3A00&end=2020-10-08T00%3A00%3A00&Unit=e&maxt=maxt&mint=mint&temp=temp&qpf=qpf&pop12=pop12&snow=snow&dew=dew&wspd=wspd&wdir=wdir&sky=sky&wx=wx&Submit=Submit");
			//new URL("http://graphical.weather.gov/xml/SOAP_server/ndfdXMLclient.php?whichClient=NDFDgenMultiZipCode&lat=&lon=&listLatLon=&lat1=&lon1=&lat2=&lon2=&resolutionSub=&listLat1=&listLon1=&listLat2=&listLon2=&resolutionList=&endPoint1Lat=&endPoint1Lon=&endPoint2Lat=&endPoint2Lon=&listEndPoint1Lat=&listEndPoint1Lon=&listEndPoint2Lat=&listEndPoint2Lon=&zipCodeList=97203&listZipCodeList=&centerPointLat=&centerPointLon=&distanceLat=&distanceLon=&resolutionSquare=&listCenterPointLat=&listCenterPointLon=&listDistanceLat=&listDistanceLon=&listResolutionSquare=&citiesLevel=&listCitiesLevel=&sector=&gmlListLatLon=&featureType=&requestedTime=&startTime=&endTime=&compType=&propertyName=&product=time-series&begin=2004-01-01T00%3A00%3A00&end=2020-09-18T00%3A00%3A00&Unit=e&maxt=maxt&mint=mint&temp=temp&qpf=qpf&pop12=pop12&snow=snow&dew=dew&wspd=wspd&wdir=wdir&sky=sky&wx=wx&waveh=waveh&icons=icons&rh=rh&appt=appt&Submit=Submit");
			
			
			InputStream stream = url.openStream();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(stream);						// Online XML
	
			/* Local XML Copy Test Case 
			Document doc = builder.parse("testweather.xml"); */ 
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			/* getSection(Document doc, String parent, String child, String childTxt, String sibling) */ 
			getSection(doc, "temperature", "name", "Daily Maximum Temperature", "value");			
			getSection(doc, "temperature", "name", "Temperature", "value");
			getSection(doc, "precipitation", "name", "Snow Amount", "value");
			getSection(doc, "probability-of-precipitation", "name", "12 Hourly Probability of Precipitation", "value");
			getSection(doc, "humidity", "name", "Relative Humidity", "value");
			getSection(doc, "wind-speed", "name", "Wind Speed Gust", "value");
			getSection(doc, "cloud-amount", "name", "Cloud Cover Amount", "value");
					
			// Generate 7-day forecast	
			// Generate 3-day forecast

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getSection(Document doc, String parent, String child, String childTxt, String sibling) {
		
		NodeList nList = doc.getElementsByTagName(parent);							// find matching categories and generate list
		
		for ( int i = 0; i < nList.getLength(); i++ ) {								// iterate through the list
			
			Node nNode = nList.item(i);												// make each matching element a node
			
			if ( nNode.getNodeType() == Node.ELEMENT_NODE ) {		
				
				Element eElement = (Element) nNode;									// make node an element
				
				if( eElement.getElementsByTagName(child)							// if category is matching				
						.item(0).getTextContent().equalsIgnoreCase(childTxt) ) {
											
					System.out.print("\nSection: " +								// print current section
									nNode.getNodeName() + " \n");	
					System.out.println("Name: "+ eElement							// print name
							.getElementsByTagName(child)								
							.item(0).getTextContent());
					
					for ( int j = 0; j < eElement.getElementsByTagName(sibling)		// iterate through the element's values
										.getLength(); j++ ) {	
						
						System.out.println(sibling + ": " + eElement				// print out values
							.getElementsByTagName(sibling)
							.item(j).getTextContent());
					}
					
					if( eElement.getElementsByTagName(child)						// get corresponding time			
							.item(0).getNodeName()
							.equalsIgnoreCase("layout-key") == false ){
						
						getSection(doc, "time-layout", "layout-key",				// get time
								eElement.getAttribute("time-layout"), "start-valid-time");
					}
				}
			}
		}
	}
	
	// Inserts values of weekly's weather conditions in arrays
	public static void generateWeekly(Document doc){
		
		// Today's date and time
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
		
		// max temp
		getSection(doc, "temperature", "name", "Daily Maximum Temperature", "value");		
	}

}