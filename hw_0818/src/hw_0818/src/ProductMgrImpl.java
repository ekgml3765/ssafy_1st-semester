package hw_0818.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;




public class ProductMgrImpl implements IProductMgr{


	private ArrayList<Product> pd;
	
	//생성자를 private로 감춘다
	private ProductMgrImpl(){
		//리스트를 새거를 만드는게 아니라. 파일로부터 읽어서. 리스트를 복구!
		
				try {
					ObjectInputStream oi = new ObjectInputStream(new FileInputStream("Product.dat"));
					pd = (ArrayList<Product>) oi.readObject();
				}
				
				catch(Exception e) {
					pd = new ArrayList<>();
				}
	}
	
	//인스턴스 스태틱 변수를 생성한다.
	static ProductMgrImpl instance;	
	//게터 생성	
	public static ProductMgrImpl getInstance() {
		if(instance == null)
			instance = new ProductMgrImpl();
		return instance;
	}

//	
//	private ArrayList<Product> pd = new ArrayList<>();

	public int getSize() {
		return pd.size();
	}
	//추가
	@Override
	public void add(Product p) throws DuplicateException {
		// TODO Auto-generated method stub
		boolean flag = true;
		if(pd.size() == 0) {
			pd.add(p);
			return;
		}
			
		for(int i = 0; i < pd.size(); i++) {
			if(pd.get(i).getP_Num() == p.getP_Num()) {
				flag = false;
				break;
			}
		}
		if(flag == false) throw new DuplicateException();
		else pd.add(p);
	}

	//전체 검색
	@Override
	public ArrayList<Product> serch() {
		// TODO Auto-generated method stub
		return pd;
	}

	//번호로 검색
	@Override
	public ArrayList<Product> serch_Num(int p_num) throws CodeNotFoundException {
		// TODO Auto-generated method stub
		ArrayList<Product> result = new ArrayList<Product>();
		for (int i = 0; i < pd.size(); i++) {
			if (pd.get(i).getP_Num() == p_num) {
				result.add(pd.get(i));
				return result;
			}
		}
		throw new CodeNotFoundException();
		
	}

	//이름으로 검색
	@Override
	public ArrayList<Product> serch_Name(String s) {
		// TODO Auto-generated method stub
		ArrayList<Product> result = new ArrayList<Product>();
		for (int i = 0; i < pd.size(); i++) {
			if (pd.get(i).getP_Name().contains(s)) {
				result.add(pd.get(i));
			}
		}
		return result;
	}

	//정보로 검색 TV냐 냉장고냐
	@Override
	public ArrayList<Product> serch_Info(String info) {
		// TODO Auto-generated method stub
		ArrayList<Product> result = new ArrayList<Product>();
		for (int i = 0; i < pd.size(); i++) {
			if (pd.get(i).getInfo().contains(info)) {
				result.add(pd.get(i));
			}
		}
		return result;
	}

	// 용량 tv or 냉장고 
	@Override
	public ArrayList<Product> serch_Info2(String info, int num) throws ProductNotFoundException{
		// TODO Auto-generated method stub
		ArrayList<Product> result = new ArrayList<Product>();
		for (int i = 0; i < pd.size(); i++) {
			if (pd.get(i).getInfo().contains(info) && pd.get(i).getCapacity() == num) {
				result.add(pd.get(i));
				return result;
			}
		}
		throw new ProductNotFoundException();
	}

	//상품 번호로 삭제
	@Override
	public boolean delete(int p_num) {
		// TODO Auto-generated method stub
	
		for(int i = 0; i < pd.size(); i++) {
			if(pd.get(i).getP_Num() == p_num) {
				pd.remove(i);
				return true;
			}			
		}
		
		return false;
	}

	//상품번호를 입력받아 원하는 가격으로 변경
	@Override
	public void price_change(int p_num, int price) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < pd.size(); i++) {
			if (pd.get(i).getP_Num() == p_num) {
				pd.get(i).setP_price(price);
			}
		}
	}

	@Override
	public int sum_price() {
		// TODO Auto-generated method stub
		int sum = 0;
		for(int i = 0; i < pd.size(); i++) {
				sum += pd.get(i).getAmount() * pd.get(i).getP_price();
		}
		return sum;
	}
	
	public void save() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {		
			@Override
			public void run() {
				ObjectOutputStream bo = null;
				try {
					bo
					= new ObjectOutputStream(new FileOutputStream("product.dat"));
					bo.writeObject(pd);
					bo.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					if( bo != null ) {
						try {
							bo.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
	
	
}
