package hw_0810;

import java.util.Scanner;

class Product {
	private int p_Num, p_amount, p_price, info;
	private String p_Name;

	public int getInfo() {
		return info;
	}

	public void setInfo(int info) {
		this.info = info;
	}

	public int getP_Num() {
		return p_Num;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public void setP_Num(int p_Num) {
		this.p_Num = p_Num;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public int getP_amount() {
		return p_amount;
	}

	public void setP_amount(int p_amount) {
		this.p_amount = p_amount;
	}

	@Override
	public String toString() {
		return "[상품번호:" + p_Num + ", 상품수량:" + p_amount + ", 상품가격: " + p_price + ", 상품정보:" + info
				+ ", 상품이름:" + p_Name + "]";
	}
	
	

}

class TV extends Product {

	// 생성자
	TV(int p_Num, String p_Name, int p_amount, int p_price, int info) {
		super.setP_Num(p_Num);
		super.setP_Name(p_Name);
		super.setP_amount(p_amount);
		super.setP_price(p_price);
		super.setInfo(info);
	}
}

class Refrigerator extends Product {

	// 생성자
	Refrigerator(int p_Num, String p_Name, int p_amount, int p_price, int info) {
		super.setP_Num(p_Num);
		super.setP_Name(p_Name);
		super.setP_amount(p_amount);
		super.setP_price(p_price);
		super.setInfo(info);
	}

}

class ProductMgr {

	int idx = 0;
	Product pd[] = new Product[100]; // 객체배열

	private static ProductMgr instance;

	// 생성자를 private로 감춘다
	private ProductMgr() {
	}

	// 게터
	public static ProductMgr getInstance() {
		if (instance == null)
			instance = new ProductMgr();
		return instance;
	}

	public int getSize() {
		return idx;
	}

	// 저장
	public void add(Product p) {
		pd[idx] = p;
		idx++;
	}

	// 전체검색
	public Product[] serch() {
		return pd;
	}

	// 상품번호 검색
	public Product[] serch_n(int num) {

		Product[] result = new Product[idx];
		int index = 0;
		for (int i = 0; i < idx; i++) {
			if (pd[i].getP_Num() == num) {
				result[index++] = pd[i];
			}
		}
		return result;
	}

	// 상품이름 검색
	public Product[] serch_s(String s) {

		Product[] result = new Product[idx];
		int index = 0;
		for (int i = 0; i < idx; i++) {
			if (pd[i].getP_Name().contains(s)) {
				result[index++] = pd[i];
			}
		}
		return result;
	}

	// TV or Refrigerator 검색
	public Product[] serch_i(int info) {

		Product[] result = new Product[idx];
		int index = 0;
		for (int i = 0; i < idx; i++) {
			if (pd[i].getInfo() == info) {
				result[index++] = pd[i];
			}
		}
		return result;	
	}

	// 상품 번호로 상품 삭제
	public boolean delete(int num) {
		for(int i = 0; i < idx; i++) {
			if( pd[i].getP_Num() == num) {
				for(int j = i+1; j < idx; j++)
					pd[j-1] = pd[j];
				idx--;
				return true;
			}
		}
		
		return false;
		
	}

	// 전체 재고 상품금액을 구하는 기능
	public int sum_price() {

		int sum = 0;
		for(int i = 0; i < idx; i++) {
				sum += pd[i].getP_price() * pd[i].getP_amount();
		}
		return sum;
	}

}

public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		boolean flag = true;

		ProductMgr pdmgr = ProductMgr.getInstance();
		
		while (flag) {

			System.out.println("============================");
			System.out.println("1. 상품 정보 입력");
			System.out.println("2. 상품 정보 전체검색");
			System.out.println("3. 상품 번호로 검색");
			System.out.println("4. 상품명으로 검색");
			System.out.println("5. TV정보만 검색");
			System.out.println("6. 냉장고만 검색");
			System.out.println("7. 전체 재고 상품 금액을 구하기");
			System.out.println("8. 상품번호로 삭제");
			System.out.println("0. 종료");
			System.out.println("==============원하는 번호를 입력하시오:");
			int n = sc.nextInt();

			switch (n) {
			case 0: {
				System.out.println("종료합니다.");
				flag = false;
				break;
			}

			case 1: { //상품 정보 입력
				System.out.println("1. 티비 or 2.냉장고 인지 선택하시오. (번호입력) ");
				int info = sc.nextInt();
				System.out.println("상품 번호를 입력하세요.");
				int p_num = sc.nextInt();
				System.out.println("상품명을  입력하세요.");
				String p_Name = sc.next();
				System.out.println("상품 가격을 입력하세요");
				int price = sc.nextInt();
				System.out.println("상품 수량을 입력하세요.");
				int amount = sc.nextInt();
				
				if (info == 1) {
					TV tv = new TV(p_num, p_Name, amount, price, info);
					pdmgr.add(tv);
				}
				if (info == 2) {
					Refrigerator re = new Refrigerator(p_num, p_Name, amount, price, info);
					pdmgr.add(re);
				}
				break;
			}

			case 2: {//상품 정보 전체 검색
				Product [] list = pdmgr.serch();
				for(int i = 0; i <pdmgr.getSize(); i++)
					System.out.println(list[i]);
				break;
			}

			case 3: {// 상품 번호검색
				System.out.println("상품 번호를 입력해주세요:");
                int number = sc.nextInt();
                for(Product x : pdmgr.serch_n(number)) {
                    if(x!=null) {
                        System.out.println(x);
                    }
                }
				break;
			}

			case 4: {//상품명으로 검색
				System.out.println("상품 이름을 입력해주세요:");
                String name = sc.next();
                for(Product x : pdmgr.serch_s(name)) {
                    if(x!=null) {
                        System.out.println(x);
                    }
                }
				break;
			}

			case 5: {//TV정보만 검색
                for(Product x : pdmgr.serch_i(1)) {
                    if(x!=null) {
                        System.out.println(x);
                    }
                }
				break;
			}

			case 6: { //냉장고 정보만검색
				for(Product x : pdmgr.serch_i(2)) {
                    if(x!=null) {
                        System.out.println(x);
                    }
                }
				
				break;
			}
			case 7: {//전체재고상품금액 구하기
				System.out.println(pdmgr.sum_price() + "원 입니다.");
				break;
			}
			case 8: { //상품번호로 삭제
				System.out.println("삭제할 상품 번호를 입력하세요:");
				int num = sc.nextInt();
				boolean b = pdmgr.delete(num);
				if(b)
					System.out.println("삭제되었습니다.");
				else
					System.out.println("삭제할 상품이 없습니다.");
				break;
			}

			default:
				break;
			}
		}

	}

}
