<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
   // 不要显示，只处理逻辑 
//post 提交 的乱码处理,写在接收之前
request.setCharacterEncoding("UTF-8");

// 请求：request
      // 接收参数  
      String upwd = request.getParameter("upwd");
      String uname = request.getParameter("uname");
      
      if(uname.equals("管理员") && upwd.equals("admin")){
    	  //自动跳转:重定向
    	  response.sendRedirect("index.jsp");
      }else{
    	  response.sendRedirect("login.jsp?error=1"); 
      }
%>
