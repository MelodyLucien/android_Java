package com.uz.eample;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookListHandler extends DefaultHandler{
	 List books = new ArrayList();
	 String currentNode = null;
	 Book book = null;
	 @Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("startDocument");
	}
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("endDocument");
	}
	@Override
	 public void startElement(String uri, String localName, String name,
	   Attributes attributes) throws SAXException {
		System.out.println("startElement");
		System.out.println("nodename :"+name);
	     currentNode = name;
	  if("book".equals(name)){
	   book = new Book();
	  }
	 }
	 @Override
	 public void characters(char[] ch, int start, int length)
	   throws SAXException {
		 System.out.println("characters");
	  String value = new String(ch,start,length);
	  if("bookname".equals(currentNode)){
	   book.setName(value);
	  }
	  if("author".equals(currentNode)){
	   book.setAuthor(value);
	  }
	  if("price".equals(currentNode)){
	   book.setPrice(value);
	  }
	 }

	 @Override
	 public void endElement(String uri, String localName, String name)
	   throws SAXException {
		 System.out.println("endElement");
	  if("book".equals(name)){
	   books.add(book);
	   book = null;
	  }
	  currentNode = null;
	 }
	 public List getBooks(){
	  return books;
	 }
	}
	 

