package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.*;

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
        System.out.println("tag login check: " + login);
        if (login == null){
            return SKIP_PAGE;
        }else {
            return EVAL_PAGE;
        }
    }

    @Override
    public int doAfterBody() throws JspException {

        return super.doAfterBody();
    }
}
