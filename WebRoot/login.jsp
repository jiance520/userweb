<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String error = request.getParameter("error");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>登录页面</h1>
    <h2>
       <% if(error != null && error.equals("1")){%>
                        用户名或密码错误
       <% }%>
    </h2>
     <form action="iflogin.jsp" method="post">
        <h3>用户姓名：<input type="text" name="uname"/></h3>
        <h3>用户密码：<input type="password" name="upwd"/></h3>
        <p><input type="submit" value="登录"/></p>
    </form>
  </body>
</html>
