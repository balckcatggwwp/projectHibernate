package com.movie.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.movie.bean.InfoBean;
import com.movie.model.IInfoService;
import com.movie.model.InfoService;
import com.tick.util.HibernateUtil;

public class insert {
	
	public static void main(String[] args) {
	
	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	Session session = factory.getCurrentSession();
	

	try {
		session.beginTransaction();
		IInfoService infoService = new InfoService(session);
		
		InfoBean test = new InfoBean();
		
		test.setName("543543");
		test.setTrailer("34534543");
		
		InfoBean insert = infoService.insert(test);
		
		System.out.println(insert);
		
		
		
		
		session.getTransaction().commit();
		
	} catch (Exception e) {
		session.getTransaction().rollback();
	}finally {
		HibernateUtil.closeSessionFactory();
	}
}


}
