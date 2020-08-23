package hw_0820;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookTest {
	
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		String isbn;
		Book book;
		BookDAO dao = new BookDAO();
		
		while (true) {
			System.out.println("================= 도서 관리 시스템 =================");
			System.out.println("1. 도서 저장");
			System.out.println("2. 도서 수정");
			System.out.println("3. 도서 삭제");
			System.out.println("4. 도서 찾기");
			System.out.println("5. 도서 전체 목록");
			System.out.println("6. 도서 수량");
			System.out.println("7. 시스템 종료");
			System.out.print("번호 입력 : ");
			int no = scan.nextInt();
			
			switch (no) {
			case 1:
				book = new Book();
				System.out.print("책 번호 : ");
				book.setIsbn(scan.next());
				System.out.print("책 제목 : ");
				book.setTitle(scan.next());
				System.out.print("작가 : ");
				book.setAuthor(scan.next());
				System.out.print("출판사 : ");
				book.setPublisher(scan.next());
				System.out.print("가격 : ");
				book.setPrice(scan.nextInt());
				System.out.print("도서 설명 : ");
				book.setDescription(scan.next());
				dao.insertBook(book);
				System.out.println("도서 정보가 입력되었습니다.");
				break;
			case 2:
				book = new Book();
				System.out.print("수정할 책 번호 : ");
				book.setIsbn(scan.next());
				System.out.print("수정할 책 제목 : ");
				book.setTitle(scan.next());
				System.out.print("수정할 작가 : ");
				book.setAuthor(scan.next());
				System.out.print("수정할 출판사 : ");
				book.setPublisher(scan.next());
				System.out.print("수정할 가격 : ");
				book.setPrice(scan.nextInt());
				System.out.print("수정할 도서 설명 : ");
				book.setDescription(scan.next());
				dao.updateBook(book);
				System.out.println("도서가 수정되었습니다.");
				break;
			case 3:
				System.out.print("삭제할 도서번호를 입력하세요 : ");
				isbn = scan.next();
				dao.deleteBook(isbn);
				break;
			case 4:
				System.out.print("찾을 도서 번호를 입력하세요 : ");
				isbn = scan.next();
				book = dao.findBook(isbn);
				System.out.println(book.toString());
				break;
			case 5:
				System.out.println("도서 목록입니다.");
				List<Book> list = dao.listBook();
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).toString());
				}
				break;
			case 6:
				System.out.println("도서 개수입니다.");
				System.out.println(dao.count() + "개");
				break;
			case 7:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				break;

			
			}
		}
		
	}
	
}
