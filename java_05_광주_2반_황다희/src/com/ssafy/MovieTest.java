package com.ssafy;

import java.util.Scanner;

public class MovieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int num;

		// MovieMgr 객체 생성
		MovieMgr mgr = MovieMgr.getMgr();

		System.out.println("<<< 영화 관리 프로그램 >>>");
		System.out.println("1. 영화 정보 입력");
		System.out.println("2. 영화 정보 전체 검색");
		System.out.println("3. 영화명 검색");
		System.out.println("4. 영화 장르별 검색");
		System.out.println("5. 영화 정보 삭제");
		System.out.println("0. 종료");

		do {

			System.out.println("원하는 번호를 선택하세요.");
			num = sc.nextInt();

			switch (num) {
			case 1: {

				String s1 = sc.next();
				String s2 = sc.next();
				int j = sc.nextInt();
				String s3 = sc.next();

				Movie m = new Movie(s1, s2, j, s3);
				mgr.add(m);
				break;
			}

			case 2: {

				Movie[] mv = mgr.search();
				// 출력
				for (int i = 0; i < mv.length; i++) {

					if (mv[i] == null)
						continue;
					System.out.println(mv[i]);
				}

				break;
			}

			case 3: {

				String s = sc.next();
				Movie[] mv = mgr.search(s);

				for (int i = 0; i < mv.length; i++) {
					if (mv[i] == null)
						continue;
					System.out.println(mv[i]);
				}

				break;
			}

			case 4: {

				String s = sc.next();
				Movie[] mv = mgr.searchGenre(s);

				for (int i = 0; i < mv.length; i++) {
					if (mv[i] == null)
						continue;
					System.out.println(mv[i]);
				}

				break;
			}

			case 5: {

				mgr.delete(sc.next());
				break;
			}

			}
		} while (num != 0);

	}

}
