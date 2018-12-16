package com.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import java.sql.SQLException;
/**
 * @version 时间：2018年5月10日 上午10:05:42
 * 所有的类属性和方法都是静态的属性和方法,如果不初始化，都默认使用Oracle中的scott用户中的orcl
 */
public class JdbcUtil {//工具类，针对不同的数据库，使用同样的jdbc方法。
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private static String driver = "oracle.jdbc.driver.OracleDriver";//
	private static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
	private static String user = "scott";
	private static String password = "scott";
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rst = null;
	private static CallableStatement callableStatement = null;
//	static {//已经在连接中加载
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			System.out.println("加载驱动错误");
//			e.printStackTrace();
//		}
//	}
	public JdbcUtil() {
		super();
	}
//	使用构造方法初始化工具类,必须是非静态属性
    public JdbcUtil(String driver,String url ,String user,String password) {  
        this.driver = driver;  
        this.url = url;  
        this.user = user;  
        this.password = password;  
    }
	//	得到连接，所有的其它方法中都调用了连接。
	public static Connection getConn(){
		if(conn == null){
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,user,password);
			} catch (Exception e) {
				logger.debug("加载驱动错误");
				e.printStackTrace();
			}
		}
		return conn;
	}
	  /**   
     * insert update delete SQL语句的执行的统一方法   
     * @param sql SQL语句   
     * @param params 占位符?参数数组，若没有参数则为null   
     * @return 受影响的行数   
     */  
	public static int executeUpdate(String sql,Object[] params){
		conn = getConn();
		int affectedLine = 0;// 受影响的行数  
		try {
			pst = conn.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i,params[i]);
				}
			}
	/*在此 PreparedStatement 对象中执行 SQL 语句， 
            该语句必须是一个 SQL 数据操作语言（Data Manipulation Language，DML）语句，
            比如 INSERT、UPDATE 或 DELETE语句；或者是无返回内容的 SQL 语句，比如 DDL 语句。    */ 
			affectedLine = pst.executeUpdate();
		} catch (SQLException e) {
			logger.debug("executeUpdate查询错误");
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return affectedLine;
	}
	/**   
     * SQL 查询将查询结果直接放入ResultSet中   
     * @param sql SQL语句   
     * @param params 参数数组，若没有参数则为null   
     * @return 结果集   
     */ 
	public static ResultSet executeQueryRS(String sql,Object[] params){
		conn = getConn();
		try {
			pst = conn.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i,params[i]);
				}
			}
			rst = pst.executeQuery();
		} catch (SQLException e) {
			logger.debug("executeUpdateRS查询错误");
			e.printStackTrace();
		}
		finally{
			// 释放资源
			closeAll();
		}
		return rst;
	}
    /**   
     * 获取结果集，并将结果放在List中   
     * @param sql  SQL语句   
     * params  参数，没有则为null    
     * @return List 结果集   
     */ 
//	MetaData元数据
//	ResultSetMetaData存储了 ResultSet的MetaData。
	public static List<Object> exectueQuery(String sql,Object[] params){
		rst = executeQueryRS(sql,params);
		ResultSetMetaData rsmd = null;
//		获取集列数
		int columnCount = 0;
		try {
			rsmd = rst.getMetaData();//获取有关ResultSet结果集数据的信息，如结果集的字段数，字段名。但不是获取数据内容。
//			通过ResultSet的元数据，获得结果集列数
			columnCount = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();//可根据情况，改变泛型<Object>
		try {
			//将ResultSet的结果保存到List中
			while(rst.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for (int i = 0; i < columnCount; i++) {
					map.put(rsmd.getColumnLabel(i), rst.getObject(i));//rsmd.getColumnLabel(i),通过元数据获取具有唯一性的字段名，rs.getObject(i)每一个字段名对应一个字段的值。
//					用getColumnLabel查出的是咱们在后面重新定义的字段名
				}
				list.add(map);//每一个map代表一条记录，把所有的记录存在list中。
			}
			
		} catch (SQLException e) {
			logger.debug("exectueQuery查询错误");
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return list;
	}
	 /**   
     * 存储过程带有一个输出参数的方法   
     * @param sql 存储过程语句   
     * @param params 参数数组   
     * @param outParamPos 输出参数位置   
     * @param SqlType 输出参数类型   
     * @return 输出参数的值   
     */ 
	public static Object executeQuery(String sql,Object[] params,int outParamPos,int sqlType){
		Object object = null;
		conn = getConn();
		try {
			callableStatement = conn.prepareCall(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					callableStatement.setObject(i, params[i]);
				}
			}
			callableStatement.registerOutParameter(outParamPos, sqlType);
//			在查询之前注册，不需要查询的结果集，所以使用无返回值的execute()方法，输出参数在callableStatement对象中。
			callableStatement.execute();
			object = callableStatement.getObject(outParamPos);
		} catch (SQLException e) {
			logger.debug("exectueQuery查询错误");
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return object;
		
	}
	//	只关闭连接
	public static void closeConn(){
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	关闭全部
	public static void closeAll(){
		if(conn!=null){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try {
				pst.close();
				pst = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rst!=null){
			try {
				rst.close();
				rst = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(callableStatement!=null){
			try {
				callableStatement.close();
				callableStatement = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.debug("全部关闭成功");
	}
//	public static void main(String[] args) {
//		
//	}
}
