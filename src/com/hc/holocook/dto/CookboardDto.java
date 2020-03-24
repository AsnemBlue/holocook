package com.hc.holocook.dto;

import java.sql.Date;

public class CookboardDto {
	private int		cbNo   		;
    private String 	cbTitle		;
    private String 	cbImage		;
    private String 	cbIngredient;
    private int 	cbHit  		;
    private Date 	cbRdate		;
    private String 	cbIp   		;
    private int 	cbcNo  		;
    private String 	mId    		;
    private String 	cbLike  	;
    private String  mNick		;
    private String  mPhoto		;
	public CookboardDto() {}
	public CookboardDto(int cbNo, String cbTitle, String cbImage, String cbIngredient, int cbHit, Date cbRdate,
			String cbIp, int cbcNo, String mId, String cbLike, String mNick, String mPhoto) {
		this.cbNo = cbNo;
		this.cbTitle = cbTitle;
		this.cbImage = cbImage;
		this.cbIngredient = cbIngredient;
		this.cbHit = cbHit;
		this.cbRdate = cbRdate;
		this.cbIp = cbIp;
		this.cbcNo = cbcNo;
		this.mId = mId;
		this.cbLike = cbLike;
		this.mNick = mNick;
		this.mPhoto = mPhoto;
	}
	public int getCbNo() {
		return cbNo;
	}
	public void setCbNo(int cbNo) {
		this.cbNo = cbNo;
	}
	public String getCbTitle() {
		return cbTitle;
	}
	public void setCbTitle(String cbTitle) {
		this.cbTitle = cbTitle;
	}
	public String getCbImage() {
		return cbImage;
	}
	public void setCbImage(String cbImage) {
		this.cbImage = cbImage;
	}
	public String getCbIngredient() {
		return cbIngredient;
	}
	public void setCbIngredient(String cbIngredient) {
		this.cbIngredient = cbIngredient;
	}
	public int getCbHit() {
		return cbHit;
	}
	public void setCbHit(int cbHit) {
		this.cbHit = cbHit;
	}
	public Date getCbRdate() {
		return cbRdate;
	}
	public void setCbRdate(Date cbRdate) {
		this.cbRdate = cbRdate;
	}
	public String getCbIp() {
		return cbIp;
	}
	public void setCbIp(String cbIp) {
		this.cbIp = cbIp;
	}
	public int getCbcNo() {
		return cbcNo;
	}
	public void setCbcNo(int cbcNo) {
		this.cbcNo = cbcNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getCbLike() {
		return cbLike;
	}
	public void setCbLike(String cbLike) {
		this.cbLike = cbLike;
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
	@Override
	public String toString() {
		return "CookboardDto [cbNo=" + cbNo + ", cbTitle=" + cbTitle + ", cbImage=" + cbImage + ", cbIngredient="
				+ cbIngredient + ", cbHit=" + cbHit + ", cbRdate=" + cbRdate + ", cbIp=" + cbIp + ", cbcNo=" + cbcNo
				+ ", mId=" + mId + ", cbLike=" + cbLike + ", mNick=" + mNick + ", mPhoto=" + mPhoto + "]";
	}
			
}
