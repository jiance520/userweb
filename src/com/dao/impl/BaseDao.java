package com.dao.impl;
import java.util.*;
import java.sql.*;
/**
 * @version ʱ�䣺2018��5��12�� ����9:16:21
 *
 */
public class BaseDao {
	protected Connection con = null;
	//��ʼ������
	public void init(){
		con = JdbcUtil.getConn();
	}
	//�ͷ���Դ
	public void close(){
		JdbcUtil.closeConn();
		con = null;
	}
}
