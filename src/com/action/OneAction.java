package com.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import com.dao.impl.*;

import java.util.*;

import com.entity.*;

/**
 * @version 时间：2018年5月17日 上午10:58:26
 *
 */
public class OneAction extends HttpServlet {
	private IUserDao userDao = new UserDataDao();
	/**
	 * Constructor of the object.
	 */
	public OneAction() {
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
		String USID = request.getParameter("USID");
		int usid = 0;
		try {
			usid = Integer.parseInt(USID);
		} catch (Exception e) {
			usid =0;
			e.printStackTrace();
		}
		UserData userData = userDao.getOne(usid);//取原始数据
		request.setAttribute("dobj", userData);
		request.getRequestDispatcher("userupdate.jsp").forward(request, response);;
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
