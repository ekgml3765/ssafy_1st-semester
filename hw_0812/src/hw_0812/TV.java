package hw_0812;

public class TV extends Product {

	// 생성자
	TV(int p_Num, String p_Name, int p_price, String info, int amount, int capacity) {
		super.setP_Num(p_Num);
		super.setP_Name(p_Name);
		super.setP_price(p_price);
		super.setInfo(info);
		super.setAmount(amount);
		super.setCapacity(capacity);
	}
}
