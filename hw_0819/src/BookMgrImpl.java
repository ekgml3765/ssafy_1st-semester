import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class BookMgrImpl implements IBookMgr {
	private List<Book> list;

	private BookMgrImpl() {
		//list = new ArrayList<>();
		/*try {
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream("book.dat"));
			list = (List<Book>) oi.readObject();
		}catch(Exception e) {
			list = new ArrayList<>();
		}*/
		list = new ArrayList<>();
	}

	private static BookMgrImpl instance;

	public static synchronized BookMgrImpl getInstance() {
		if (instance == null)
			instance = new BookMgrImpl();
		return instance;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		// book.dat에 멤버변수 리스트에 있는 책 정보를 저장

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ObjectOutputStream bo=null;
				try {
					bo = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("book.dat")));
					bo.writeObject(list);
					bo.flush();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(bo!=null) {
						try {
							bo.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
		
		
	}

	@Override
	public void add(Book b) {
		// TODO Auto-generated method stub
		list.add(b);
	}

	@Override
	public List<Book> list() {
		// TODO Auto-generated method stub
		return new ArrayList<>(list);
	}

	@Override
	public void sell(String isbn, int quantity) throws QuantityException, ISBNNotFoundException {
		// TODO Auto-generated method stub
		// list를 순회하면서 isbn이 일치하는 책을 찾는다.
		Book target = null;
		for (Book b : list) {
			if (b.getIsbn().equals(isbn)) {
				target = b;
			}
		}
		// target가 null이라면 못찾은 것.

		// 못찾는다면 ISBNNotFoundException 발생.
		if (target == null)
			throw new ISBNNotFoundException(isbn);
		// 해당 책에 quantity가 부족하다면 QuantityException 발생.
		if (target.getQuantity() - quantity < 0)
			throw new QuantityException(quantity - target.getQuantity());
		// 문제 없다면 해당 책의 quantity를 차감
		target.setQuantity(target.getQuantity() - quantity);
	}

	@Override
	public void buy(String isbn, int quantity) throws ISBNNotFoundException {
		// TODO Auto-generated method stub
		// list를 순회하면서 isbn이 일치하는 책을 찾는다.
		Book target = null;
		for (Book b : list) {
			if (b.getIsbn().equals(isbn)) {
				target = b;
			}
		}
		// target가 null이라면 못찾은 것.

		// 못찾는다면 ISBNNotFoundException 발생.
		if (target == null)
			throw new ISBNNotFoundException(isbn);
		target.setQuantity(target.getQuantity() + quantity);
	}

	@Override
	public int getTotalAmount() {
		int amount = 0;
		for (Book b : list) {
			amount += (b.getPrice() * b.getQuantity());
		}
		return amount;
	}
}
