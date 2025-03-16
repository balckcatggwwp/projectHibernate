package com.tick.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.project.Servletutil.util;
import com.project.bean.BookTypeBean;
import com.project.bean.BookticketBean;
import com.project.bean.HallsBean;
import com.project.bean.MovieInfoBean;
import com.project.bean.ShowtimeBean;
import com.project.bean.onofflineBean;


public class Dao {
	
	//增
	public void inser(String order ,int user,int time,String Seatid,int hall,int money,int movie,int type,String payout) {
		String sql ="INSERT INTO Booktickets(orderid,userid,showtimeid,Seatid,hallid,onemoney,movieid,tickettypeid,payout)"+
					"VALUES(?,?,?,?,?,?,?,?,?)";
		
//		PreparedStatement stmt=null;
		
//		BookticketBean tick=null;
		try (			Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);
				
				){
			stmt.setString(1, order);
			stmt.setInt(2, user);
			stmt.setInt(3, time);
			stmt.setString(4, Seatid);
			stmt.setInt(5, hall);
			stmt.setInt(6, money);
			stmt.setInt(7, movie);
			stmt.setInt(8, type);
			stmt.setString(9, payout);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	//刪
	public void delticket(Integer tickid) {
		String sql="DELETE FROM Booktickets WHERE tickid=?";
		
		 //stmt=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
			
			stmt.setInt(1, tickid);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//改
	public void updateticket(int time,String Seatid,int hall,int money,int movie,int type,String payout,int tickid) {
		String sql="UPDATE Booktickets SET showtimeid=?,Seatid=?,hallid=?,onemoney=?,movieid=?,tickettypeid=?,payout=? WHERE tickid=? ";
		
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
			
			//stmt.setString(1, order);
			//stmt.setInt(2, user);
			stmt.setInt(1, time);
			stmt.setString(2, Seatid);
			stmt.setInt(3, hall);
			stmt.setInt(4, money);
			stmt.setInt(5, movie);
			stmt.setInt(6, type);
			stmt.setString(7, payout);
			stmt.setInt(8, tickid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//查訂票id
	public static List<BookticketBean> findticketbyid(String tickid) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE b.tickid=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
			 Integer tickint = Integer.parseInt(tickid);
			 stmt.setInt(1, tickint);
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	///////////////////////////////////////////////////////////////////////
	//查訂單編號
	public static List<BookticketBean> findticketbyorderid(String orderid) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE b.orderid=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
//			 Integer tickint = Integer.parseInt(tickid);
			 stmt.setString(1, orderid);
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	//查會員id
	public static List<BookticketBean> findticketbyuserid(String userid) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE b.userid=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
			 Integer userint = Integer.parseInt(userid);
			 stmt.setInt(1, userint);
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	//查日期
	public static List<BookticketBean> findticketbystartdate(String startdate) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE s.startdate=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
//			 Integer tickint = Integer.parseInt(tickid);
			 stmt.setDate(1, java.sql.Date.valueOf(startdate));
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	//查廳
	public static List<BookticketBean> findticketbyhall(String hallid) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE b.hallid=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
			 Integer hallint = Integer.parseInt(hallid);
			 stmt.setInt(1, hallint);
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	//查moviename
	public static List<BookticketBean> findticketbyname(String findname) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE b.movieid=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
			 Integer findnameint = Integer.parseInt(findname);
			 stmt.setInt(1, findnameint);
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	//查是否付款
	public static List<BookticketBean> findticketbypay(String payout) {
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
				" WHERE b.payout=?;";
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
				PreparedStatement stmt= conn.prepareStatement(sql);){
//			 Integer findnameint = Integer.parseInt(findname);
			 stmt.setString(1, payout);
			 ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
				tick=new BookticketBean();
				tick.setTickid(rs.getInt("tickid"));   //0k
				tick.setOrderid(rs.getString("orderid"));//ok
				tick.setUserid(rs.getInt("userid"));//ok
				tick.setStartdate(rs.getString("startdate"));
//				tick.setStarttime(rs.getString("starttime"));
				Time sqltime =rs.getTime("starttime");
				LocalTime localTime = sqltime.toLocalTime();
			    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
			    tick.setStarttime(formattedTime);
				tick.setSeatid(rs.getString("seatid"));//ok
				tick.setHallid(rs.getInt("hallid"));//ok
				tick.setOnemoney(rs.getInt("onemoney"));//ok
				tick.setMoviename(rs.getString("moviename"));
				tick.setBooktype(rs.getString("tickettype"));
				tick.setPayout(rs.getString("payout"));//ok
				ticks.add(tick);
			} ;
			}
		}catch (Exception e) {
		}
	return ticks;
	}
	///////////////////////////////////////////////////////////////////////
	//查全部
public static List<BookticketBean> findticketAll() {
		
		String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
				" join Movieinfo m on b.movieid=m.movieid"+
				" join Booktype bt on b.tickettypeid=bt.tickettypeid;";
//				" WHERE b.tickid=?;";
		
		
		
		 
		BookticketBean tick =null;
		List<BookticketBean> ticks=null;
		try (Connection conn = util.getConnection();
			PreparedStatement stmt= conn.prepareStatement(sql);){
			 
//			 Integer tickint = Integer.parseInt(tickid);
			 
//			stmt.setInt(1, tickint);
			
			ticks=new ArrayList<BookticketBean>();
			try (ResultSet rs=stmt.executeQuery();){
				while(rs.next()) {
					tick=new BookticketBean();
					tick.setTickid(rs.getInt("tickid"));   //0k
					tick.setOrderid(rs.getString("orderid"));//ok
					tick.setUserid(rs.getInt("userid"));//ok
					
					
//					tick.setShowtimeid(rs.getString("Showtimeid"));//okkk
					tick.setStartdate(rs.getString("startdate"));
//					tick.setStarttime(rs.getString("starttime"));
					/////////
					Time sqltime =rs.getTime("starttime");
					LocalTime localTime = sqltime.toLocalTime();
				    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
				    tick.setStarttime(formattedTime);
					
					tick.setSeatid(rs.getString("seatid"));//ok
					
					
					tick.setHallid(rs.getInt("hallid"));//ok
					tick.setOnemoney(rs.getInt("onemoney"));//ok
					
//					tick.setMovienameid(rs.getString("moviename"));
					tick.setMoviename(rs.getString("moviename"));
					
//					tick.setTickettypeid(rs.getString("tickettype"));
					tick.setBooktype(rs.getString("tickettype"));
					tick.setPayout(rs.getString("payout"));//ok
					ticks.add(tick);
			} 
			 
			
			}
		}catch (Exception e) {
		// TODO: handle exception
		}
		//System.out.println(tick);
//		System.out.println(stmt);
	return ticks;
	}
	
	//查用日期抓time
public List<ShowtimeBean> findtimebydate(String date){
	//System.out.println(date);
	String sql = "SELECT showtimeid ,starttime from showtime where startdate=?";
	
	
	
	ShowtimeBean time =null;
	List<ShowtimeBean> times=null;
	try (Connection conn = util.getConnection();
			PreparedStatement stmt= conn.prepareStatement(sql);){
		
		
		 stmt.setDate(1, java.sql.Date.valueOf(date));
		 
		
		try (ResultSet rs=stmt.executeQuery();){
			times=new ArrayList<>();
		while(rs.next()) {
			time=new ShowtimeBean();
			time.setShowtimeid(rs.getInt("showtimeid"));
			Time sqltime =rs.getTime("starttime");
			LocalTime localTime = sqltime.toLocalTime();
		    String formattedTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		    time.setStarttime(formattedTime);
//			time.setStarttime(rs.getString("starttime"));   //0k
			
			times.add(time);
		}
		
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return times;
	
}
//查廳
public List<HallsBean> findhalls(){
	
	String sql = "SELECT hallid  from halls";
	Connection conn = util.getConnection();
	PreparedStatement stmt=null;
	ResultSet rs=null;
	HallsBean hall =null;
	List<HallsBean> halls=null;
	try {
		stmt= conn.prepareStatement(sql);
		
		 
		rs=stmt.executeQuery();
		halls=new ArrayList<>();
		while(rs.next()) {
			hall=new HallsBean();
			hall.setHallid(rs.getInt("hallid"));
			
//			time.setStarttime(rs.getString("starttime"));   //0k
			
			halls.add(hall);
			}
	
		} catch (Exception e) {
	// TODO: handle exception
			}finally {
				util.closeResource(conn, stmt, rs);
			}
		return halls;
	
	}


//查座位
public static List<BookticketBean> findseatbytime(String showtimeid,String hallid ) {
	
	String sql = "SELECT seatid from Booktickets where showtimeid=? and hallid=? ;";
//			" WHERE b.tickid=?;";
	
	Connection conn = util.getConnection();
	PreparedStatement stmt=null;
	ResultSet rs=null;
	BookticketBean tick =null;
	List<BookticketBean> ticks=null;
	try {
		 stmt= conn.prepareStatement(sql);
//		 Integer tickint = Integer.parseInt(tickid);
		 
		stmt.setString(1, showtimeid);
		stmt.setString(2, hallid);
		
		rs=stmt.executeQuery();
		
		 ticks=new ArrayList<BookticketBean>();
		while(rs.next()) {
			tick=new BookticketBean();
			tick.setSeatid(rs.getString("seatid"));//ok
			ticks.add(tick);
		}
	}catch (Exception e) {
	// TODO: handle exception
	}finally {
		util.closeResource(conn, stmt, rs);
	}
//	System.out.println(stmt);
return ticks;
}
//查票種
public List<BookTypeBean> findtype(){
	
	String sql = "SELECT tickettypeid,tickettype,moneytype from Booktype";
	Connection conn = util.getConnection();
	PreparedStatement stmt=null;
	ResultSet rs=null;
	BookTypeBean type =null;
	List<BookTypeBean> types=null;
	try {
		stmt= conn.prepareStatement(sql);
		
		 
		rs=stmt.executeQuery();
		types=new ArrayList<>();
		while(rs.next()) {
			type=new BookTypeBean();
			
			type.setTickettypeid(rs.getInt("tickettypeid"));
			type.setTickettype(rs.getString("tickettype"));
			type.setMoneytype(rs.getInt("moneytype"));
			
//			time.setStarttime(rs.getString("starttime"));   //0k
			
			types.add(type);
			}
	
		} catch (Exception e) {
	// TODO: handle exception
			}finally {
				util.closeResource(conn, stmt, rs);
			}
		return types;
	
	}

//查電影
public List<onofflineBean> findmoviename(String hallid){
	String sql = "SELECT m.movieid,m.moviename from onoffline o join movieinfo m on o.movieid =m.movieid where o.hallid=?";
	Connection conn = util.getConnection();
	PreparedStatement stmt=null;
	ResultSet rs=null;
	onofflineBean name =null;
	List<onofflineBean> names=null;
	try {
		stmt= conn.prepareStatement(sql);
		
		 stmt.setString(1, hallid);
		rs=stmt.executeQuery();
		names=new ArrayList<>();
		while(rs.next()) {
			name=new onofflineBean();
			name.setMovieid(rs.getInt("movieid"));
			name.setMoviename(rs.getString("moviename"));
			
//			time.setStarttime(rs.getString("starttime"));   //0k
			
			names.add(name);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		util.closeResource(conn, stmt, rs);
	}
	return names;
	
}
//查電影全部
public List<MovieInfoBean> findmovienameall(){
	String sql = "SELECT movieid,moviename from movieinfo";
	Connection conn = util.getConnection();
	PreparedStatement stmt=null;
	ResultSet rs=null;
	MovieInfoBean name =null;
	List<MovieInfoBean> names=null;
	try {
		stmt= conn.prepareStatement(sql);
		
		 
		rs=stmt.executeQuery();
		names=new ArrayList<>();
		while(rs.next()) {
			name=new MovieInfoBean();
			name.setMovieid(rs.getInt("movieid"));
			name.setMoviename(rs.getString("moviename"));
			
//			time.setStarttime(rs.getString("starttime"));   //0k
			
			names.add(name);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		util.closeResource(conn, stmt, rs);
	}
	return names;
	
}
//查來修改
public static BookticketBean Getticketbyid(String tickid) {
	
	String sql = "SELECT b.tickid,b.orderid,b.userid,s.startdate,s.starttime,b.showtimeid,b.seatid,b.hallid,b.onemoney,m.moviename,bt.tickettype,b.payout From Booktickets b join Showtime s on b.showtimeid=s.showtimeid"+
			" join Movieinfo m on b.movieid=m.movieid"+
			" join Booktype bt on b.tickettypeid=bt.tickettypeid"+
			" WHERE b.tickid=?;";
	Connection conn = util.getConnection();
	PreparedStatement stmt=null;
	ResultSet rs=null;
	BookticketBean tick =null;
	//List<BookticketBean> ticks=null;
	try {
		 stmt= conn.prepareStatement(sql);
		 Integer tickint = Integer.parseInt(tickid);
		 
		 stmt.setInt(1, tickint);
//		 Integer tickint = Integer.parseInt(tickid);
		 
//		stmt.setInt(1, tickint);
		
		rs=stmt.executeQuery();
		tick=new BookticketBean();
		if(rs.next()) {
			tick.setTickid(rs.getInt("tickid"));   //0k
			tick.setOrderid(rs.getString("orderid"));//ok
			tick.setUserid(rs.getInt("userid"));//ok
			
			
//			tick.setShowtimeid(rs.getString("Showtimeid"));//okkk
			tick.setStartdate(rs.getString("startdate"));
			tick.setStarttime(rs.getString("starttime"));
			tick.setShowtimeid(rs.getInt("showtimeid"));
			
			tick.setSeatid(rs.getString("seatid"));//ok
			
			
			tick.setHallid(rs.getInt("hallid"));//ok
			tick.setOnemoney(rs.getInt("onemoney"));//ok
			
//			tick.setMovienameid(rs.getString("moviename"));
			tick.setMoviename(rs.getString("moviename"));
			
//			tick.setTickettypeid(rs.getString("tickettype"));
			tick.setPayout(rs.getString("payout"));
			tick.setBooktype(rs.getString("tickettypeid"));
		}
		 //ticks=new ArrayList<BookticketBean>();
		
	}catch (Exception e) {
	// TODO: handle exception
	}finally {
		util.closeResource(conn, stmt, rs);
	}
//	System.out.println(tick);
//	System.out.println(stmt);
return tick;
}

}