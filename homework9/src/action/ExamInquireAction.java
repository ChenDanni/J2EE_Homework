package action;

import action.business.ExamsInfoBean;
import action.business.StatesBean;
import action.business.StatisticsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import service.ExamInquireService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import static org.apache.struts2.ServletActionContext.getServletContext;

/**
 * Created by cdn on 17/3/19.
 */
@Controller
public class ExamInquireAction extends BaseAction{

    private static final long serialVersionUID = 1L;
    private static ApplicationContext applicationContext;

    private ExamInquireService examInquireService;

    public String execute(){
        applicationContext=new ClassPathXmlApplicationContext("spring-config.xml");
        examInquireService=(ExamInquireService) applicationContext.getBean("examInquireService");
        HttpSession session = request.getSession(true);
        ServletContext context = getServletContext();
        ExamsInfoBean examsInfo = new ExamsInfoBean();
        StatisticsBean statistics = new StatisticsBean();
        String username = (String)session.getAttribute("login");
        StatesBean states = new StatesBean();
        System.out.println("username : " + username);

        if (request.getParameter("logout") != null){
            session.setAttribute("logout","logout");
            return "logout";
        }


        if (username == null){

            return "input";
        }

        examsInfo.setExamsInfoList(examInquireService.getExamsInfo(username));
        states.setState(examInquireService.getPageState(username));
        int total = Integer.parseInt((String)context.getAttribute("total"));
        int login = Integer.parseInt((String)context.getAttribute("login"));
        statistics.setTotal(total);
        statistics.setLogin(login);
        statistics.setVisitor(total - login);

        try{
            if (examsInfo.getExamsInfoList().size() < 1){

            }else {
                session.setAttribute("examsInfo", examsInfo);
                session.setAttribute("statistics", statistics);
                session.setAttribute("states", states);
                return "success";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "input";
    }



}
