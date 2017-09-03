package com.uz.eample;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RestrictionsHandler extends DefaultHandler {
	private List<String> apklist = new ArrayList<String>();
	private String currentNode = null;
	private String apkname = null;

	private final static String ELEMENTNAME = "apkname";

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("startDocument");
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("endDocument");
	}

	@Override
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		currentNode = name;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (ELEMENTNAME.equals(currentNode)) {
           if(ch != null){
        	   String value = new String(ch, start, length);
        	   apklist.add(value);
           }
		}
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		
		if (ELEMENTNAME.equals(name)) {
			apkname = null;
		}
		currentNode = null;
	}

	public List<String> getapklist() {
		return apklist;
	}
}
