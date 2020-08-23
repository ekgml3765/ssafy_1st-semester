package emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpMgrImpl implements EmpMgr {

	private EmpMgrImpl() {
	}

	private static EmpMgrImpl instance;

	public static EmpMgrImpl getInstance() {
		if (instance == null)
			instance = new EmpMgrImpl();
		return instance;
	}
	
	//List<Employee> list = new ArrayList<Employee>();

	// 수정해야해
	public static Connection getConnection() {
		Connection conn = null;

		try {
			// JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false ===> 클라이언트와 서버의 시간을
			// 일치시키기 위해서 8버전 이상부터 쓰는것을 의무화 시킴
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/emp_db?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "ssafy",
					"1234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void close(Connection conn) throws SQLException {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void close(Statement statement) throws SQLException {
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void close(ResultSet rs) throws SQLException {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//직원 추가
	@Override
	public void add(Employee b) throws SQLException {
		Connection conn = getConnection();
		// 사용할 SQL문 준비
		String sql = "INSERT INTO employee VALUES(?, ?, ?, ?)";
		// Statement 구문 객체에 SQL문을 준비
		PreparedStatement ps = conn.prepareStatement(sql);
		// 필요한 파라메터들을 매칭
		ps.setInt(1, b.getEmpNo());
		ps.setString(2, b.getName());
		ps.setString(3, b.getPosition());
		ps.setString(4,  b.getDept());
		// Statement구문 객체 던짐
		ps.executeUpdate();

		close(ps);
		close(conn);

	}

	//전체 리스트 반환
	@Override
	public List<Employee> search() throws SQLException {
		// TODO Auto-generated method stub
		List<Employee> list = new ArrayList<>();
		Connection conn = getConnection();
		// 사용할 SQL문 준비
		String sql = "SELECT * fROM employee";
		// Statement 구문 객체에 SQL문을 준비
		PreparedStatement ps = conn.prepareStatement(sql);
		
		Employee emp = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int empno = rs.getInt("empno");
			String empname = rs.getString("empname");
			String position = rs.getString("position");
			String dept = rs.getString("dept");

			emp = new Employee(empno, empname, position, dept);
			list.add(emp);
		}
		close(rs);
		close(ps);
		close(conn);
		return list;

	}

	//번호로 검색 
	@Override
	public List<Employee> search(int empNo) throws SQLException {
		List<Employee> list = new ArrayList<>();
		Employee emp = null;

		Connection conn = getConnection();
		String sql = "SELECT * FROM employee WHERE empno = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, empNo);
		//쿼리던짐
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int empno = rs.getInt("empno");
			String empname = rs.getString("empname");
			String position = rs.getString("position");
			String dept = rs.getString("dept");
			emp = new Employee(empno, empname, position, dept);
			list.add(emp);
		}
		close(rs);
		close(ps);
		close(conn);

		return list;
	}

	//직원 이름으로 검색
	@Override
	public List<Employee> search(String name) throws SQLException {
		List<Employee> list = new ArrayList<>();
		Employee emp = null;

		Connection conn = getConnection();
		String sql = "SELECT * FROM employee WHERE empname like '%"+name+"%' ";
		PreparedStatement ps = conn.prepareStatement(sql);
		//쿼리던짐
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int empno = rs.getInt("empno");
			String empname = rs.getString("empname");
			String position = rs.getString("position");
			String dept = rs.getString("dept");
			emp = new Employee(empno, empname, position, dept);
			list.add(emp);
		}
		close(rs);
		close(ps);
		close(conn);

		return list;
	}

	
	//직원번호 업데이트, 부서변경
	@Override
	public boolean update(int empNo, String dept) throws SQLException {
		Connection conn = getConnection();
		// 사용할 SQL문 준비
		String sql = "UPDATE employee SET dept = ? WHERE empno = ?";
		// Statement 구문 객체에 SQL문을 준비
		PreparedStatement ps = conn.prepareStatement(sql);
		// 필요한 파라메터들을 매칭
		ps.setString(1, dept);
		ps.setInt(2, empNo);
		// 구문 객체 던짐
		int result = ps.executeUpdate();
		if(result < 1) {
			System.out.println("직원번호가 틀렸습니다. 업데이트할 정보가 없습니다.");
			return false;
		}
			
		close(ps);
		close(conn);
		return true;
		
	}

	//직원번호로 삭제
	@Override
	public boolean delete(int empNo)  throws SQLException {
		Connection conn = getConnection();
		// 사용할 SQL문 준비
		String sql = "DELETE FROM employee WHERE empno = ?";
		// Statement 구문 객체에 SQL문을 준비
		PreparedStatement ps = conn.prepareStatement(sql);
		// 필요한 파라메터들을 매칭
		ps.setInt(1, empNo);
		// 구문 객체 던짐
		int result = ps.executeUpdate();
		if(result < 1) {
			System.out.println("직원번호가 틀렸습니다. 삭제할 정보가 없습니다.");
			return false;
		}
			
		close(ps);
		close(conn);
		return true;
	}

}
