<%@ page language="java" import="java.util.*,com.entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//接收信息
Object obj = request.getAttribute("userData");
UserData userData = new UserData();
if(obj!=null){
	userData =  (UserData)obj;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deptupdate.jsp' starting page</title>
    
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
    <h1>修改用户</h1>
    <form action="UpdateAction1" method="post">
    	<p>
    		用户id:<input type="text" readonly="readonly" name="USID" value="<%=userData.getUSID() %>"/>
    	</p>
    	   	<p>
    		姓名:<input type="text" name="UNAME" required  value="<%=userData.getUNAME() %>"/>
    	</p>
    	<p>
    		密码:<input type="text" name="UPWD" required  value="<%=userData.getUPWD() %>"/>
    	</p>
    	<p>
    		性别:<input type="text" name="SEX" required  value="<%=userData.getSEX() %>"/>
    	</p>
    	<p>
    		身份证号码:<input type="text" name="CARD_NO" required  value="<%=userData.getCARD_NO() %>"/>
    	</p>
    	<p>
    		邮箱:<input type="text" name="EMAIL" required  value="<%=userData.getEMAIL() %>"/>
    	</p>
    	<p>
    		手机:<input type="text" name="MOBILE" required  value="<%=userData.getMOBILE() %>"/>
    	</p>
    	<p>
    		<input type="submit" value="修改"/>
    	</p>
    </form>
  </body>
</html>
