package com.hc.holocook.dto;

public class CookboardDetailDto {
	private int 	cbdNo  		;
	private int		cbNo   		;
	private int		cbdOrder   	;
	private String	cbdImage   	;
	private String	cbdContent	;
	private int		cbdCount	;
	public CookboardDetailDto() {}
	public CookboardDetailDto(int cbdNo, int cbNo, int cbdOrder, String cbdImage, String cbdContent, int cbdCount) {
		this.cbdNo = cbdNo;
		this.cbNo = cbNo;
		this.cbdOrder = cbdOrder;
		this.cbdImage = cbdImage;
		this.cbdContent = cbdContent;
		this.cbdCount = cbdCount;
	}
	public int getCbdNo() {
		return cbdNo;
	}
	public void setCbdNo(int cbdNo) {
		this.cbdNo = cbdNo;
	}
	public int getCbNo() {
		return cbNo;
	}
	public void setCbNo(int cbNo) {
		this.cbNo = cbNo;
	}
	public int getCbdOrder() {
		return cbdOrder;
	}
	public void setCbdOrder(int cbdOrder) {
		this.cbdOrder = cbdOrder;
	}
	public String getCbdImage() {
		return cbdImage;
	}
	public void setCbdImage(String cbdImage) {
		this.cbdImage = cbdImage;
	}
	public String getCbdContent() {
		return cbdContent;
	}
	public void setCbdContent(String cbdContent) {
		this.cbdContent = cbdContent;
	}
	public int getCbdCount() {
		return cbdCount;
	}
	public void setCbdCount(int cbdCount) {
		this.cbdCount = cbdCount;
	}
	@Override
	public String toString() {
		return "CookboardDetailDto [cbdNo=" + cbdNo + ", cbNo=" + cbNo + ", cbdOrder=" + cbdOrder + ", cbdImage="
				+ cbdImage + ", cbdContent=" + cbdContent + ", cbdCount=" + cbdCount + "]";
	}
	
}
