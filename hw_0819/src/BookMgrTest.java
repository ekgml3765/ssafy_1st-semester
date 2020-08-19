import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class BookMgrTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//IBookMgr mgr = BookMgrImpl.getInstance();

		Socket socket;
		try {
			socket = new Socket("localhost",5000);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					
			while (true) {
				System.out.println("\n----------");
				System.out.println("1. 추가 2. 다 보기 3. 팔기 4. 사기 5. 총량 6. 저장하기 0. 종료");
				int input = sc.nextInt();
				if (input == 1) {

					System.out.println("도서 종류 입력 (1:책, 2:잡지)");
					int sel = sc.nextInt();
					if (sel != 1 && sel != 2)
						continue;
					System.out.println("도서 번호 입력");
					String isbn = sc.next();
					System.out.println("도서 제목 입력");
					String title = sc.next();
					System.out.println("도서 가격 입력");
					int price = sc.nextInt();
					System.out.println("도서 개수 입력");
					int quantity = sc.nextInt();
					if (sel == 1)
						oos.writeObject(new Msg(1,new Book(isbn, title, price, quantity)));
					else {
						System.out.println("잡지 월 입력");
						int month = sc.nextInt();
						oos.writeObject(new Msg(1,new Magazine(isbn, title, price, quantity,month)));
					}
				} else if (input == 2) {
					oos.writeObject(new Msg(2,null));
					try {
						List<Book> b = (List<Book>)ois.readObject();
						for(int i=0;i<b.size();i++)
							System.out.println(b.get(i));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else if (input == 3) {
					System.out.println("판매하려는 도서 번호 입력");
					String isbn = sc.next();
					System.out.println("판매하려는 개수 입력");
					int n = sc.nextInt();
					try {
						oos.writeObject(new Msg(3,new Book(isbn,null,0,n)));
						//mgr.sell(isbn,n);

					} catch (QuantityException e) {

						System.out.println(e.getMessage());

					} catch (ISBNNotFoundException e) {

						System.out.println(e.getMessage());

					}

				} else if (input == 4) {

					/*System.out.println("구매하려는 도서 번호 입력");

					String isbn = sc.next();

					System.out.println("구매하려는 개수 입력");

					try {

						//mgr.buy(isbn, sc.nextInt());

					} catch (ISBNNotFoundException e) {

						System.out.println(e.getMessage());

					}*/

				} else if (input == 5) {

					//System.out.println("총 재고는 " + mgr.getTotalAmount() + "입니다.");

				} else if (input == 0) {
					System.out.println("종료");
					oos.writeObject(new Msg(0,null));
					break;
				}
				else if(input ==6) {
					//저장하기
					//mgr.save();
				}

			}//end while
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

