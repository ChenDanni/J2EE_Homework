package tag;

import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;
import java.io.IOException;

/**
 * Created by user on 17/1/4.
 */

public class CheckSessionHandler extends BodyTagSupport {

    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        String login = (String)pageContext.getSession().getAttribute("login");
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String path = request.getRequestURI();

        System.out.println("tag login check: " + login);
        System.out.println("current path : " + path);

        try {
            if (login == null){
                System.out.println("check session:null");
                if (path.equals("/EJB_war_exploded/jsp/ExamsInfo.jsp")){
                    response.sendRedirect("/EJB_war_exploded/jsp/Login.jsp");
                    return SKIP_PAGE;
                }
                return EVAL_PAGE;
            }else {
                System.out.println("check session:not null");
                System.out.println("path now : " + path);
                if (path.equals("/EJB_war_exploded/jsp/Login.jsp")){
                    response.sendRedirect("/EJB_war_exploded/ExamsInfo");
                    return SKIP_PAGE;
                }
                return EVAL_PAGE;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_PAGE;
    }

    @Override
    public int doAfterBody() throws JspException {

        return super.doAfterBody();
    }
}
