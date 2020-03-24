package com.hc.holocook.dto;

import java.sql.Timestamp;

public class QnaDto {
	private int 	qNo;
	private String 	qTitle;
	private String 	qContent;
	private int 	qGroup;
	private int 	qPno;
	private int 	qStep;
	private int 	qIndent;
	private String 	qFile;
	private int 	qHit;
	private Timestamp 	qRdate;
	private String 	qIp;
	private String 	aId;
	private String 	aName;
	private String 	mId;
	private String 	mName;
	private String 	mBlack;
	
	public QnaDto() {}

	public QnaDto(int qNo, String qTitle, String qContent, int qGroup, int qPno, int qStep, int qIndent, String qFile,
			int qHit, Timestamp qRdate, String qIp, String aId, String aName, String mId, String mName, String mBlack) {
		this.qNo = qNo;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qGroup = qGroup;
		this.qPno = qPno;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qFile = qFile;
		this.qHit = qHit;
		this.qRdate = qRdate;
		this.qIp = qIp;
		this.aId = aId;
		this.aName = aName;
		this.mId = mId;
		this.mName = mName;
		this.mBlack = mBlack;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public int getqGroup() {
		return qGroup;
	}

	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}

	public int getqPno() {
		return qPno;
	}

	public void setqPno(int qPno) {
		this.qPno = qPno;
	}

	public int getqStep() {
		return qStep;
	}

	public void setqStep(int qStep) {
		this.qStep = qStep;
	}

	public int getqIndent() {
		return qIndent;
	}

	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}

	public String getqFile() {
		return qFile;
	}

	public void setqFile(String qFile) {
		this.qFile = qFile;
	}

	public int getqHit() {
		return qHit;
	}

	public void setqHit(int qHit) {
		this.qHit = qHit;
	}

	public Timestamp getqRdate() {
		return qRdate;
	}

	public void setqRdate(Timestamp qRdate) {
		this.qRdate = qRdate;
	}

	public String getqIp() {
		return qIp;
	}

	public void setqIp(String qIp) {
		this.qIp = qIp;
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

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmBlack() {
		return mBlack;
	}

	public void setmBlack(String mBlack) {
		this.mBlack = mBlack;
	}

	@Override
	public String toString() {
		return "QnaDto [qNo=" + qNo + ", qTitle=" + qTitle + ", qContent=" + qContent + ", qGroup=" + qGroup + ", qPno="
				+ qPno + ", qStep=" + qStep + ", qIndent=" + qIndent + ", qFile=" + qFile + ", qHit=" + qHit
				+ ", qRdate=" + qRdate + ", qIp=" + qIp + ", aId=" + aId + ", aName=" + aName + ", mId=" + mId
				+ ", mName=" + mName + ", mBlack=" + mBlack + "]";
	}
	
}
