package com.tick.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tick.bean.BookticketvuBean;
import com.tick.service.tickservice;
import com.tick.util.HibernateUtil;

@WebServlet("/findticketbyOther")
public class findticketbyOther extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public findticketbyOther() {
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
		String select = request.getParameter("select");
		Integer sw = Integer.parseInt(select);
		System.out.println(select);
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session =factory.getCurrentSession();
		tickservice tservice = new tickservice(session);
		List<BookticketvuBean> tick =null;
				
				
		
//		Dao dao =new Dao();
//		List<BookticketBean>  tick=null;
//		
		
		
		if(sw==1) {
			String tickid = request.getParameter("tickid");
			tick=tservice.findticketbyid(tickid);
		}else if(sw==2) {
			String orderid = request.getParameter("orderid");
			tick=tservice.findticketbyorderid(orderid);
		}else if(sw==3) {
			String userid = request.getParameter("userid");
			tick=tservice.findticketbyuserid(userid);
		}else if(sw==4) {
			String startdate = request.getParameter("startdate");
//		System.out.println(startdate);
			tick=tservice.findticketbystartdateta(startdate);
		}else if(sw==5) {
			String hallid = request.getParameter("hallid");
			tick=tservice.findticketbyhall(hallid);
			
		}else if(sw==6) {
			String findname = request.getParameter("findname");
			tick=tservice.findticketbyname(findname);
//		for(BookticketBean a:tick) {
//			System.out.println(a.getMoviename().length());
//			
//		}
		}else if(sw==7) {
			String payout = request.getParameter("payout");
			tick=tservice.findticketbypay(payout);
		}else {
			System.out.println("you are shit");
		}
		
//	try {
//		Dao dao =new Dao();
//		List<BookticketBean>  tick =dao.findticketbyid(tickid);
////		System.out.println(tick.getHallid());
		request.setAttribute("tickinfo", tick);
		try {
			request.getRequestDispatcher("/profind/datatable.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	} catch (Exception e) {
//		// TODO: handle exception
//	}
		
	}

}
