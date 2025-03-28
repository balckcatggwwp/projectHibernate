package com.movie.model;

import java.sql.Date;
import java.util.List;

import com.movie.bean.InfoBean;

public interface IInfoService {

	
	public List<InfoBean> findAll();
	public List<InfoBean> findByKey(String key);
	public List<InfoBean> findByName(String key);
	public InfoBean findById(Integer key);
	public InfoBean insert(InfoBean infoBean);
	public boolean delete(Integer id);
	public InfoBean update(Integer id, String movieName, String rating, String genre, String director, String actor, Integer runtime, 
			Date releaseDate, String status, String movieDescription, String image, String trailer);
	
}
