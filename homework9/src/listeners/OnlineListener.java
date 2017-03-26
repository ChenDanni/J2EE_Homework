package listeners;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.sound.midi.Soundbank;

@WebListener
public class OnlineListener implements ServletContextListener,ServletContextAttributeListener{
	int total;
	int login;
	int visitor;
	
	String onlineFilePath = "/Users/user/Documents/un/s6/j2ee/homework/homework9/web/online.txt";
	
	public void contextInitialized(ServletContextEvent cse){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(onlineFilePath));
			total = Integer.parseInt(reader.readLine());
			login = Integer.parseInt(reader.readLine());
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ServletContext servletContext = cse.getServletContext();
		servletContext.setAttribute("total", Integer.toString(total));
		servletContext.setAttribute("login", Integer.toString(login));
		System.out.println("Application Initialized");
	}
	public void attributeAdded(ServletContextAttributeEvent arg0){
		System.out.println("ServletContextAttribute added");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

	}

	public void attributeReplaced(ServletContextAttributeEvent scae){
		System.out.println("ServletContextattribute replaced");
		writeCounter(scae);
	}
	synchronized void writeCounter(ServletContextAttributeEvent scae){
		System.out.println("in replace");
		ServletContext servletContext = scae.getServletContext();
		total = Integer.parseInt((String) servletContext.getAttribute("total"));
		login = Integer.parseInt((String) servletContext.getAttribute("login"));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(onlineFilePath));
			writer.write(Integer.toString(total));
			writer.newLine();
			writer.write(Integer.toString(login));
			writer.close();
			System.out.println("writing");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Application shut down"); 
	}
}
