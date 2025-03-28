package com.tick.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "movietickvu")
@NoArgsConstructor
@Setter
@Getter
public class BookticketvuBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id@Column(name = "tickid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tickid;
	@Column(name = "orderid")
	private String orderid;
	@Column(name = "userid")
	private int userid;
	//private String startdate;
	@Column(name = "showid")
	private int showtimeid;
	@Column(name = "seatid")
	private String seatid;
	@Column(name = "hallid")
	private int hallid;
	@Column(name = "onemony")
	private int onemoney;
	

	@Column(name = "payout")
	private String payout;
	@Column(name = "showtimedate")
	private String startdate;
	@Column(name = "showtime")
	private String starttime;
	@Column(name = "ticktype")
	private String booktype;
	@Column(name = "ticktypeid")
	private int tickettypeid;
	@Column(name = "moviename")
	private String moviename;
	
	
//	public String getStartdate() {
//		return startdate;
//	}
//	public void setStartdate(String startdate) {
//		this.startdate = startdate;
//	}
//	public String getStarttime() {
//		return starttime;
//	}
//	public void setStarttime(String starttime) {
//		this.starttime = starttime;
//	}
//	public String getMoviename() {
//		return moviename;
//	}
//	public void setMoviename(String moviename) {
//		this.moviename = moviename;
//	}
//	public String getBooktype() {
//		return booktype;
//	}
//	public void setBooktype(String booktype) {
//		this.booktype = booktype;
//	}
//	public int getTickid() {
//		return tickid;
//	}
//	public void setTickid(int tickid) {
//		this.tickid = tickid;
//	}
//	public String getOrderid() {
//		return orderid;
//	}
//	public void setOrderid(String orderid) {
//		this.orderid = orderid;
//	}
//	public int getUserid() {
//		return userid;
//	}
//	public void setUserid(int userid) {
//		this.userid = userid;
//	}
//	
////	public String getShowtimeid() {
////		return showtimeid;
////	}
////	public void setShowtimeid(String showtimeid) {
////		this.showtimeid = showtimeid;
////	}
//	public String getSeatid() {
//		return seatid;
//	}
//	public int getShowtimeid() {
//		return showtimeid;
//	}
//	public void setShowtimeid(int showtimeid) {
//		this.showtimeid = showtimeid;
//	}
//	public void setSeatid(String seatid) {
//		this.seatid = seatid;
//	}
//	public int getHallid() {
//		return hallid;
//	}
//	public void setHallid(int hallid) {
//		this.hallid = hallid;
//	}
//	public int getOnemoney() {
//		return onemoney;
//	}
//	public void setOnemoney(int onemoney) {
//		this.onemoney = onemoney;
//	}
//	
//
//
//
//	
//	public String getPayout() {
//		return payout;
//	}
//	public void setPayout(String payout) {
//		this.payout = payout;
//	}
	
	

}
