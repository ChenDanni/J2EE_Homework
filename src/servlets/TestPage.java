package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import model.Test;
import utility.PageState;
import utility.TestState;
import utility.*;

/**
 * Servlet implementation class TestPage
 */
@WebServlet("/TestPage")
public class TestPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String username = (String)(request.getSession().getAttribute("login"));
    	String student_id = Student.getIdByName(username);
    	ArrayList<Test> test_info = Test.getTestInfo(student_id);
    	PageState pageState = PageState.COMMON;
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	ServletContext context = getServletContext();
    	int total = Integer.parseInt((String)context.getAttribute("total"));
    	int login = Integer.parseInt((String)context.getAttribute("login"));
    	
    	for (int i = 0;i < test_info.size();i++){
    		Test t = test_info.get(i);
    		if (t.state == TestState.WAIT){
    			pageState = PageState.WARNING;
    			break;
    		}
    	}
    	
    	
    	out.println("<html>");
    	out.println("<head>");
    	out.println("<title>测试查询</title>");
    	out.println("</head>");
    	out.println("<body>");

    	out.println("<p>选课测试情况</p>");
//    	out.println("<br/>");
    	if (pageState == PageState.WARNING){
    		out.println("<h4>!!警告－尚有未参与测试测试!!</h4>");
    	}
//    	out.println("<br/>");
    	out.println("<table>");
    	out.println("<tr>");
    	out.println("<td>课程</td>");
    	out.println("<td>测试</td>");
    	out.println("<td>状态</td>");
    	out.println("<td>分数</td>");
    	out.println("</tr>");
    	for (int i = 0;i < test_info.size();i++){
    		Test t = test_info.get(i);
    		String state = "";
    		if (t.state == TestState.WAIT){
    			state = "未参与";
    		}else if (t.state == TestState.FINISH){
    			state = "已结束";
    		}
    		out.println("<tr>");
        	out.println("<td>"+ t.lesson +"</td>");
        	out.println("<td>" + t.name + "</td>");
        	out.println("<td>" + state + "</td>");
        	out.println("<td>" + t.score + "</td>");
        	out.println("</tr>");
    	}
    	out.println("</table>");
    	out.println("<br/>");
    	out.println("<br/>");
    	out.println("<p>在线人数： " + total + "    登录用户： " + login + "    游客数： " + (total - login) + "</p>");
    	
//    	//点击here,刷新该页面,会话有效
//    	out.println("Click <a href='" + response.encodeURL(request.getRequestURI()) + "'>here</a>"
//    			+ "to reload this page.<br>");
    	
    	
    	//注销Logout
    	out.println("<form method='GET' action='" +
    	response.encodeURL(request.getContextPath()+"/login.user")
    	+ "'>");
    	out.println("<input type='submit' value='注销' name='logout'>");
    	out.println("</form>");
    	
    	out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
