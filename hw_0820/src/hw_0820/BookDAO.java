package hw_0820;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {	
	// Statement, PreparedStatement ===> SQL문을 데이터베이스에 보내기 위한 객체
	// ResultSet ===> SQL질의에 의해 생성된 테이블을 저장하는 객체
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			// JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false ===> 클라이언트와 서버의 시간을 일치시키기 위해서 8버전 이상부터 쓰는것을 의무화 시킴
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ssafydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false","ssafy","1234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
	
	
	public static void close(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void close(Statement statement) {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertBook(Book book) throws SQLException {
		Connection conn = getConnection();
		// 사용할 SQL문 준비
		String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?)";
		// Statement 구문 객체에 SQL문을 준비
		PreparedStatement ps = conn.prepareStatement(sql);
		// 필요한 파라메터들을 매칭
		ps.setString(1, book.getIsbn());
		ps.setString(2, book.getTitle());
		ps.setString(3, book.getAuthor());
		ps.setString(4, book.getPublisher());
		ps.setInt(5, book.getPrice());
		ps.setString(6, book.getDescription());
		// Statement구문 객체 던짐
		ps.executeUpdate();
		
		close(ps);
		close(conn);
		
	}
	
	public static void updateBook(Book book) throws SQLException {
		Connection conn = getConnection();
		String sql = "UPDATE book SET title = ?, author = ?, publisher = ?, price = ?, description = ? WHERE isbn = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, book.getTitle());
		ps.setString(2, book.getAuthor());
		ps.setString(3, book.getPublisher());
		ps.setInt(4, book.getPrice());
		ps.setString(5, book.getDescription());
		ps.setString(6, book.getIsbn());
		ps.executeUpdate();
		
		close(ps);
		close(conn);
	}
	
	public static void deleteBook(String isbn) throws SQLException {
		Connection conn = getConnection();
		String sql = "DELETE FROM book WHERE isbn = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, isbn);
		ps.executeUpdate();
		
		close(ps);
		close(conn);
	}
	
	public static Book findBook(String isbn) throws SQLException {
		Book book = null;
		Connection conn = getConnection();
		String sql = "SELECT * FROM book WHERE isbn = " + isbn;
		Statement state = conn.createStatement();
		
		ResultSet rs = state.executeQuery(sql);
		
		while (rs.next()) {
			String no = rs.getString("isbn");
			String title = rs.getString("title");
			String author = rs.getString("author");
			String publisher = rs.getString("publisher");
			int price = rs.getInt("price");
			String description = rs.getString("description");
			book = new Book(no, title, author, publisher, price, description);
		}

		close(rs);
		close(state);
		close(conn);
	
		return book;
	}
	
	public static List<Book> listBook() throws SQLException {
		List<Book> list = new ArrayList<>();
		Connection conn = getConnection();
		String sql = "SELECT * FROM book";
		Statement state = conn.createStatement();
		// SQL 질의 결과를 ResultSet에 저장
		ResultSet rs = state.executeQuery(sql);
		// rs.next()로 커서를 이동 (1개일 경우 if문 사용가능)
		while (rs.next()) {
			String no = rs.getString("isbn");
			String title = rs.getString("title");
			String author = rs.getString("author");
			String publisher = rs.getString("publisher");
			int price = rs.getInt("price");
			String description = rs.getString("description");
			list.add(new Book(no, title, author, publisher, price, description));
		}

		close(rs);
		close(state);
		close(conn);
		
		return list;
	}
	
	public static int count() throws SQLException {
		Connection conn = getConnection();
		String sql = "SELECT COUNT(*) FROM book";
		Statement state = conn.createStatement();
		
		ResultSet rs = state.executeQuery(sql);
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}

		close(rs);
		close(state);
		close(conn);
		
		return count;
	}
	
	
	

	
}