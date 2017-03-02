package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import action.business.ExamsInfoBean;
import action.business.StatesBean;
import action.business.StatisticsBean;
import factory.ServiceFactory;
import utility.PageState;

/**
 * Servlet implementation class ExamsInfoServlet
 */
@WebServlet("/ExamsInfo")
public class ExamsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamsInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in get");
		processrequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void processrequest(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		ServletContext context = getServletContext();
		ExamsInfoBean examsInfo = new ExamsInfoBean();
		StatisticsBean statistics = new StatisticsBean();
		String username = (String)session.getAttribute("login");
		StatesBean states = new StatesBean();
		System.out.println("username : " + username);

		if (username == null){
			try{
				response.sendRedirect("/jsp/Login.jsp");
			}catch(Exception e){
				e.printStackTrace();
			}
			return;
		}
		
//		username = "cdn";
		
		examsInfo.setExamsInfoList(ServiceFactory.getExamInquireService().getExamsInfo(username));
		states.setState(ServiceFactory.getExamInquireService().getPageState(username));
		int total = Integer.parseInt((String)context.getAttribute("total"));
    	int login = Integer.parseInt((String)context.getAttribute("login"));
    	statistics.setTotal(total);
    	statistics.setLogin(login);
    	statistics.setVisitor(total - login);
		
		try{
			if (examsInfo.getExamsInfoList().size() < 1){
				
			}else {
                System.out.println("run to this");
                session.setAttribute("examsInfo", examsInfo);
				session.setAttribute("statistics", statistics);
				session.setAttribute("states", states);
				context.getRequestDispatcher("/jsp/ExamsInfo.jsp").forward(request, response);
//				response.sendRedirect("/jsp/ExamsInfo.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
