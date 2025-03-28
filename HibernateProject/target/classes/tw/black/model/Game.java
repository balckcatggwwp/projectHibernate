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

@Entity @Table(name = "game")
public class Game {
	
	@Id @Column(name = "GAMEID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gameid;
	@Column(name = "GAMENAME")
	private String gamename;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "gamerole",joinColumns = {@JoinColumn(name="GAMEID")},inverseJoinColumns = {@JoinColumn(name="ROLEID")})
	private Set<Role> roles = new HashSet<Role>();
	
	
	
	public int getGameid() {
		return gameid;
	}






	public Set<Role> getRoles() {
		return roles;
	}






	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}






	public void setGameid(int gameid) {
		this.gameid = gameid;
	}






	public String getGamename() {
		return gamename;
	}






	public void setGamename(String gamename) {
		this.gamename = gamename;
	}






	public Game(String gamename) {
		super();
		this.gamename = gamename;
	}






	public Game() {
	}

}
