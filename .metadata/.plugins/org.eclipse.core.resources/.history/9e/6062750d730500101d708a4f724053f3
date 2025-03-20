package com.movie.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

import com.movie.bean.InfoBean;

public class InfoService implements IInfoService{

	private InfoDao infoDao;
	
	public InfoService(Session session) {
		infoDao = new InfoDao(session);
	}
	
	@Override
	public List<InfoBean> findAll(){
		return infoDao.findAll();
	}
	
	@Override
	public InfoBean insert(InfoBean infoBean) {
		return infoDao.insert(infoBean);	
	}
	
	@Override
	public List<InfoBean> findByKey(String key){
		return infoDao.findByKey(key);
	}
	
	@Override
	public InfoBean findById(Integer id) {
		return infoDao.findById(id);
	}
	
	@Override
	public boolean delete(Integer id) {
		return infoDao.delete(id);
	}
	
	@Override
	public InfoBean update(Integer id,String movieName, String rating, String genre, String director, String actor, Integer runtime, 
			Date releaseDate, String status, String movieDescription, String image, String trailer) {
		return infoDao.update(id, movieName, rating, genre, director, actor, runtime, releaseDate, status, movieDescription, image, trailer);
	}
}
