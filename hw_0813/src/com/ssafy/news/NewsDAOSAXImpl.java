package com.ssafy.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NewsDAOSAXImpl implements INewsDAO {

	static List<News> list = new ArrayList<News>();
	
	
	@Override
	public List<News> getNewsList(String url) {
		// TODO Auto-generated method stub
		NewsDAOSAXImpl.connectNews(url);
		return list;
	}

	@Override
	public News search(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static public void connectNews(String url) {
		
		
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			SAXHandler handler = new SAXHandler();
			saxParser.parse(url, handler);

			list = handler.list;

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	
}

class SAXHandler extends DefaultHandler {
	
	static List<News> list = new ArrayList<News>();

	News N;
	private StringBuilder data = null;
	
	boolean btitle = false;
	boolean bDec = false;
	boolean bLink = false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equals("title")) {
			btitle = true;
			N = new News(); //객체 생성
		}
		else if(qName.equals("description")) {
			bDec= true;
		}
		else if(qName.equals("link")) {
			bLink = true;
		}
		
		data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(btitle) {
			N.setTitle(data.toString());
			btitle = false;
		}
		else if(bDec) {
			N.setDesc(data.toString());
			bDec= false;
			list.add(N); //맨 끝 태그를 만나면 리스트에 추가
		}
		else if(bLink) {
			N.setLink(data.toString());
			bLink = false;
		}
			
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
	
	
	
}
