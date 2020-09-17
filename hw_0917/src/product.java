

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class product
 */
@WebServlet("/product.do")
public class product extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public product() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); // 한글 안깨지게
		response.setContentType("text/html"); 
		String product_name = request.getParameter("product_name");
		int product_price = Integer.parseInt(request.getParameter("product_price"));
		String product_dic = request.getParameter("product_dic");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("상품명:" + product_name);
		out.println("상품가격:" + product_price);
		out.println("상품설명:" + product_dic);
		out.println("</body>");
		out.println("</html>");
	}



}
