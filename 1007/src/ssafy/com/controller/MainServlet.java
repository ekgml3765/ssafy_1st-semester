package ssafy.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ssafy.com.model.MemberDto;
import ssafy.com.model.ProductDto;
import ssafy.com.model.ProductService;
import ssafy.com.model.UserService;



/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();

	}
	
	private static List<ProductDto> A_list = new ArrayList<ProductDto>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);

		try {
			if (action.equals("login")) {
				login(request, response);
			} else if (action.equals("logout")) {
				logout(request, response);
			} else if (action.equals("joinform")) {
				joinform(request, response);
			} else if (action.equals("join")) {
				join(request, response);
			}else if (action.equals("addform")) {
				addform(request, response);
			}else if (action.equals("add")) {
				add(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//상품 등록페이지로 redirect함
	protected void addform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.sendRedirect(request.getContextPath() + "/add.jsp");
	}
	
	//상품 추가.
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		 HttpSession session = request.getSession();
		 if(session.getAttribute("loginId")!=null) {
			 int pnum = Integer.parseInt(request.getParameter("pnum"));
			 String pname = request.getParameter("pname");
			 int price = Integer.parseInt(request.getParameter("price"));
			 String dc = request.getParameter("dc");
			 
			 ProductDto product = new ProductDto(pnum, pname, price, dc);
			 int result = ProductService.getProductService().addProduct(product);
			 
			 if(result==1) {
				 A_list.add(product);
				 System.out.println(A_list);
				 session.setAttribute("list", A_list);
		        	RequestDispatcher disp = request.getRequestDispatcher("/list.jsp");
		            disp.forward(request, response);
		        }else {
		        	request.setAttribute("msg", "상품 등록 실패.");
		        	RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
		            disp.forward(request, response);
		        }
		 }else {
			 request.setAttribute("msg", "로그인해야 가능합니다.");
	        	RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
	            disp.forward(request, response);
		 }
		
	}
	
	
	protected void joinform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher("/joinform.jsp");
        disp.forward(request, response);        
        //회원가입 버튼 누르면 join으로 포워드시킴.
	}
	
	protected void join(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		//1.파라미터 분석 > member
		String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
    
        MemberDto member = new MemberDto(id, name, password);
        
		//2, member를 서비스에 전달
        int result = UserService.getUserService().join(member);
        
		//3. login유도 
        if(result==1) {
        	request.setAttribute("msg", "가입 성공! 로그인하러 가세요");
        	RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
            disp.forward(request, response);
        }else {
        	RequestDispatcher disp = request.getRequestDispatcher("/join.jsp");
            disp.forward(request, response);
        }
        
	}
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		 // UserService를 연동해서 로그인 가능한지 확인
	    String id = request.getParameter("id");
	    String pass = request.getParameter("pass");
	    
	    boolean result = UserService.getUserService().login(id, pass); 
	    // 성공 --> main.jsp, 세션 등록
	    if (result) {
	        HttpSession session = request.getSession();
	        session.setAttribute("loginId", id);
	        response.sendRedirect(request.getContextPath() + "/index.jsp");
	        System.out.println("성공");
	    }
	    // 실패 --> index.jsp
	    else {
	    	System.out.println("실패");       
	    	request.setAttribute("msg", "아이디/비밀번호를 확인하세요");
	    	RequestDispatcher disp = request.getRequestDispatcher("/index.jsp");
	        disp.forward(request, response);
	    }
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//2. session 종료
        HttpSession session = request.getSession();
        session.invalidate(); //해당 사용자 세선 정리
       
		//3. index.jsp로 이동
        response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	protected void template(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
