package com.hc.holocook.dto;

import java.sql.Date;

public class MemberDto {
	private String 	mId		;
	private String 	mPw		;
	private String 	mNick	;
	private String 	mName	;
	private String 	mTel	;
	private String 	mEmail	;
	private String 	mPhoto	;
	private Date 	mRdate	;
	private int 	mBlack	;
	private int 	mLike	;
	private int		gNo		;
	private String	gName	;
	public MemberDto() {}
	public MemberDto(String mId, String mPw, String mNick, String mName, String mTel, String mEmail, String mPhoto,
			Date mRdate, int mBlack, int mLike, int gNo, String gName) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mNick = mNick;
		this.mName = mName;
		this.mTel = mTel;
		this.mEmail = mEmail;
		this.mPhoto = mPhoto;
		this.mRdate = mRdate;
		this.mBlack = mBlack;
		this.mLike = mLike;
		this.gNo = gNo;
		this.gName = gName;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmNick() {
		return mNick;
	}
	public void setmNick(String mNick) {
		this.mNick = mNick;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmTel() {
		return mTel;
	}
	public void setmTel(String mTel) {
		this.mTel = mTel;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPhoto() {
		return mPhoto;
	}
	public void setmPhoto(String mPhoto) {
		this.mPhoto = mPhoto;
	}
	public Date getmRdate() {
		return mRdate;
	}
	public void setmRdate(Date mRdate) {
		this.mRdate = mRdate;
	}
	public int getmBlack() {
		return mBlack;
	}
	public void setmBlack(int mBlack) {
		this.mBlack = mBlack;
	}
	public int getmLike() {
		return mLike;
	}
	public void setmLike(int mLike) {
		this.mLike = mLike;
	}
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mNick=" + mNick + ", mName=" + mName + ", mTel=" + mTel
				+ ", mEmail=" + mEmail + ", mPhoto=" + mPhoto + ", mRdate=" + mRdate + ", mBlack=" + mBlack + ", mLike="
				+ mLike + ", gNo=" + gNo + ", gName=" + gName + "]";
	}
	
	
}
