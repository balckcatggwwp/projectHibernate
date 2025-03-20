package com.movie.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.movie.bean.InfoBean;

public class InfoDao implements IInfoDao {
	
	private Session session;
	
	public InfoDao (Session session) {
		this.session = session;
	}
	
	@Override
	public List<InfoBean> findAll(){
		Query<InfoBean> query = session.createQuery("from InfoBean",InfoBean.class);
		return query.list();
	}
	
	@Override
	public InfoBean insert(InfoBean infoBean) {
		if(infoBean != null) {
			session.persist(infoBean);
			return infoBean;
		}
		return null;
	}
	
	@Override
	public List<InfoBean> findByKey(String key) {
	    String sql = "SELECT * FROM info WHERE movieName LIKE :key OR rating LIKE :key OR genre LIKE :key OR "
	               + "director LIKE :key OR actor LIKE :key OR runtime LIKE :key OR releaseDate LIKE :key OR "
	               + "movieStatus LIKE :key OR movieDescription LIKE :key OR images LIKE :key OR trailer LIKE :key OR id LIKE :key";

	    Query<InfoBean> query = session.createNativeQuery(sql, InfoBean.class);
	    query.setParameter("key", "%" + key + "%");  
	    return query.list();
	}
	
	@Override
	public InfoBean findById(Integer id) {
		return session.get(InfoBean.class, id);
	}
	
	@Override
	public boolean delete(Integer id) {
		InfoBean deleteBean = session.get(InfoBean.class, id);
		
		if(deleteBean != null) {
			session.remove(deleteBean);
			return true;
		}
		
		return false;
	}
	
	@Override
	public InfoBean update(Integer id, String movieName, String rating, String genre, String director, String actor, Integer runtime, 
			Date releaseDate, String status, String movieDescription, String image, String trailer) {
		InfoBean resultBean = session.get(InfoBean.class, id);
		
		if(resultBean != null) {
			resultBean.setName(movieName);
			resultBean.setRating(rating);
			resultBean.setGenre(genre);
			resultBean.setDirector(director);
			resultBean.setActor(actor);
			resultBean.setRuntime(runtime);
			resultBean.setReleaseDate(releaseDate);
			resultBean.setStatus(status);
			resultBean.setDescription(movieDescription);
			resultBean.setImage(image);
			resultBean.setTrailer(trailer);
		}
		
		return resultBean;
	}
	
}
