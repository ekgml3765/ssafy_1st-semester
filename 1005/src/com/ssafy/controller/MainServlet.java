package com.ssafy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.model.UserDao;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main.do")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	
	 if (action.equals("logout")) 
			logout(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// 2, model 연결
		UserDao dao = UserDao.getDao();
		boolean result = dao.login(id, pass);

		// 로그인 성공시 결과페이지, 로그인 실패시 원래페이지 그대로
		if (result) {
			// 3. 화면 연결
			// redirect or forward? //로그인되서 url 바뀐 페이지를 보여주기 위헤 redirect
			// 3-1 화면에서 필요한 데이터 설정 .
			HttpSession session = request.getSession(); // 리퀘스트 얻어옴
			session.setAttribute("loginUser", id);
			// session.setAttribute("num2", pass);
			response.sendRedirect(request.getContextPath() + "/loginresult.jsp");

		} else {
			RequestDispatcher disp = request.getRequestDispatcher("/main.jsp");
			disp.forward(request, response);
		}

	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.파라미터 확보
		// 2. model 연결
		// 세션 정리.
		HttpSession session = request.getSession();
		session.invalidate(); // 해당 사용자의 세션 정리하기
		response.sendRedirect(request.getContextPath() + "/main.jsp");

	}

}
