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

    try {

	File file = new File("/Users/BrianFerry/WeatherApp/pointForecast.xml");

	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();

	Document doc = dBuilder.parse(file);

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	if (doc.hasChildNodes()) {

		// printNote(doc.getChildNodes());

	}
	
	NodeList myList = doc.getChildNodes();

	// root node
	Node myNode = myList.item(0);
	NodeList subList = myNode.getChildNodes();
	System.out.println(myNode.getNodeName() + " " + myNode.getNodeValue());

	// ith period entry 
	Node subNode = subList.item(10);
	NodeList actualData = subNode.getChildNodes();
	
	// temp(erature) is 6th item in period entry
	Node actualNode = actualData.item(6);
	NamedNodeMap attributeNode = actualNode.getAttributes();
	
	Node subAttributeNode = attributeNode.item(1);
	System.out.println(actualNode.getNodeName() + " " + subAttributeNode.getNodeValue());
	
    } catch (Exception e) {
	System.out.println(e.getMessage());
    }

  }

  private static void printNote(NodeList nodeList) {

    for (int count = 0; count < nodeList.getLength(); count++) {

	Node tempNode = nodeList.item(count);

	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

		// get node name and value
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		System.out.println("Node Value =" + tempNode.getTextContent());

		if (tempNode.hasAttributes()) {

			// get attributes names and values
			NamedNodeMap nodeMap = tempNode.getAttributes();

			for (int i = 0; i < nodeMap.getLength(); i++) {

				Node node = nodeMap.item(i);
				System.out.println("attr name : " + node.getNodeName());
				System.out.println("attr value : " + node.getNodeValue());

			}

		}

		if (tempNode.hasChildNodes()) {

			// loop again if has child nodes
			printNote(tempNode.getChildNodes());

		}

		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

	}

    }

  }

}