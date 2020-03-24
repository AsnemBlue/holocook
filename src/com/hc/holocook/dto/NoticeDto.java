package com.hc.holocook.dto;

import java.sql.Date;

public class NoticeDto {
	private int    nNo;
	private String nTitle;
	private String nContent;
	private int    nHit;
	private Date   nRdate;
	private String nIp;
	private String aId;
	private String aName;
	public NoticeDto() {}
	public NoticeDto(int nNo, String nTitle, String nContent, int nHit, Date nRdate, String nIp, String aId,
			String aName) {
		this.nNo = nNo;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nHit = nHit;
		this.nRdate = nRdate;
		this.nIp = nIp;
		this.aId = aId;
		this.aName = aName;
	}
	public int getnNo() {
		return nNo;
	}
	public void setnNo(int nNo) {
		this.nNo = nNo;
	}
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public int getnHit() {
		return nHit;
	}
	public void setnHit(int nHit) {
		this.nHit = nHit;
	}
	public Date getnRdate() {
		return nRdate;
	}
	public void setnRdate(Date nRdate) {
		this.nRdate = nRdate;
	}
	public String getnIp() {
		return nIp;
	}
	public void setnIp(String nIp) {
		this.nIp = nIp;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	@Override
	public String toString() {
		return "NoticeDto [nNo=" + nNo + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nHit=" + nHit
				+ ", nRdate=" + nRdate + ", nIp=" + nIp + ", aId=" + aId + ", aName=" + aName + "]";
	}
	
}	
