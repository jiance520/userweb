<%@ page language="java" import="java.util.*,java.io.*,com.entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//接收信息
Object obj = request.getAttribute("userList");
List<UserData> userList = new ArrayList<UserData>();
if(obj!= null){
	userList = (List<UserData>)obj;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    <h1>用户信息</h1>
    <table align="center" border="1" width="80%">
    	<tr>
    		<td>用户id</td><td>姓名</td><td>密码</td><td>性别</td><td>身份证号码</td><td>邮箱</td><td>手机</td><td>删除</td><td>修改</td>
    	</tr>
    	
    	<%
    	  for(UserData uu:userList){
    	 %>
    	<tr>
    		<td><a href="Update?USID=<%=uu.getUSID()%>"><%=uu.getUSID()%></a></td>
    		<td><%=uu.getUNAME() %></td>
    		<td><%=uu.getUPWD() %></td>
    		<td><%=uu.getSEX() %></td>
    		<td><%=uu.getCARD_NO() %></td>
    		<td><%=uu.getEMAIL() %></td>
    		<td><%=uu.getMOBILE() %></td>
    		<td><a href="DeleteAction?USID=<%=uu.getUSID()%>">删除</a></td>
    		<td><a href="Update?USID=<%=uu.getUSID()%>">修改</a></td>
    	</tr>
    	<%}%>   	
    </table>
  	<h2><a href="useradd.jsp">新增用户</a></h2>
  </body>
</html>
