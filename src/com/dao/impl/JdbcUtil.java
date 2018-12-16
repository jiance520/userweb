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
 * @version ʱ�䣺2018��5��10�� ����10:05:42
 * ���е������Ժͷ������Ǿ�̬�����Ժͷ���,�������ʼ������Ĭ��ʹ��Oracle�е�scott�û��е�orcl
 */
public class JdbcUtil {//�����࣬��Բ�ͬ�����ݿ⣬ʹ��ͬ����jdbc������
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());
	private static String driver = "oracle.jdbc.driver.OracleDriver";//
	private static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
	private static String user = "scott";
	private static String password = "scott";
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rst = null;
	private static CallableStatement callableStatement = null;
//	static {//�Ѿ��������м���
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			System.out.println("������������");
//			e.printStackTrace();
//		}
//	}
	public JdbcUtil() {
		super();
	}
//	ʹ�ù��췽����ʼ��������,�����ǷǾ�̬����
    public JdbcUtil(String driver,String url ,String user,String password) {  
        this.driver = driver;  
        this.url = url;  
        this.user = user;  
        this.password = password;  
    }
	//	�õ����ӣ����е����������ж����������ӡ�
	public static Connection getConn(){
		if(conn == null){
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,user,password);
			} catch (Exception e) {
				logger.debug("������������");
				e.printStackTrace();
			}
		}
		return conn;
	}
	  /**   
     * insert update delete SQL����ִ�е�ͳһ����   
     * @param sql SQL���   
     * @param params ռλ��?�������飬��û�в�����Ϊnull   
     * @return ��Ӱ�������   
     */  
	public static int executeUpdate(String sql,Object[] params){
		conn = getConn();
		int affectedLine = 0;// ��Ӱ�������  
		try {
			pst = conn.prepareStatement(sql);
			if(params!=null){
				for (int i = 0; i < params.length; i++) {
					pst.setObject(i,params[i]);
				}
			}
	/*�ڴ� PreparedStatement ������ִ�� SQL ��䣬 
            ����������һ�� SQL ���ݲ������ԣ�Data Manipulation Language��DML����䣬
            ���� INSERT��UPDATE �� DELETE��䣻�������޷������ݵ� SQL ��䣬���� DDL ��䡣    */ 
			affectedLine = pst.executeUpdate();
		} catch (SQLException e) {
			logger.debug("executeUpdate��ѯ����");
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return affectedLine;
	}
	/**   
     * SQL ��ѯ����ѯ���ֱ�ӷ���ResultSet��   
     * @param sql SQL���   
     * @param params �������飬��û�в�����Ϊnull   
     * @return �����   
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
			logger.debug("executeUpdateRS��ѯ����");
			e.printStackTrace();
		}
		finally{
			// �ͷ���Դ
			closeAll();
		}
		return rst;
	}
    /**   
     * ��ȡ������������������List��   
     * @param sql  SQL���   
     * params  ������û����Ϊnull    
     * @return List �����   
     */ 
//	MetaDataԪ����
//	ResultSetMetaData�洢�� ResultSet��MetaData��
	public static List<Object> exectueQuery(String sql,Object[] params){
		rst = executeQueryRS(sql,params);
		ResultSetMetaData rsmd = null;
//		��ȡ������
		int columnCount = 0;
		try {
			rsmd = rst.getMetaData();//��ȡ�й�ResultSet��������ݵ���Ϣ�����������ֶ������ֶ����������ǻ�ȡ�������ݡ�
//			ͨ��ResultSet��Ԫ���ݣ���ý��������
			columnCount = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();//�ɸ���������ı䷺��<Object>
		try {
			//��ResultSet�Ľ�����浽List��
			while(rst.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for (int i = 0; i < columnCount; i++) {
					map.put(rsmd.getColumnLabel(i), rst.getObject(i));//rsmd.getColumnLabel(i),ͨ��Ԫ���ݻ�ȡ����Ψһ�Ե��ֶ�����rs.getObject(i)ÿһ���ֶ�����Ӧһ���ֶε�ֵ��
//					��getColumnLabel������������ں������¶�����ֶ���
				}
				list.add(map);//ÿһ��map����һ����¼�������еļ�¼����list�С�
			}
			
		} catch (SQLException e) {
			logger.debug("exectueQuery��ѯ����");
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return list;
	}
	 /**   
     * �洢���̴���һ����������ķ���   
     * @param sql �洢�������   
     * @param params ��������   
     * @param outParamPos �������λ��   
     * @param SqlType �����������   
     * @return ���������ֵ   
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
//			�ڲ�ѯ֮ǰע�ᣬ����Ҫ��ѯ�Ľ����������ʹ���޷���ֵ��execute()���������������callableStatement�����С�
			callableStatement.execute();
			object = callableStatement.getObject(outParamPos);
		} catch (SQLException e) {
			logger.debug("exectueQuery��ѯ����");
			e.printStackTrace();
		}
		finally{
			closeAll();
		}
		return object;
		
	}
	//	ֻ�ر�����
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
//	�ر�ȫ��
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
		logger.debug("ȫ���رճɹ�");
	}
//	public static void main(String[] args) {
//		
//	}
}
