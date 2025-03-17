package com.tick.service;

import java.util.List;

import org.hibernate.Session;

import com.tick.bean.BookTypeBean;
import com.tick.bean.BookticketBean;
import com.tick.bean.BookticketvuBean;
import com.tick.bean.HallsBean;
import com.tick.bean.MovieInfoBean;
import com.tick.bean.ShowtimeBean;
import com.tick.bean.onofflineBean;
import com.tick.dao.TickDao;
import com.tick.interfac.ItickService;



public class tickservice implements ItickService{
	private TickDao tickDao;

	public tickservice (Session session) {
		tickDao= new TickDao(session);
	}
	@Override
	public void inser(BookticketBean bean) {
		 tickDao.inser(bean);
	};
	//刪
	@Override
	public void delticket(Integer tickid) {
		tickDao.delticket(tickid);
	};
	//改
	@Override
	public void updateticket(int time,String Seatid,int hall,int money,int movie,int type,String payout,int tickid) {
		tickDao.updateticket(time, Seatid, hall, money, movie, type, payout, tickid);
		
	};
	//查訂票id
	@Override
	public List<BookticketvuBean> findticketbyid(String tickid){
		return tickDao.findticketbyid(tickid);
	};
	//查訂單編號
	@Override
	public List<BookticketvuBean> findticketbyorderid(String orderid){
		return tickDao.findticketbyorderid(orderid);
	};
	//查會員id
	@Override
	public List<BookticketvuBean> findticketbyuserid(String userid){
		
		return tickDao.findticketbyuserid(userid);
	};
	//查日期
	@Override
	public List<ShowtimeBean> findticketbystartdate(String startdate){
		return tickDao.findticketbystartdate(startdate);
	};
	//查廳
	@Override
	public List<BookticketvuBean> findticketbyhall(String hallid){
		return tickDao.findticketbyhall(hallid);
	};
	//查moviename
	@Override
	public List<BookticketvuBean> findticketbyname(String findname){
		return tickDao.findticketbyname(findname);
	};
	//查是否付款
	@Override
	public List<BookticketvuBean> findticketbypay(String payout){
		return tickDao.findticketbypay(payout);
	};
	//查全部
	@Override
	public List<BookticketvuBean> findticketAll(){
		System.out.println("a2");
		return tickDao.findticketAll();
	};
	//查用日期抓time
	@Override
	public List<ShowtimeBean> findtimebydate(String date){
		return tickDao.findtimebydate(date);
	};
	//查廳
	@Override
	public List<HallsBean> findhalls(){
		return tickDao.findhalls()
				;
	};
	//查座位
	@Override
	public List<BookticketBean> findseatbytime(String showtimeid,String hallid ){
		return tickDao.findseatbytime(showtimeid, hallid);
	};
	//查票種
	@Override
	public List<BookTypeBean> findtype(){
		return tickDao.findtype();
	};
	//查電影
	@Override
	public List<onofflineBean> findmoviename(String hallid){
		return tickDao.findmoviename(hallid);
	};
	//查電影全部
	@Override
	public List<MovieInfoBean> findmovienameall(){
		return tickDao.findmovienameall();
	};
	//查來修改
	@Override
	public BookticketvuBean Getticketbyid(String tickid) {
		
		return tickDao.Getticketbyid(tickid);
	}
	
}
