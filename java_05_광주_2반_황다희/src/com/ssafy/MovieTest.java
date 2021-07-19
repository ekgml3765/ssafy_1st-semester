package com.ssafy;

import java.util.Scanner;

public class MovieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int num;

		// MovieMgr ��ü ����
		MovieMgr mgr = MovieMgr.getMgr();

		System.out.println("<<< ��ȭ ���� ���α׷� >>>");
		System.out.println("1. ��ȭ ���� �Է�");
		System.out.println("2. ��ȭ ���� ��ü �˻�");
		System.out.println("3. ��ȭ�� �˻�");
		System.out.println("4. ��ȭ �帣�� �˻�");
		System.out.println("5. ��ȭ ���� ����");
		System.out.println("0. ����");

		do {

			System.out.println("���ϴ� ��ȣ�� �����ϼ���.");
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
				// ���
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
