package hw_0811;

public class Product {

	private int p_Num, p_price, capacity, amount;
	private String p_Name, info;


	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
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

	@Override
	public String toString() {
		return "Product [p_Num=" + p_Num + ", p_price=" + p_price + ", capacity=" + capacity + ", amount=" + amount
				+ ", p_Name=" + p_Name + ", info=" + info + "]";
	}



	
	
	
}
