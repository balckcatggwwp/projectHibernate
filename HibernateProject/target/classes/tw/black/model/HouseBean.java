package tw.black.model;

public class HouseBean {
private int houseid;
private String housename;
	public HouseBean() {
		// TODO Auto-generated constructor stub
	}
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}
	public String getHousename() {
		return housename;
	}
	public void setHousename(String housename) {
		this.housename = housename;
	}
	public HouseBean(String housename) {
		super();
		this.housename = housename;
	}
	

}
