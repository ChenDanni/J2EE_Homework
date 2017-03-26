package action;

import action.business.StatesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import service.UserManagerService;
import service.impl.UserManagerServiceImpl;
import utility.CountHelper;
import utility.LoginState;
import utility.PageState;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.util.Collections;

import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * Created by cdn on 17/3/19.
 */
@Controller
public class LoginAction extends BaseAction{

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    private static ApplicationContext applicationContext;
    private UserManagerService userManagerService;

    public String execute(){
        applicationContext=new ClassPathXmlApplicationContext("spring-config.xml");
        userManagerService=(UserManagerService) applicationContext.getBean("userManagerService");

        HttpSession session = request.getSession(false);
        ServletContext context = getServletContext();
        if (session.getAttribute("logout") != null){
            CountHelper.minusLogin(context);
            if (session != null){
                System.out.println("session disable");
                session.invalidate();
                session = null;
            }
            return "input";
        }
        if (request.getParameter("visitor") != null){
            System.out.println("visitor");
            CountHelper.addGuest(context);
            return "visitor";
        }else if (request.getParameter("visitor-out") != null){
            CountHelper.minusGuest(context);
            return "input";
        }

        //验证
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginState loginState = userManagerService.validateUser(username,password);
        StatesBean pageState = new StatesBean();
        if (loginState == LoginState.SUCCESS){
            if ((session == null)||(request.getAttribute("login") == null)) {

                session = request.getSession(true);
                session.setAttribute("login", username);
                request.setAttribute("login", username);

                CountHelper.addLogin(context);
                return "success";
            }
        }else if (loginState == LoginState.USERNOTEXIST) {
            pageState.setState(PageState.USERNOTFOUND);
            session.setAttribute("state", pageState);
            return "fail";
        }else if (loginState == LoginState.WRONGPWD) {
            pageState.setState(PageState.WRONGPWD);
            session.setAttribute("state", pageState);
            return "fail";
        }

        return "input";
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
