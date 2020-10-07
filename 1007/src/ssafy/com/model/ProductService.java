package ssafy.com.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ssafy.com.util.DBUtil;

public class ProductService {
	private ProductDao dao = ProductDao.getDao();

	
	private static ProductService service; // dao자주쓰니까 변수 생성.

	private ProductService() {
	}

	public static ProductService getProductService() {
		if (service == null)
			service = new ProductService();
		return service;
	}


	//상품 명으로 검색
	public List<ProductDto> search_name(String pname) throws SQLException {
		List<ProductDto> list = null; 
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);

			// dao 호출
			list = dao.select_pname(con, pname);
			// 사용자로 로그인 해보자.
			List<ProductDto> selected = dao.select_pname(con, pname);
			System.out.println(selected);

			// 여기까지 실행되었따는 것은 ? 성공! 별 문제 없었다. 그럴떈 commit 한다.
			con.commit();
		} catch (Exception e) {
			// try블럭에서 뭔가 일이 있어. 사고발생 ! 되돌리기
			
			e.printStackTrace();
			con.rollback();
		} finally {
			// 모드 되돌리기! - select할때는 autocommit이 훨씬 속도가 빨라서.

			if (con != null) {
				con.setAutoCommit(true); // 이것도 null 뜰 수도 있어.
			}
			DBUtil.close(con);// 커넥션 가져오다가 실패할 수도 있음. 이게 널이면.
		}	
		return list;
	}
	
	public List<ProductDto> search_price(int price) throws SQLException {
		List<ProductDto> list = null; 
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);

			// dao 호출
			list = dao.select_price(con, price);
			// 사용자로 로그인 해보자.
			List<ProductDto> selected = dao.select_price(con, price);
			System.out.println(selected);

			// 여기까지 실행되었따는 것은 ? 성공! 별 문제 없었다. 그럴떈 commit 한다.
			con.commit();
		} catch (Exception e) {
			// try블럭에서 뭔가 일이 있어. 사고발생 ! 되돌리기
			
			e.printStackTrace();
			con.rollback();
		} finally {
			// 모드 되돌리기! - select할때는 autocommit이 훨씬 속도가 빨라서.

			if (con != null) {
				con.setAutoCommit(true); // 이것도 null 뜰 수도 있어.
			}
			DBUtil.close(con);// 커넥션 가져오다가 실패할 수도 있음. 이게 널이면.
		}	
		return list;
	}
	
	//상품 등록.
	public int addProduct(ProductDto product) throws SQLException {

		int result = -1;

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);

			// dao 호출
			result = dao.insert(con, product);
			// 사용자로 로그인 해보자.
			ProductDto selected = dao.select(con, product.getPnum());
			System.out.println(selected);

			// 여기까지 실행되었따는 것은 ? 성공! 별 문제 없었다. 그럴떈 commit 한다.
			con.commit();
		} catch (Exception e) {
			// try블럭에서 뭔가 일이 있어. 사고발생 ! 되돌리기
			result = -1;
			e.printStackTrace();
			con.rollback();
		} finally {
			// 모드 되돌리기! - select할때는 autocommit이 훨씬 속도가 빨라서.

			if (con != null) {
				con.setAutoCommit(true); // 이것도 null 뜰 수도 있어.
			}
			DBUtil.close(con);// 커넥션 가져오다가 실패할 수도 있음. 이게 널이면.
		}

		return result;
	}
}
