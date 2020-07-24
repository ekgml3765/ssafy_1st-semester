package com.ssafy;

public class MovieMgr {

	//싱글패턴 객체생성
	private static MovieMgr mgr;
	
	public static MovieMgr getMgr() {
		if( mgr == null)
			mgr = new MovieMgr();
		return mgr;
	}

	//생성자
	private MovieMgr() {
		
	}

	//영화 배열
	private Movie[] movies = new Movie[100];
	private int index; //영화배열 갯수
	
	public void add(Movie m) {
		movies[index] = m;
		index++;
		
	}

	//영화 정보 전체검색
	public Movie[] search() {
		return movies;		
	}

	// 영화명 검색
	public Movie[] search(String title) {
		
		Movie[] mv = new Movie [100];
		int count = 0;
		for(int i = 0; i < index; i++) {
			if(movies[i].getTitle().contains(title)) {
				mv[count] = movies[i];
				count++;		
			}
				
		}
		
		return mv;
	}

	
	// 감독명으로 검색
	public Movie[] searchDirector(String name) {
		
		Movie[] mv = new Movie [getSize()];
		int count = 0;
		for(int i = 0; i < index; i++) {
			if(movies[i].getDirector().contains(name)) {
				mv[count] = movies[i];
				count++;
			}
				
		}
		
		return mv;
	}
	

	// 영화 장르별 검색
	public Movie[] searchGenre(String genre) {
		
		Movie[] mv = new Movie [100];
		int count = 0;
		for(int i = 0; i < index; i++) {
			if(movies[i].getGenre().contains(genre)) {
				mv[count] = movies[i];
				count++;
			}
				
		}
		
		return mv;
	}

	// 영화 정보 삭제
	public void delete(String title) {
		
		
		for(int i = 0; i < index; i++) {
			if(movies[i].getTitle().equals(title)) {
				movies[i] = null;		
			}
				
		}
		
			
		
	}

	/** 저장된 영화 정보의 갯수를 리턴한다.*/ 
	public int getSize() {
		return index;
	}

}
