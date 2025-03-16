package tw.black.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity@Table(name = "role")
public class Role {
	@Id @Column(name = "ROLEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleid;
	@Column(name = "ROLENAME")
	private String rolename;
	@Column(name = "SKILL")
	private String skill;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "gamerole",joinColumns = {@JoinColumn(name="ROLEID")},inverseJoinColumns = {@JoinColumn(name="GAMEID")})
	private Set<Game> games= new HashSet<Game>();
	
	
	
	public int getRoleid() {
		return roleid;
	}



	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}



	public String getRolename() {
		return rolename;
	}



	public void setRolename(String rolename) {
		this.rolename = rolename;
	}



	public String getSkill() {
		return skill;
	}



	public void setSkill(String skill) {
		this.skill = skill;
	}



	public Set<Game> getGames() {
		return games;
	}



	public void setGames(Set<Game> games) {
		this.games = games;
	}



	public Role(String rolename, String skill) {
		super();
		this.rolename = rolename;
		this.skill = skill;
	}



	public Role() {
	}

}
