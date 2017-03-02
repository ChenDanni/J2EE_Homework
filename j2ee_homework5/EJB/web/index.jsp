<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/2/21
  Time: 下午8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.Hello"%>
<%@page import="javax.naming.NamingException"%>
<%@ page import="java.util.Hashtable" %>
<%@ page import="javax.naming.Context" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
  <title>测试页</title>
</head>
<body>
ejb.Hello World!!!!!
<%
  final String appName = "";
  final String moduleName = "EJBTest_war_exploded";
  final String distinctName = "";
  final String beanName = "HelloEJB";
  final String viewClassName = Hello.class.getName();
  final String namespace = "ejb:" + appName + "/" + moduleName
          + "/" + distinctName + "/" + beanName + "!" + viewClassName;
  System.out.println(namespace);
  try {
    final Hashtable jndiProperties = new Hashtable();
    jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    final Context context = new InitialContext(jndiProperties);
    Hello helloWorld = (Hello) context.lookup(namespace);
    System.out.println(helloWorld);
    String s = helloWorld.sayHello("hello");
    System.out.println(s);
  }catch (NamingException e) {
    e.printStackTrace();
  }
%>
</body>
</html>