package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.IUserDao;
import com.entity.UserData;
import com.dao.impl.UserDataDao;

/**
 * @version 时间：2018年5月19日 上午10:45:46
 *
 */
public class AddeAction extends HttpServlet {
	private IUserDao userDao = new UserDataDao();

	/**
	 * Constructor of the object.
	 */
	public AddeAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//接收参数
		String USID = request.getParameter("USID");
		String UNAME = request.getParameter("UNAME");
		String UPWD= request.getParameter("UPWD");
		String SEX= request.getParameter("SEX");
		String CARD_NO= request.getParameter("CARD_NO");
		String EMAIL= request.getParameter("EMAIL");
		String MOBILE= request.getParameter("MOBILE");
		//转型
		int usid = 0;
		try {
			usid = Integer.parseInt(USID);
		} catch (Exception e) {
			usid =0;
			e.printStackTrace();
		}
		long mobile = 0;
		try {
			mobile = Long.parseLong(MOBILE);
		} catch (Exception e) {
			mobile =0;
			e.printStackTrace();
		}
		//用对象封装数据
		UserData userData = new UserData(usid,UNAME,UPWD,SEX,CARD_NO,EMAIL,mobile);
		//修改
		int n2 = userDao.insert(userData);
		//自动跳转：注意是要去部门信息页面逻辑变化，不要转发了，用重定向,要去servlet，不要去jsp，因为页面还是要取值。
		response.sendRedirect("UserAction");//user.jsp
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
