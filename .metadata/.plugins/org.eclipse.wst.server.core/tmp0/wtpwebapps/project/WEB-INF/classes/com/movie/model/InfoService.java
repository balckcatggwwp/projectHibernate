package com.movie.model;

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
}
