package com.tick.interfac;

import java.util.List;

import com.tick.bean.BookTypeBean;
import com.tick.bean.BookticketBean;
import com.tick.bean.BookticketvuBean;
import com.tick.bean.HallsBean;
import com.tick.bean.MovieInfoBean;
import com.tick.bean.ShowtimeBean;
import com.tick.bean.onofflineBean;




public interface ItickDAO {
	//增
	public void inser(BookticketBean bean);
	//刪
	public void delticket(Integer tickid);
	//改
	public void updateticket(int time,String Seatid,int hall,int money,int movie,int type,String payout,int tickid);
	//查訂票id
	public List<BookticketvuBean> findticketbyid(String tickid);
	//查訂單編號
	public List<BookticketvuBean> findticketbyorderid(String orderid);
	//查會員id
	public List<BookticketvuBean> findticketbyuserid(String userid);
	//查日期
	public List<ShowtimeBean> findticketbystartdate(String startdate);
	//查廳
	public List<BookticketvuBean> findticketbyhall(String hallid);
	//查moviename
	public List<BookticketvuBean> findticketbyname(String findname);
	//查是否付款
	public List<BookticketvuBean> findticketbypay(String payout);
	//查全部
	public List<BookticketvuBean> findticketAll();
	//查用日期抓time
	public List<ShowtimeBean> findtimebydate(String date);
	//查廳
	public List<HallsBean> findhalls();
	//查座位
	public List<BookticketBean> findseatbytime(String showtimeid,String hallid );
	//查票種
	public List<BookTypeBean> findtype();
	//查電影
	public List<onofflineBean> findmoviename(String hallid);
	//查電影全部
	public List<MovieInfoBean> findmovienameall();
	//查來修改
	public BookticketvuBean Getticketbyid(String tickid);
}