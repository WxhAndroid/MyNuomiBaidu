package com.wxh.project.baiduNuomi.update;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class UpdateHandler extends DefaultHandler{
	
	private Update update;
	
	public Update getUpdate() {
		return update;
	}
	private static final int TITLE = 1;
	private static final int MESSAGE = 2;
	private static final int VERSION = 3;
	private int currentIndex = 0;
	
	@Override
	public void startDocument() throws SAXException {
		update = new Update();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("title")) {
			currentIndex = TITLE;
		} else if (qName.equals("message")) {
			currentIndex = MESSAGE;
		} else if (qName.equals("version")) {
			currentIndex = VERSION;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length);
		switch (currentIndex) {
		case 1:
			update.setTitle(value);
			break;
		case 2:
			update.setMessage(value);
			break;
		case 3:
			update.setVersion(Integer.parseInt(value));
			break;
		default:
			break;
		}
		//÷√0
		currentIndex = 0;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	}
	
	@Override
	public void endDocument() throws SAXException {
	}
}
