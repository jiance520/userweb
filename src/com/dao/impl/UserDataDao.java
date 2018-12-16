package com.dao.impl;
import java.util.ArrayList;
import java.util.List;

import com.dao.IUserDao;
import com.entity.UserData;

import java.sql.*;

import org.apache.log4j.Logger;

/**
 * @version ʱ�䣺2018��5��12�� ����9:21:31
 *����ʵ����
 */
public class UserDataDao extends BaseDao implements IUserDao{
	@Override
	public List<UserData> getAll() {
		init();//��ʼ��
		List<UserData> list = new ArrayList<UserData>();//�ü��ϴ�����ݣ�����Ľ��������ʹclose�ر������ӣ�����Ľ���������ͷ���Դ
		try {
			String sql = " select USID,UNAME,UPWD,decode(SEX,'1','��','0','Ů') as SEX,CARD_NO,EMAIL,MOBILE from userdata order by USID desc";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				//�ö��� ��װһ�����ݣ�ȡ�ֶΣ���������
				UserData userData = new UserData();
				userData.setUSID(rs.getInt("USID"));
				userData.setUNAME(rs.getString("UNAME"));
				userData.setUPWD(rs.getString("UPWD"));
				userData.setSEX(rs.getString("SEX"));
				userData.setCARD_NO(rs.getString("CARD_NO"));
				userData.setEMAIL(rs.getString("EMAIL"));
				userData.setMOBILE(rs.getLong("MOBILE"));
				//�Ѷ�����뼯��
				list.add(userData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//�ͷ���Դ
		return list;
	}

	@Override
	public UserData getOne(int USID) {
		init();//��ʼ��
		UserData userData = null;//�ü��ϴ�����ݣ�����Ľ��������ʹclose�ر������ӣ�����Ľ���������ͷ���Դ
		try {
			String sql = " select * from userdata where USID=? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, USID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				//ȡ�ֶΣ���������
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
		close();//�ͷ���Դ
		return userData;
	}

	@Override
	public int insert(UserData userData) {
		init();//��ʼ��
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
		close();//�ͷ���Դ
		return num;
	}

	@Override
	public int update(UserData userData) {
		init();//��ʼ��
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
		close();//�ͷ���Դ
		return num;
	}

	@Override
	public int delete(int USID) {
		init();//��ʼ��
		int num = 0;
		try {
			String sql = " delete from userdata where USID=? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,USID);
			num = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();//�ͷ���Դ
		return num;
	}
//	public static void main(String[] args) {
//		UserDataDao ud = new UserDataDao();
//	}
}
