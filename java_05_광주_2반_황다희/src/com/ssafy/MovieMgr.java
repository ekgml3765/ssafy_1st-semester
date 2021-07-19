package com.ssafy;

public class MovieMgr {

	//�̱����� ��ü����
	private static MovieMgr mgr;
	
	public static MovieMgr getMgr() {
		if( mgr == null)
			mgr = new MovieMgr();
		return mgr;
	}

	//������
	private MovieMgr() {
		
	}

	//��ȭ �迭
	private Movie[] movies = new Movie[100];
	private int index; //��ȭ�迭 ����
	
	public void add(Movie m) {
		movies[index] = m;
		index++;
		
	}

	//��ȭ ���� ��ü�˻�
	public Movie[] search() {
		return movies;		
	}

	// ��ȭ�� �˻�
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

	
	// ���������� �˻�
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
	

	// ��ȭ �帣�� �˻�
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

	// ��ȭ ���� ����
	public void delete(String title) {
		
		
		for(int i = 0; i < index; i++) {
			if(movies[i].getTitle().equals(title)) {
				movies[i] = null;		
			}
				
		}
		
			
		
	}

	/** ����� ��ȭ ������ ������ �����Ѵ�.*/ 
	public int getSize() {
		return index;
	}

}
