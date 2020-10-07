package ssafy.com.model;

public class ProductDto {

	private int pnum;
	private String pname;
	private int price;
	private String dc;
	
	public ProductDto() {}
	public ProductDto(int pnum, String pname, int price, String dc) {
		super();
		this.pnum = pnum;
		this.pname = pname;
		this.price = price;
		this.dc = dc;
	}
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	@Override
	public String toString() {
		return "ProductDto [pnum=" + pnum + ", pname=" + pname + ", price=" + price + ", dc=" + dc + "]";
	}
	
	
}
