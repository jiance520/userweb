<%@ page language="java" import="java.util.*,java.io.*,com.entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//接收信息
Object robj = request.getAttribute("dobj");
UserData dobj = new UserData();
if(robj!=null){
	dobj =  (UserData)robj;
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
    <h1>新增用户</h1>
    <form action="AddeAction" method="post">
    	<p>
    		用户id:<input type="text" name="USID" required value="<%=dobj.getUSID() %>"/>
    	</p>
    	<p>
    		姓名:<input type="text" name="UNAME" required  value="<%=dobj.getUNAME() %>"/>
    	</p>
    	<p>
    		密码:<input type="text" name="UPWD" required  value="<%=dobj.getUPWD() %>"/>
    	</p>
    	<p>
    		性别:<input type="text" name="SEX" required  value="<%=dobj.getSEX() %>"/>
    	</p>
    	<p>
    		身份证号码:<input type="text" name="CARD_NO" required  value="<%=dobj.getCARD_NO() %>"/>
    	</p>
    	<p>
    		邮箱:<input type="text" name="EMAIL" required  value="<%=dobj.getEMAIL() %>"/>
    	</p>
    	<p>
    		手机:<input type="text" name="MOBILE" required  value="<%=dobj.getMOBILE() %>"/>
    	</p>
    	<p>
    		<input type="submit" value="新增"/>
    	</p>
    </form>
  
  </body>
</html>
