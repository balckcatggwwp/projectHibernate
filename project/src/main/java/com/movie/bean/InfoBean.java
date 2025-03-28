package com.movie.bean;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "info")
public class InfoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "movieName")
	private String name;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "actor")
	private String actor;
	
	@Column(name = "runtime")
	private Integer runtime;
	
	@Column(name = "releaseDate")
	private Date releaseDate;
	
	@Column(name = "movieStatus")
	private String status;
	
	@Column(name = "movieDescription")
	private String description;
	
	@Column(name = "images")
	private String image;
	
	@Column(name = "trailer")
	private String trailer;
	
	public InfoBean() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	@Override
	public String toString() {
		return "InfoBean [id=" + id + ", name=" + name + ", rating=" + rating + ", genre=" + genre + ", director="
				+ director + ", actor=" + actor + ", runtime=" + runtime + ", releaseDate=" + releaseDate + ", status="
				+ status + ", description=" + description + ", image=" + image + ", trailer=" + trailer + "]";
	}
	
	

}
