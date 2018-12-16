package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.IUserDao;
import com.dao.impl.JdbcUtil;
import com.dao.impl.UserDataDao;
import com.entity.UserData;

/**
 * @version ʱ�䣺2018��5��21�� ����12:17:35
 *
 */
public class Update extends HttpServlet {
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private IUserDao userDao = new UserDataDao();
	/**
	 * Constructor of the object.
	 */
	public Update() {
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
		//�鲿����Ϣ
		String USID = request.getParameter("USID");

		int usid = 0;
		try {
			usid = Integer.parseInt(USID);
		} catch (Exception e) {
			usid =0;
			e.printStackTrace();
		}
		UserData userData = userDao.getOne(usid);
		int num = 10;
		//ͨ��request��ֵ��ҳ���ϡ�
		request.setAttribute("userData", userData);
		//request.setAttribute("num", num);
		//����ͨ��request��ֵ��ҳ���ϣ�����Ҫת����
		//�Զ���ת��ת��
		request.getRequestDispatcher("userupdate.jsp").forward(request,response);
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
