package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import service.UserManagerService;
import service.impl.UserManagerServiceImpl;
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
	UserManagerService userManagerService = new UserManagerServiceImpl();
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ServletContext context = getServletContext();
		if (request.getParameter("logout") != null){

			CountHelper.minusLogin(context);
			if (session != null){
				System.out.println("session disable");
				session.invalidate();
				session = null;
			}
		}
		if (request.getParameter("visitor") != null){
			System.out.println("visitor");
			CountHelper.addGuest(context);
			response.sendRedirect(request.getContextPath() + "/jsp/visitor.jsp");
		}else if (request.getParameter("visitor-out") != null){
			CountHelper.minusGuest(context);
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
		
		//TODO 验证用户名密码
		LoginState loginState = userManagerService.validateUser(username,password);
		HttpSession session = request.getSession(false);
		StatesBean pageState = new StatesBean();
		ServletContext context = getServletContext();
		if (loginState == LoginState.SUCCESS){
			if ((session == null)||(request.getAttribute("login") == null)) { 
				
				session = request.getSession(true);
				session.setAttribute("login", username);
				request.setAttribute("login", username);

				CountHelper.addLogin(context);
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
