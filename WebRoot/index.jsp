<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String name = "小明";
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
    <h1>my page</h1>
    <h1>name=:${name}</h1>
    <h1>name=:<c:out value="${name}"/></h1>
    <c:set var="num3" value="11" scope="request"/>
    <c:if test="${(!empty num3)&&(1==1)}">
    	输出:${num3}。</br>
    	<c:out value="${num3}"/>
    </c:if>
    <c:choose>
    	<c:when test="">
    		输出内容
    	</c:when>
    	<c:when test=""></c:when>
    	<c:otherwise></c:otherwise>
    </c:choose>
    <c:forEach var="集合的成员变量名var" items="${数组} }"></c:forEach>
    <a href="UserAction">用户信息</a>

  </body>
</html>
