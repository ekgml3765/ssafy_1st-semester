package com.ssafy;

import java.util.Scanner;

public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		boolean flag = true;

		while (flag) {

			System.out.println("----------상품 관리 프로그램-----------");
			System.out.println("1. 상품 저장");
			System.out.println("2. 저장된 상품 조회");
			System.out.println("3. 상품 번호로 검색");
			System.out.println("4. 상품 번호로 삭제");
			System.out.println("5. 특정 가격 이하 상품 검색 ");
			System.out.println("0. 종료");
			System.out.println("--------------------------------");
			System.out.println("원하시는 번호를 입력하세요 :");

			int num = sc.nextInt();

			// 객체 배열 생성
			ProductMgr pr = ProductMgr.getInstance();

			switch (num) {

			// 상품저장
			case 1: {
				String name;
				int num1, price, num2;
				// 객체생성
				Product p = new Product();

				System.out.println("상품 번호를 입력하시오:");
				num1 = sc.nextInt();
				p.setP_number(num1);

				System.out.println("상품 이름을 입력하시오:");
				name = sc.next();
				p.setP_name(name);

				System.out.println("상품 가격을 입력하시오:");
				price = sc.nextInt();
				p.setPrice(price);

				System.out.println("상품 수량을 입력하시오:");
				num2 = sc.nextInt();
				p.setQuantity(num2);

				pr.add(p);
				System.out.println("상품 정보가 입력되었습니다.");
				break;
			}
			
			
			// 저장된 상품 조회
			case 2: {

				System.out.println(" < 상품 정보 목록 출력 >");
				// 객체 배열 생성
				Product[] p = pr.search();

				for (int i = 0; i < pr.getSize(); i++) {
					if (p[i] == null)
						continue;
					System.out.println(p[i]);
				}
				break;
			}

			// 상품 번호로 검색
			case 3: {
				

				System.out.println("상품 번호를 입력하세요:");
				
				// 객체 배열 생성
				Product[] p = pr.search(sc.nextInt());
				for (int i = 0; i < p.length; i++) {
					if (p[i] == null)
						continue;
					System.out.println(p[i]);
				}

				break;
			}
			// 상품 번호로 삭제
			case 4: {
				
				int delete;
				System.out.println("삭제하실 상품 번호를 입력하세요.");
				delete = sc.nextInt();
				
				pr.delete(delete);
				System.out.println("정상적으로 삭제 되었습니다.");
				break;
			}
			// 특정 가격 이하 상품 검색
			case 5: {
				
				int Sprice;
				System.out.println("특정 가격 이하의 상품을 검색합니다. 가격을 입력해주십시오: ");
				Sprice = sc.nextInt();
				
				Product[] p = pr.searchPrice(Sprice);
				for (int i = 0; i < p.length; i++) {
					if (p[i] == null)
						continue;
					System.out.println(p[i]);
				}
				
				
				break;
			}
			case 0: {
				System.out.println(" 종료합니다. ");
				flag = false;
				break;
			}

			}
		}
		sc.close();
	}

}
