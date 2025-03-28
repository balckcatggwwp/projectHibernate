package com.tick.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.tick.bean.BookTypeBean;
import com.tick.bean.BookticketBean;
import com.tick.bean.BookticketvuBean;
import com.tick.bean.HallsBean;
import com.tick.bean.MovieInfoBean;
import com.tick.bean.ShowtimeBean;
import com.tick.bean.onofflineBean;
import com.tick.interfac.ItickDAO;



public class TickDao implements ItickDAO{
	private Session session;

	public TickDao(Session session) {
		this.session = session;
	}
	@Override
	public void inser(BookticketBean bean) {
		if(bean!=null) {
			session.persist(bean);
		}
	};
	//刪
	@Override
	public void delticket(Integer tickid) {
		try {
			BookticketBean bean= session.get(BookticketBean.class,tickid);
			if(bean!=null) {
				session.remove(bean);
				session.flush();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	};
	//改
	@Override
	public void updateticket(int time,String Seatid,int hall,int money,int movie,int type,String payout,int tickid) {
		BookticketBean bean = session.get(BookticketBean.class, tickid);
		if(bean!=null) {
			bean.setShowtimeid(time);
			bean.setSeatid(Seatid);
			bean.setHallid(hall);
			bean.setOnemoney(money);
			bean.setMovieid(movie);
			bean.setTickettypeid(type);
			bean.setPayout(payout);
			session.flush();
		}
	};
	//查訂票id
	@Override
	public List<BookticketvuBean> findticketbyid(String tickid){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where tickid =:n",BookticketvuBean.class);
		Integer s = Integer.parseInt(tickid);
		query.setParameter("n", s);
		return query.list();
	};
	//查訂單編號
	@Override
	public List<BookticketvuBean> findticketbyorderid(String orderid){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where orderid =:n",BookticketvuBean.class);
		query.setParameter("n", orderid);
		return query.list();
	};
	//查會員id
	@Override
	public List<BookticketvuBean> findticketbyuserid(String userid){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where userid =:n",BookticketvuBean.class);
		Integer s = Integer.parseInt(userid);
		query.setParameter("n", s);
		return query.list();
	};
	//查日期
	@Override
	public List<ShowtimeBean> findticketbystartdate(String startdate){
		try {
			Query<ShowtimeBean> query = session.createQuery("from ShowtimeBean where startdate =:n",ShowtimeBean.class);
			
			query.setParameter("n", startdate);
			return query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};
	@Override
	public List<BookticketvuBean> findticketbystartdateta(String startdate){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where startdate =:n",BookticketvuBean.class);
		query.setParameter("n", startdate);
		return query.list();
	};
	//查廳
	@Override
	public List<BookticketvuBean> findticketbyhall(String hallid){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where hallid =:n",BookticketvuBean.class);
		Integer s = Integer.parseInt(hallid);
		query.setParameter("n", s);
		return query.list();
	};
	//查moviename
	@Override
	public List<BookticketvuBean> findticketbyname(String findname){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where moviename=:n",BookticketvuBean.class);
		query.setParameter("n", findname);
		return query.list();
	};
	//查是否付款
	@Override
	public List<BookticketvuBean> findticketbypay(String payout){
		Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean where payout =:n",BookticketvuBean.class);
		query.setParameter("n", payout);
		return query.list();
	};
	//查全部
	@Override
	public List<BookticketvuBean> findticketAll(){
		try {
			
			Query<BookticketvuBean> query = session.createQuery("from BookticketvuBean",BookticketvuBean.class);
			System.out.println("QUERY:"+query);
			return query.list();
		} catch (Exception e) {
			System.out.println("gg");
			 e.printStackTrace();
			return null;
		}
	};
	//查用日期抓time
	@Override
	public List<ShowtimeBean> findtimebydate(String date){
		Query<ShowtimeBean> query = session.createQuery("from ShowtimeShowtimeBean where startdate =:n",ShowtimeBean.class);
		query.setParameter("n", date);
		return query.list();
	};
	//查廳
	@Override
	public List<HallsBean> findhalls(){
		Query<HallsBean> query = session.createQuery("from HallsBean ",HallsBean.class);
		return query.list();
	};
	//查座位
	@Override
	public List<BookticketBean> findseatbytime(int showtimeid,int hallid ){
		try {
			System.out.println(showtimeid+"===========================");
			System.out.println(hallid);
			Query<BookticketBean> query = session.createQuery("from BookticketBean where showtimeid =:n and hallid=:x",BookticketBean.class);
			query.setParameter("n", showtimeid);
			query.setParameter("x", hallid);
			return query.list();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};
	//查票種
	@Override
	public List<BookTypeBean> findtype(){
		try {
			Query<BookTypeBean> query = session.createQuery("from BookTypeBean",BookTypeBean.class);
			return query.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	};
	//查電影
	@Override
	public List<onofflineBean> findmoviename(String hallid){
		Query<onofflineBean> query = session.createQuery("from onofflineBean where hallid =:n",onofflineBean.class);
		query.setParameter("n", hallid);
		return query.list();
	};
	//查電影全部
	@Override
	public List<MovieInfoBean> findmovienameall(){
		Query<MovieInfoBean> query = session.createQuery("from MovieInfoBean ",MovieInfoBean.class);
		return query.list();
	};
	//查來修改
	@Override
	public BookticketvuBean Getticketbyid(String tickid) {
		
		return session.get(BookticketvuBean.class,tickid);
	};
}
