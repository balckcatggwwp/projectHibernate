package com.tick.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "Showtime")
public class ShowtimeBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L; 
	@Id @Column(name = "showtimeid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int showtimeid;
	@Column(name = "startdate")
	private String startdate;
	@Column(name = "starttime")
	private String starttime; 
	
	public int getShowtimeid() {
		return showtimeid;
	}
	public void setShowtimeid(int showtimeid) {
		this.showtimeid = showtimeid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}



	

	
	
	

}
