package ssafy.com.model;

import java.sql.Connection;
import java.sql.SQLException;

import ssafy.com.util.DBUtil;

public class UserService {

	private UserDao dao = UserDao.getDao();

	private static UserService service; // dao자주쓰니까 변수 생성.

	private UserService() {
	}

	public static UserService getUserService() {
		if (service == null)
			service = new UserService();
		return service;
	}

	// 서비스 : 트렌젝션 처리의 책임, 커넥션 객체 생성후 TX관리
	public boolean login(String id, String pass) throws SQLException {
		// 커넥션 auto closeable로 닫음.
		try (Connection con = DBUtil.getConnection()) {
			MemberDto member = dao.select(con, id);
			System.out.println("조회결과:" + member);
			if (member != null && member.getPassword().equals(pass)) {
				return true;
			} else {
				return false;
			}
		}
	}

	// 트렌젝션 처리해보자! 바로 커밋하지마.
	// 우리의 가입 업무: 1. 회원가입을 한다. dB에 저장. 2. 가입된 회원을 조회해서 console에 출력한다. -> 가입된 회원 뿌려주기
	public int join(MemberDto member) throws SQLException {

		int result = -1;

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			// 바로 커밋하지마
			// autocommit 중지 -> 이제 내가 commit, rollback은 내가 처리.
			con.setAutoCommit(false);

			// dao 호출
			result = dao.insert(con, member);
			// 사용자로 로그인 해보자.
			MemberDto selected = dao.select(con, member.getId());
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
