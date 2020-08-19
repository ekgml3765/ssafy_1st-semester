package hw_0818.src;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		boolean flag = true;

		ProductMgrImpl pdmgr = ProductMgrImpl.getInstance();

		while (flag) {

			System.out.println("============================");
			System.out.println("1. 상품 정보 입력");
			System.out.println("2. 상품 정보 전체검색");
			System.out.println("3. 상품 번호로 검색");
			System.out.println("4. 상품명으로 검색 (상품명 부분검색가능)");
			System.out.println("5. TV정보만 검색");
			System.out.println("6. 냉장고만 검색");
			System.out.println("7. 전체 재고 상품 금액을 구하기");
			System.out.println("8. 상품번호로 삭제");
			System.out.println("9. 상품 가격 변경하기");
			System.out.println("10. ~이상의 TV 또는 냉장고 검색");
			System.out.println("11. 저장하기");
			System.out.println("0. 종료");
			System.out.println("==============원하는 번호를 입력하시오:");
			int n = sc.nextInt();

			switch (n) {
			case 0: {
				System.out.println("종료합니다.");
				flag = false;
				break;
			}

			case 1: { // 상품 정보 입력
				System.out.println(" TV or 냉장고 인지 선택하시오.");
				String info = sc.next();
				System.out.println("상품 번호를 입력하세요.");
				int p_num = sc.nextInt();
				System.out.println("상품명을  입력하세요.");
				String p_Name = sc.next();
				System.out.println("상품 가격을 입력하세요");
				int price = sc.nextInt();
				System.out.println("상품 수량을 입력하세요.");
				int amount = sc.nextInt();
				System.out.println("상품 용량을 입력하세요.");

				int capacity = sc.nextInt();
				if (info.equals("TV")) {
					TV tv = new TV(p_num, p_Name, price, info, amount, capacity);
					try {
						pdmgr.add(tv);
						//저장하자
						//pdmgr.save();
					} catch (DuplicateException e) {
						System.out.println("상품 번호가 같으며 이미 존재하는 상품입니다.");
					}
				}
				if (info.equals("냉장고")) {
					Refrigerator re = new Refrigerator(p_num, p_Name, price, info, amount, capacity);
					try {
						pdmgr.add(re);
						//pdmgr.save();
					} catch (DuplicateException e) {
						System.out.println("상품 번호가 같으며 이미 존재하는 상품입니다.");

					}
				}
				break;
			}

			case 2: {// 상품 정보 전체 검색
				ArrayList<Product> list = new ArrayList<>();
				list = pdmgr.serch();
				for (int i = 0; i < pdmgr.getSize(); i++)
					System.out.println(list.get(i));
				break;
			}

			case 3: {// 상품 번호검색
				System.out.println("상품 번호를 입력해주세요:");
				int number = sc.nextInt();
				try {
					for (Product x : pdmgr.serch_Num(number)) {
						if (x != null) {
							System.out.println(x);
						}
					}
				} catch (CodeNotFoundException e) {
					System.out.println("상품번호가 존재하지 않습니다");
				}
				break;
			}

			case 4: {// 상품명으로 검색
				System.out.println("상품 이름을 입력해주세요:");
				String name = sc.next();
				for (Product x : pdmgr.serch_Name(name)) {
					if (x != null) {
						System.out.println(x);
					}
				}
				break;
			}

			case 5: {// TV정보만 검색
				for (Product x : pdmgr.serch_Info("TV")) {
					if (x != null) {
						System.out.println(x);
					}
				}
				break;
			}

			case 6: { // 냉장고 정보만검색
				for (Product x : pdmgr.serch_Info("냉장고")) {
					if (x != null) {
						System.out.println(x);
					}
				}

				break;
			}
			case 7: {// 전체재고상품금액 구하기
				System.out.println(pdmgr.sum_price() + "원 입니다.");
				break;
			}
			case 8: { // 상품번호로 삭제
				System.out.println("삭제할 상품 번호를 입력하세요:");
				int num = sc.nextInt();
				boolean b = pdmgr.delete(num);
				if (b)
					System.out.println("삭제되었습니다.");
				else
					System.out.println("삭제할 상품이 없습니다.");
				break;
			}

			case 9: {// 상품가격 변경
				System.out.println("상품 번호를 입력하세요:");
				int num = sc.nextInt();
				System.out.println("변경할 가격을 입력하세요:");
				int price = sc.nextInt();
				pdmgr.price_change(num, price);
				System.out.println("변경되었습니다.");
				break;
			}

			case 10: {
				System.out.println("무엇을 검색하시겠습니까? TV or 냉장고 ");
				String s = sc.next();
				System.out.println("용량 크기를 숫자로만 입력하세요.");
				int size = sc.nextInt();
				try {
					for (Product x : pdmgr.serch_Info2(s, size)) {
						if (x != null) {
							System.out.println(x);
						}
					}
				} catch (ProductNotFoundException e) {
					System.out.println("그런 상품은 존재하지 않습니다.");
				}
				break;
			}
			
			case 11: {
				pdmgr.save();
			}
			

			default:
				break;
			}
		}
	}

}
