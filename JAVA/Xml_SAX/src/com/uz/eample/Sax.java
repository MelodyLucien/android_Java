package com.uz.eample;



import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

	public class Sax {

		 public static void main(String[] args) throws ParserConfigurationException, Exception {

		  SAXParserFactory factory = SAXParserFactory.newInstance();

		  SAXParser sp = factory.newSAXParser();
		  
		  XMLReader reader = sp.getXMLReader();

/*		  BookListHandler handler = new BookListHandler();
		  reader.setContentHandler(handler);*/
		  
		  RestrictionsHandler handler = new RestrictionsHandler();
		  reader.setContentHandler(handler);
		  
		  reader.parse("src/restrictions.xml");

		  List<String> books = handler.getapklist();
		  System.out.println(books);
		 }
	}


