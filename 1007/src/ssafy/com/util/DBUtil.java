package ssafy.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			Context root = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) root.lookup("jdbc/hw"); 
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	//자원 반납.
	public static void close(PreparedStatement pstmt, ResultSet rset) {

		try {
			if (rset != null) {
				rset.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException ignore) {

		}

	}

	//커넥션 닫음 
	public static void close(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException ignore) {

		}

	}

}
