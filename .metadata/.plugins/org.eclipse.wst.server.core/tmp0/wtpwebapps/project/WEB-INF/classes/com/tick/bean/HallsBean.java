package com.tick.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity@Table(name = "halls")
public class HallsBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L; 
	@Id @Column(name = "hallid")
	private int hallid;
	public int getHallid() {
		return hallid;
	}
	public void setHallid(int hallid) {
		this.hallid = hallid;
	}
	


	

	
	
	

}
