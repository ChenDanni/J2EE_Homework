package utility;

import javax.servlet.ServletContext;

/**
 * Created by chendanni on 17/2/22.
 * count++ & count--
 */
public class CountHelper {
    private static int login = 0;
    private static int total = 0;

    private static void getLoginTotal(ServletContext context){
        total = Integer.parseInt((String)context.getAttribute("total"));
        login = Integer.parseInt((String)context.getAttribute("login"));
    }
    private static void setContext(ServletContext context){
        context.setAttribute("total", Integer.toString(total));
        context.setAttribute("login", Integer.toString(login));
    }

    public static void addLogin(ServletContext context){
        getLoginTotal(context);
        total++;
        login++;
        setContext(context);
    }

    public static void minusLogin(ServletContext context){
        getLoginTotal(context);
        total--;
        login--;
        setContext(context);
    }

    public static void addGuest(ServletContext context){
        getLoginTotal(context);
        total++;
        setContext(context);
    }

    public static void minusGuest(ServletContext context){
        getLoginTotal(context);
        total--;
        setContext(context);
    }
}
