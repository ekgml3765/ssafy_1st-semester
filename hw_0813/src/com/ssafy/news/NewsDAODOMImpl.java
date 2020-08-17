package com.ssafy.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NewsDAODOMImpl implements INewsDAO {

	static List<News> list = new ArrayList<News>();

	@Override
	public List<News> getNewsList(String url) {
		// TODO Auto-generated method stub
		
		NewsDAODOMImpl.connectNews(url);
		return list;

	}

	@Override
	public News search(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	static public void connectNews(String url) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder; // 빌더

		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(url); // 파싱해서 다큐먼트 문서로 만듦.
			doc.getDocumentElement().normalize();

			Element root = doc.getDocumentElement(); // 루트태그
			Element channel = (Element)root.getChildNodes().item(1);// 첫번째 자식을 받고
			NodeList childNodes = channel.getChildNodes();

			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i); // 노드리스트에 인덱스를 하나씩 받고
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node; // 엘리먼트로 바꿔주고
					String nodeName = element.getNodeName(); // 태그이름
					if (nodeName == "item") {
						// 자식 리스트 생성
						NodeList itemNodes = element.getChildNodes();
						News N = new News();
						for (int j = 0; j < itemNodes.getLength(); j++) {
							Node childNode = itemNodes.item(j); // 노드를 하나씩 가져옴

							if (childNode.getNodeType() == Node.ELEMENT_NODE) {// 노드 타입이면
								Element childElement = (Element) childNode;
								String childTextContent = childElement.getTextContent();
								String childNodeName = childElement.getNodeName();
								switch (childNodeName) {
								case "title": {
									N.setTitle(childTextContent);
									break;
								}
								case "link": {
									N.setLink(childTextContent);
									break;
								}
								case "description": {
									N.setDesc(childTextContent);
									break;
								}
								}

							}
						}

						list.add(N);

					}

				}
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

	}

}
