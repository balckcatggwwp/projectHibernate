package com.tick.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tick.bean.BookticketBean;
import com.tick.service.tickservice;
import com.tick.util.HibernateUtil;

@WebServlet("/inster")
public class inster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public inster() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}
	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String orderid = request.getParameter("orderid");
		
		String userid = request.getParameter("userid");
		Integer user = Integer.parseInt(userid);
		
		String Seatid = request.getParameter("Seatid");
//		Integer seat = Integer.parseInt(Seatid);
		
		String starttime = request.getParameter("starttime");
		Integer time = Integer.parseInt(starttime);
		
		String hallid = request.getParameter("hallid");
		Integer hall = Integer.parseInt(hallid);
		
		String tickettypeid = request.getParameter("tickettypeid");
		Integer type = Integer.parseInt(tickettypeid);
		
		String movieid = request.getParameter("movieid");
		Integer movie = Integer.parseInt(movieid);
		
		String onemoney = request.getParameter("onemoney");
		Integer money = Integer.parseInt(onemoney);
		
		String payout = request.getParameter("payout");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session =factory.getCurrentSession();
		tickservice tservice = new tickservice(session);
		BookticketBean bean = new BookticketBean(orderid, user, time, Seatid, hall, money, movie, type, payout);
		tservice.inser(bean);
//		Dao dao =new Dao();
		//System.out.println(orderid);
		//dao.inser(orderid,user,time,Seatid,hall,money,movie,type,payout);
		try {
			request.getRequestDispatcher("findticketAll").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
