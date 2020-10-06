package ssafy.com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ssafy.com.model.UserDao;



@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("logout")) {
			logout(request, response);
		}else if (action.equals("last")) {
			last(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
			request.setCharacterEncoding("utf-8"); // 인코딩 설정
			String action = request.getParameter("action");
			if (action.equals("login")) {
				login(request, response);
			}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파라미터확보.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		System.out.println(id);
		System.out.println(pass);
		// 2, model 연결
		UserDao dao = UserDao.getDao();
		boolean result = dao.login(id, pass);
		
		// 로그인 성공시 결과페이지, 로그인 실패시 원래페이지 그대로
		if (result) {
			// 3. 화면 연결
			// redirect or forward? //로그인되서 url 바뀐 페이지를 보여주기 위헤 redirect
			// 3-1 화면에서 필요한 데이터 설정 .
			System.out.println("로그인 성공");
			HttpSession session = request.getSession(); // 리퀘스트 얻어옴
			session.setAttribute("loginUser", id);
			// session.setAttribute("num2", pass);
			response.sendRedirect(request.getContextPath() + "/index.jsp");

		} else {
			System.out.println("로그인 실패");
			RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
			disp.forward(request, response);
		}

	}
	
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.파라미터 확보
		// 2. model 연결
		// 세션 정리.
		HttpSession session = request.getSession();
		session.invalidate(); // 해당 사용자의 세션 정리하기
		response.sendRedirect(request.getContextPath() + "/index.jsp");

	}
	
	private void last(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 // 쿠키 생성
	      Cookie cookie = new Cookie("pnum", "1009");
	      // 유효기간
	      cookie.setMaxAge(60 * 2);
	      // 경로
	      cookie.setPath("/");
	      // 전달
	      response.addCookie(cookie);

	      Cookie cookie2 = new Cookie("pname", "Smart_TV");
	      cookie2.setPath("/");
	      response.addCookie(cookie2);

	      Cookie cookie3 = new Cookie("price", "50000");
	      cookie3.setPath("/");
	      response.addCookie(cookie3);
	      
	      Cookie cookie4 = new Cookie("discription", "스마트하다");
	      cookie3.setPath("/");
	      response.addCookie(cookie4);

	      RequestDispatcher disp = request.getRequestDispatcher("/lastlist.jsp");
	      disp.forward(request, response);
	}

}
