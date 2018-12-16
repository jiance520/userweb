package com.dao.impl;
import java.util.ArrayList;
import java.util.List;

import com.dao.IUserDao;
import com.entity.UserData;

import java.sql.*;

import org.apache.log4j.Logger;

/**
 * @version 时间：2018年5月12日 上午9:21:31
 *部门实现类
 */
public class UserDataDao extends BaseDao implements IUserDao{
	@Override
	public List<UserData> getAll() {
		init();//初始化
		List<UserData> list = new ArrayList<UserData>();//用集合打包数据，打包的结果集，即使close关闭了连接，打包的结果集不会释放资源
		try {
			String sql = " select USID,UNAME,UPWD,decode(SEX,'1','男','0','女') as SEX,CARD_NO,EMAIL,MOBILE from userdata order by USID desc";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				//用对象 封装一行数据，取字段，设置属性
				UserData userData = new UserData();
				userData.setUSID(rs.getInt("USID"));
				userData.setUNAME(rs.getString("UNAME"));
				userData.setUPWD(rs.getString("UPWD"));
				userData.setSEX(rs.getString("SEX"));
				userData.setCARD_NO(rs.getString("CARD_NO"));
				userData.setEMAIL(rs.getString("EMAIL"));
				userData.setMOBILE(rs.getLong("MOBILE"));
				//把对象放入集合
				list.add(userData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//释放资源
		return list;
	}

	@Override
	public UserData getOne(int USID) {
		init();//初始化
		UserData userData = null;//用集合打包数据，打包的结果集，即使close关闭了连接，打包的结果集不会释放资源
		try {
			String sql = " select * from userdata where USID=? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, USID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				//取字段，设置属性
				userData = new UserData();
				userData.setUSID(rs.getInt("USID"));		
				userData.setUNAME(rs.getString("UNAME"));
				userData.setUPWD(rs.getString("UPWD"));
				userData.setSEX(rs.getString("SEX"));
				userData.setCARD_NO(rs.getString("CARD_NO"));
				userData.setEMAIL(rs.getString("EMAIL"));
				userData.setMOBILE(rs.getLong("MOBILE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//释放资源
		return userData;
	}

	@Override
	public int insert(UserData userData) {
		init();//初始化
		int num = 0;
		try {
			String sql = " insert into userdata values(?,?,?,?,?,?,?) ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userData.getUSID());
			stmt.setString(2, userData.getUNAME());
			stmt.setString(3, userData.getUPWD());
			stmt.setString(4, userData.getSEX());
			stmt.setString(5, userData.getCARD_NO());
			stmt.setString(6, userData.getEMAIL());
			stmt.setLong(7, userData.getMOBILE());
			num = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//释放资源
		return num;
	}

	@Override
	public int update(UserData userData) {
		init();//初始化
		int num = 0;
		try {
			String sql = " update userdata set UNAME=?,UPWD=?,SEX=?,CARD_NO=?,EMAIL=?,MOBILE=? where USID=? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userData.getUNAME());
			stmt.setString(2, userData.getUPWD());
			stmt.setString(3, userData.getSEX());
			stmt.setString(4, userData.getCARD_NO());
			stmt.setString(5, userData.getEMAIL());
			stmt.setLong(6, userData.getMOBILE());
			stmt.setInt(7, userData.getUSID());
			num = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//释放资源
		return num;
	}

	@Override
	public int delete(int USID) {
		init();//初始化
		int num = 0;
		try {
			String sql = " delete from userdata where USID=? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,USID);
			num = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//释放资源
		return num;
	}
//	public static void main(String[] args) {
//		UserDataDao ud = new UserDataDao();
//	}
}
