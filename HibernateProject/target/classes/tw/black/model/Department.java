package tw.black.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity @Table(name="department")
//@Setter @Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Department {
	
	@Id @Column(name="DEPTID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	private int deptid;
	
	@Column(name = "DEPTNAME") //跟資料庫一樣可以不寫 但最好不要
	@NonNull
	private String deptname;
	
//	public int getDeptid() {
//		return deptid;
//	}
//	public void setDeptid(int deptid) {
//		this.deptid = deptid;
//	}
//	public String getDeptname() {
//		return deptname;
//	}
//	public void setDeptname(String deptname) {
//		this.deptname = deptname;
//	}

}
