package com.lifewalk;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class wxAttribute {
	
	public static void main(String[] args) {
		
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("tillamook.xml");
		NodeList timeList = doc.getElementsByTagName("ob");
		for(int i=0; i<timeList.getLength();i++){
			Node t = timeList.item(i);
			if(t.getNodeType()==Node.ELEMENT_NODE){
				Element time = (Element) t;

				String date = ((Node) time).getAttributeValue();
				System.out.print("Date: " + date + "\n" );
			}
		}
	} catch (ParserConfigurationException e) {
		e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}

}
