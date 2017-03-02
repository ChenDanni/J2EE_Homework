package servlets;

import java.io.IOException;

import service.UserManagerService;
import utility.*;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.business.StatesBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.user")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB UserManagerService userManagerService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
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
				session.invalidate();
				session = null;
			}
			response.sendRedirect(request.getContextPath() + "/jsp/Login.jsp");
		}else if (request.getParameter("visitor") != null){
//			System.out.println("visitor");
			CountHelper.addGuest(context);
			response.sendRedirect(request.getContextPath() + "/jsp/visitor.jsp");
		}else if (request.getParameter("visitor-out") != null){
			CountHelper.minusGuest(context);
			response.sendRedirect(request.getContextPath() + "/jsp/Login.jsp");
		}else {
//			System.out.println("in");
			response.sendRedirect(request.getContextPath() + "/jsp/Login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

				response.sendRedirect(request.getContextPath() + "/ExamsInfo");
				
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
		}
	}
}