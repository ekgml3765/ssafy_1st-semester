package ssafy.com.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ssafy.com.util.DBUtil;



public class UserDao {

	// singleton패턴
	private static UserDao dao = new UserDao();

	private UserDao() {
	}

	static public UserDao getDao() {
		return dao;
	}

	// DAO는 개별 SQL 처리가 목적.
	// DAO는 커넥션을 만드는게 아니고, 공급 받아서 사용, service에서 전달받은 connection을 얻어서 씀!
	// 예외 처리: 여기서 발생하는 예외처리 어차피 서비스에서 부르니까 서비스한테 처리하라고 하자!


	public MemberDto select(Connection con, String userid) throws SQLException {
		// 할일 작성 - 쿼리 만들기
 
		String sql = "select * from member where id=?";
		
		MemberDto member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);

			// 쿼리 실행 - 쿼리의 타입은?
			rset = pstmt.executeQuery();

			// id는 pk니까 갯수가 1개 아님 0개니까
			if (rset.next()) {
				String userpwd = rset.getString(2);
				String username = rset.getString(3);
				member = new MemberDto(userid, username, userpwd);
			}
		} finally {
			//객체닫아줌.
		DBUtil.close(pstmt,rset);
		}

		return member;
	}

	// 회원 가입을 위한 insert 
	public int insert(Connection con, MemberDto member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
	
		String sql = "insert into member(id, password, name) values(?, ?, ?)";
		try {
			// 질의 준비
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
		
			result = pstmt.executeUpdate();
		} finally {
			DBUtil.close(pstmt,null);
		}
		return result; //insert 잘 되면 1 리턴
	}
}
