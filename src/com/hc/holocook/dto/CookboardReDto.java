package com.hc.holocook.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CookboardReDto {
	private int 	cbrNo;
	private String	cbrContent;
	private String 	cbrIP;
	private Timestamp cbrRdate;
	private int 	cbNo;
	private String 	mId;
	private String 	mNick;
	private String 	mPhoto;
	private String 	mBlack;
	public CookboardReDto() {}
	public CookboardReDto(int cbrNo, String cbrContent, String cbrIP, Timestamp cbrRdate, int cbNo, String mId,
			String mNick, String mPhoto, String mBlack) {
		this.cbrNo = cbrNo;
		this.cbrContent = cbrContent;
		this.cbrIP = cbrIP;
		this.cbrRdate = cbrRdate;
		this.cbNo = cbNo;
		this.mId = mId;
		this.mNick = mNick;
		this.mPhoto = mPhoto;
		this.mBlack = mBlack;
	}
	public int getCbrNo() {
		return cbrNo;
	}
	public void setCbrNo(int cbrNo) {
		this.cbrNo = cbrNo;
	}
	public String getCbrContent() {
		return cbrContent;
	}
	public void setCbrContent(String cbrContent) {
		this.cbrContent = cbrContent;
	}
	public String getCbrIP() {
		return cbrIP;
	}
	public void setCbrIP(String cbrIP) {
		this.cbrIP = cbrIP;
	}
	public Timestamp getCbrRdate() {
		return cbrRdate;
	}
	public void setCbrRdate(Timestamp cbrRdate) {
		this.cbrRdate = cbrRdate;
	}
	public int getCbNo() {
		return cbNo;
	}
	public void setCbNo(int cbNo) {
		this.cbNo = cbNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmNick() {
		return mNick;
	}
	public void setmNick(String mNick) {
		this.mNick = mNick;
	}
	public String getmPhoto() {
		return mPhoto;
	}
	public void setmPhoto(String mPhoto) {
		this.mPhoto = mPhoto;
	}
	public String getmBlack() {
		return mBlack;
	}
	public void setmBlack(String mBlack) {
		this.mBlack = mBlack;
	}
	@Override
	public String toString() {
		return "CookboardReDto [cbrNo=" + cbrNo + ", cbrContent=" + cbrContent + ", cbrIP=" + cbrIP + ", cbrRdate="
				+ cbrRdate + ", cbNo=" + cbNo + ", mId=" + mId + ", mNick=" + mNick + ", mPhoto=" + mPhoto + ", mBlack="
				+ mBlack + "]";
	}
	
}
