package ssafy.com.model;



public class UserDao {
	private static UserDao dao = new UserDao();
	private UserDao() {
	}
	public static UserDao getDao(){
		return dao;
	}
	
	public boolean login(String id, String pass) {
		//원래는 디비로 처리해야해
		return "ssafy".equals(id) && "1234".equals(pass);
	}
}
