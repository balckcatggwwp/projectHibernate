package com.tick.bean;

public class BookorderBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L; 
	private String orderid;
	private int userid;
	private int sumpay;
	private String orderdate;
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getSumpay() {
		return sumpay;
	}
	public void setSumpay(int sumpay) {
		this.sumpay = sumpay;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	
	
	

}
