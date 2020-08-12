package hw_0812;

import java.util.ArrayList;

public interface IProductMgr {

	// 상품정보 (TV 와 Refrigerator)를 저장
	public void add(Product p) throws DuplicateException;

	// 상품정보 전체를 검색하는 기능
	public ArrayList<Product> serch();

	// 상품번호로 상품을 검색하는 기능
	public ArrayList<Product> serch_Num(int p_num) throws CodeNotFoundException;

	// 상품명으로 상품을 검색하는 기능 상품명 부분 검색 가능
	public ArrayList<Product> serch_Name(String s);

	// TV or Refrigerator 검색
	public ArrayList<Product> serch_Info(String info);

	// 400L 이상의 Refrigerator 검색
	// 50inch 이상의 TV 검색
	public ArrayList<Product> serch_Info2(String info, int num)throws ProductNotFoundException;
	
	// 상품 번호로 상품 삭제
	public boolean delete(int p_num);

	//상품번호와 가격을 입력 받아 상품 가격을 변경할 수 있는 기능
	public void price_change(int p_num, int price);

	// 전체 재고 상품금액을 구하는 기능
	public int sum_price();
}
