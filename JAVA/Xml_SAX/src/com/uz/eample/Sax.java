package com.uz.eample;



import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
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
		  File file = new File("config","src");
		  
		  System.out.println("parent : "+file.getParent().toString());
		  
		  for (File f : file.listFiles()) {
			  if(f.getName().equals("restrictions.xml")){
				  System.out.println("match");
			  }
		   }
		  
		 
		  
		  System.out.println("dfsdafdsfds  "+file.getAbsolutePath());
		 

		  HashMap<String, String> books = handler.getApkNames();
		  
		  if (books.containsKey("7.apk")) {
			System.out.println("yes");
		  }
		  
		  System.out.println(books);
		 }
	}


