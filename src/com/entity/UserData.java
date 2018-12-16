package com.entity;
/**
 * @version 时间：2018年5月12日 上午8:54:29
 *部门信息
 */
public class UserData {
	private int USID;//（用户id
	private String UNAME;
	private String UPWD;
	private String SEX;
	private String CARD_NO;
	private String EMAIL;
	private long MOBILE;
	public UserData() {
		super();
	}
	public UserData(int uSID, String uNAME, String uPWD, String sEX,
			String cARD_NO, String eMAIL, long mOBILE) {
		super();
		USID = uSID;
		UNAME = uNAME;
		UPWD = uPWD;
		SEX = sEX;
		CARD_NO = cARD_NO;
		EMAIL = eMAIL;
		MOBILE = mOBILE;
	}
	public int getUSID() {
		return USID;
	}
	public void setUSID(int uSID) {
		USID = uSID;
	}
	public String getUNAME() {
		return UNAME;
	}
	public void setUNAME(String uNAME) {
		UNAME = uNAME;
	}
	public String getUPWD() {
		return UPWD;
	}
	public void setUPWD(String uPWD) {
		UPWD = uPWD;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getCARD_NO() {
		return CARD_NO;
	}
	public void setCARD_NO(String cARD_NO) {
		CARD_NO = cARD_NO;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public long getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(long mOBILE) {
		MOBILE = mOBILE;
	}
	@Override
	public String toString() {
		return "UserData [USID=" + USID + ", UNAME=" + UNAME + ", UPWD=" + UPWD
				+ ", SEX=" + SEX + ", CARD_NO=" + CARD_NO + ", EMAIL=" + EMAIL
				+ ", MOBILE=" + MOBILE + "]";
	}
	
}
