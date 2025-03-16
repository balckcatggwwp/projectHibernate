package com.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.project.bean.BookticketBean;
import com.project.dao.Dao;

@WebServlet("/findticketbyOther")
public class findticketbyOther extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public findticketbyOther() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	String select = request.getParameter("select");
	Integer sw = Integer.parseInt(select);
	System.out.println(select);
	Dao dao =new Dao();
	List<BookticketBean>  tick=null;
	if(sw==1) {
		String tickid = request.getParameter("tickid");
		tick=dao.findticketbyid(tickid);
	}else if(sw==2) {
		String orderid = request.getParameter("orderid");
		tick=dao.findticketbyorderid(orderid);
	}else if(sw==3) {
		String userid = request.getParameter("userid");
		tick=dao.findticketbyuserid(userid);
	}else if(sw==4) {
		String startdate = request.getParameter("startdate");
//		System.out.println(startdate);
		tick=dao.findticketbystartdate(startdate);
	}else if(sw==5) {
		String hallid = request.getParameter("hallid");
		tick=dao.findticketbyhall(hallid);
		
	}else if(sw==6) {
		String findname = request.getParameter("findname");
		tick=dao.findticketbyname(findname);
//		for(BookticketBean a:tick) {
//			System.out.println(a.getMoviename().length());
//			
//		}
	}else if(sw==7) {
		String payout = request.getParameter("payout");
		tick=dao.findticketbypay(payout);
	}else {
		System.out.println("you are shit");
	}
	
//	try {
//		Dao dao =new Dao();
//		List<BookticketBean>  tick =dao.findticketbyid(tickid);
////		System.out.println(tick.getHallid());
		request.setAttribute("tickinfo", tick);
		request.getRequestDispatcher("/profind/datatable.jsp").forward(request, response);
//	} catch (Exception e) {
//		// TODO: handle exception
//	}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
