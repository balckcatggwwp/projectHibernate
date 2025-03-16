package com.tick.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity@Table(name = "Booktype")
public class BookTypeBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L; 
	@Id @Column(name = "tickettypeid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tickettypeid;
	@Column(name = "tickettype")
	private String tickettype;
	@Column(name = "moneytype")
	private int moneytype;
	
	
	public String getTickettype() {
		return tickettype;
	}
	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
	}
	public int getMoneytype() {
		return moneytype;
	}
	public void setMoneytype(int moneytype) {
		this.moneytype = moneytype;
	}
	public int getTickettypeid() {
		return tickettypeid;
	}
	public void setTickettypeid(int tickettypeid) {
		this.tickettypeid = tickettypeid;
	}
	

	
	
	

}
