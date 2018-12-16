package com.dao.impl;
import java.util.*;
import java.sql.*;
/**
 * @version 时间：2018年5月12日 上午9:16:21
 *
 */
public class BaseDao {
	protected Connection con = null;
	//初始化连接
	public void init(){
		con = JdbcUtil.getConn();
	}
	//释放资源
	public void close(){
		JdbcUtil.closeConn();
		con = null;
	}
}
