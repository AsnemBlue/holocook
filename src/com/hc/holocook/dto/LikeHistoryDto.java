package com.hc.holocook.dto;

public class LikeHistoryDto {
	private int cbNo;
	private String mId;
	public LikeHistoryDto() {}
	public LikeHistoryDto(int cbNo, String mId) {
		this.cbNo = cbNo;
		this.mId = mId;
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
	@Override
	public String toString() {
		return "LikeHistoryDto [cbNo=" + cbNo + ", mId=" + mId + "]";
	}
	
}
