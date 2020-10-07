package ssafy.com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ssafy.com.util.DBUtil;

public class ProductDao {

	// singleton패턴
	private static ProductDao dao = new ProductDao();

	private ProductDao() {
	}

	static public ProductDao getDao() {
		return dao;
	}

	// DAO는 개별 SQL 처리가 목적.
	// DAO는 커넥션을 만드는게 아니고, 공급 받아서 사용, service에서 전달받은 connection을 얻어서 씀!
	// 예외 처리: 여기서 발생하는 예외처리 어차피 서비스에서 부르니까 서비스한테 처리하라고 하자!

	//상품명 검색
	public List<ProductDto> select_pname(Connection con, String pname) throws SQLException {

		List<ProductDto> list = new ArrayList<>();
		ProductDto product = null;

		String sql = "select * from product where p_name like '%" + pname + "%'";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			// 쿼리 실행 - 쿼리의 타입은?
			rset = pstmt.executeQuery();

			// id는 pk니까 갯수가 1개 아님 0개니까

			while (rset.next()) {
				int pnum = rset.getInt("pnum");
				String p_name = rset.getString("pname");
				int price = rset.getInt("price");
				String dc = rset.getString("dc");

				product = new ProductDto(pnum, p_name, price, dc);
				list.add(product);
			}

		} finally {
			// 객체닫아줌.
			DBUtil.close(pstmt, rset);
		}

		return list;
	}

	//가격 이하 검색
	public List<ProductDto> select_price(Connection con, int price) throws SQLException {

		List<ProductDto> list = new ArrayList<>();
		ProductDto product = null;

		String sql = "select * from product where  price <= ?";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, price);
			// 쿼리 실행 - 쿼리의 타입은?
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int pnum = rset.getInt("pnum");
				String p_name = rset.getString("pname");
				int p_price = rset.getInt("price");
				String dc = rset.getString("dc");

				product = new ProductDto(pnum,p_name, p_price,dc);
				list.add(product);
			}

		} finally {
			// 객체닫아줌.
			DBUtil.close(pstmt, rset);
		}

		return list;
	}

	//상품 하나 넣음.
		public ProductDto select(Connection con, int pnum) throws SQLException {
	
			String sql = "select * from product where pnum=?";
			
			ProductDto product = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			try {
				// 질의 준비
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pnum);

				// 쿼리 실행 - 쿼리의 타입은?
				rset = pstmt.executeQuery();

				// id는 pk니까 갯수가 1개 아님 0개니까
				if (rset.next()) {
					String pname = rset.getString(2);
					int price = rset.getInt(3);
					String dc = rset.getString(4);
					product = new ProductDto(pnum, pname, price, dc);
				}
			} finally {
				//객체닫아줌.
			DBUtil.close(pstmt,rset);
			}

			return product;
		}

	// 회원 가입을 위한 insert
	public int insert(Connection con, ProductDto product) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "insert into product (pnum, pname, price, dc) values(?, ?, ?, ?)";
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product.getPnum());
			pstmt.setString(2, product.getPname());
			pstmt.setInt(3, product.getPrice());
			pstmt.setString(4, product.getDc());

			result = pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, null);
		}
		return result; // insert 잘 되면 1 리턴
	}

	public int delete(Connection con, ProductDto product) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "delete * from product where pnum=?";
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product.getPnum());
			result = pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt, null);
		}
		return result; // delete 잘 되면 1 리턴
	}

}
