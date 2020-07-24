package com.ssafy;

public class ProductMgr {

	static private ProductMgr instance;

	public static ProductMgr getInstance() {
		if (instance == null)
			instance = new ProductMgr();
		return instance;
	}

	// 기본생성자 닫기
	private ProductMgr() {

	}

	private Product[] pd = new Product[100];
	private int index = 0;

	// 상품 정보 저장
	public void add(Product p) {
		pd[index] = p;
		index++;

	}

	// 상품 정보 전체검색
	public Product[] search() {
		return pd;
	}

	// 상품 번호로 검색
	public Product[] search(int number) {

		Product[] p = new Product[100];
		int count = 0;
		for (int i = 0; i < index; i++) {
			if (pd[i].getP_number() == number) {
				p[count] = pd[i];
				count++;
			}

		}

		return p;
	}

	// 특정 가격 이하의 상품만 검색하는 기능
	public Product[] searchPrice(int price) {

		Product[] p = new Product[100];
		int count = 0;
		for (int i = 0; i < index; i++) {
			if (pd[i].getPrice() <= price) {
				p[count] = pd[i];
				count++;
			}

		}

		return p;
	}

	// 상품 번호로 상품 정보 삭제
	public void delete(int number) {

		for (int i = 0; i < index; i++) {

			if (pd[i].getP_number() == number) {
				for (int j = i + 1; j < index; j++) {
					pd[j - 1] = pd[j];
				}
				index --;
			}
		}

	}

	/** 저장된 상품정보의 갯수를 리턴한다. */
	public int getSize() {
		return index;
	}

}
