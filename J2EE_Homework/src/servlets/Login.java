package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import utility.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.business.StatesBean;
import model.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.user")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void displayWrongPasswordPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.println("<html>");
    	out.println("<head>");
    	out.println("</head>");
    	out.println("<body>");

    	out.println("<p>密码错误</p>");
    	
    	out.println("<br/>");
    	
    	//重新登录
    	out.println("<form method='GET' action='" +
    	response.encodeURL(request.getContextPath()+"/login.user")
    	+ "'>");
    	out.println("<input type='submit' value='重新登录'>");
    	out.println("</form>");
    	
    	out.println("</body></html>");
    	
    }
    
    public void displayUserNotExistPage(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.println("<html>");
    	out.println("<head>");
    	out.println("</head>");
    	out.println("<body>");

    	out.println("<p>用户名不存在</p>");
    	
    	out.println("<br/>");
    	
    	//重新登录
    	out.println("<form method='GET' action='" +
    	response.encodeURL(request.getContextPath()+"/login.user")
    	+ "'>");
    	out.println("<input type='submit' value='重新登录'>");
    	out.println("</form>");
    	
    	out.println("</body></html>");
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("logout") != null){
			ServletContext context = getServletContext();
			int total = Integer.parseInt((String)context.getAttribute("total"));
			int login = Integer.parseInt((String)context.getAttribute("login"));
			total--;
			login--;
			context.setAttribute("total", Integer.toString(total));
			context.setAttribute("login", Integer.toString(login));
			if (session != null){
				System.out.println("session disable");
				session.invalidate();
				session = null;
			}
		}
		ServletContext context = getServletContext();
		if (request.getParameter("visitor") != null){
			System.out.println("visitor");
			int total = Integer.parseInt((String)context.getAttribute("total"));
			total++;
			context.setAttribute("total", Integer.toString(total));
			response.sendRedirect(request.getContextPath() + "/jsp/visitor.jsp");
		}else if (request.getParameter("visitor-out") != null){
			int total = Integer.parseInt((String)context.getAttribute("total"));
			total--;
			context.setAttribute("total", Integer.toString(total));
			response.sendRedirect(request.getContextPath() + "/jsp/Login.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/jsp/Login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do post");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username == null) username = "unknow";
		if (password == null) password = "unknow";
		
		//TODO 判断是否合理
//		response.setContentType("text/html");
//		PrintWriter pWriter = response.getWriter();
		
		//TODO 验证用户名密码
		LoginState loginState = LoginHander.getLoginRes(username, password);
		HttpSession session = request.getSession(false);
		StatesBean pageState = new StatesBean();
		ServletContext context = getServletContext();
		if (loginState == LoginState.SUCCESS){
			if ((session == null)||(request.getAttribute("login") == null)) { 
				
				session = request.getSession(true);
				session.setAttribute("login", username);
				request.setAttribute("login", username);
				
				int total = Integer.parseInt((String)context.getAttribute("total"));
				int login = Integer.parseInt((String)context.getAttribute("login"));
				total++;
				login++;
				context.setAttribute("total", Integer.toString(total));
				context.setAttribute("login", Integer.toString(login));
				
//				context.getRequestDispatcher("/ExamsInfo").forward(request, response);
				response.sendRedirect("/ExamsInfo");
				
			}
		}else if (loginState == LoginState.USERNOTEXIST) {
			pageState.setState(PageState.USERNOTFOUND);
			session.setAttribute("state", pageState);
			context.getRequestDispatcher("/jsp/LoginFail.jsp").forward(request, response);
		}else if (loginState == LoginState.WRONGPWD) {
			pageState.setState(PageState.WRONGPWD);
			session.setAttribute("state", pageState);
			context.getRequestDispatcher("/jsp/LoginFail.jsp").forward(request, response);
		}else {
//			System.out.println(loginState);
//			pWriter.println("<p>unknow error</p>");
		}
	}
}
