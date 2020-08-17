package com.ssafy.news;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewsDAODOMImpl test = new NewsDAODOMImpl();
		
		
		
		for(News N : test.getNewsList("http://rss.etnews.com/Section902.xml")) {
			System.out.println(N);
			}
		}

}
