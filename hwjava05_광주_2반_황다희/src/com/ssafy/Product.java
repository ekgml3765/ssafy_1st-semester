package com.ssafy;

public class Product {

	private int p_number;
	private String p_name;
	private int price;
	private int quantity;
	
	
	public int getP_number() {
		return p_number;
	}
	public void setP_number(int p_number) {
		this.p_number = p_number;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "[ 상품 번호:" + p_number + ", 상품이름:" + p_name + ", 상품가격: " + price + ", 상품정보:"
				+ quantity + " ]";
	}
	
	
	
	
	
}
